package nattapon58070036.lab09.kmitl.moneyflow;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import nattapon58070036.lab09.kmitl.moneyflow.adapter.RecyclerItemClickListener;
import nattapon58070036.lab09.kmitl.moneyflow.adapter.TransactionAdapter;
import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Summary;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;
import nattapon58070036.lab09.kmitl.moneyflow.transaction.FetchTransaction;
import nattapon58070036.lab09.kmitl.moneyflow.transaction.SummaryTransaction;


public class MainActivity extends AppCompatActivity {

    private MoneyDB database;

    private TextView resultText;
    private RecyclerView list;
    private TransactionAdapter adapter;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDatabase();
        initInstances();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void initInstances() {
        resultText = findViewById(R.id.resultText);
        list = findViewById(R.id.list);
        addButton = findViewById(R.id.addButton);

        adapter = new TransactionAdapter(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.addOnItemTouchListener(new RecyclerItemClickListener(this, list,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startTransactionActivity(adapter.getTransactionList().get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTransactionActivity(null);
            }
        });
    }

    private void setupDatabase() {
        database = Room.databaseBuilder(getApplicationContext(), MoneyDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();
    }

    private void startTransactionActivity(@Nullable Transaction transaction) {
        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra(Transaction.class.getSimpleName(), transaction);
        startActivity(intent);
    }

    private void fetchData() {
        new FetchTransaction(database, new FetchTransaction.OnFetchSuccessListener() {
            @Override
            public void onFetchSuccess(List<Transaction> transactionList) {
                updateList(transactionList);
            }
        }).execute();

        new SummaryTransaction(database, new SummaryTransaction.OnSummarySuccessListener() {
            @Override
            public void onSummarySuccess(Summary summary) {
                updateMoney(summary);
            }
        }).execute();
    }

    private void updateList(List<Transaction> transactionList) {
        adapter.setTransactionList(transactionList);
        adapter.notifyDataSetChanged();
    }

    private void updateMoney(Summary summary) {
        int sum = summary.getSum();
        int totalIncome = summary.getTotalIncome();

        if ((float) sum / totalIncome < 0.25) {
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        } else if ((float) sum / totalIncome <= 0.5) {
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        } else {
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        }
        resultText.setText(NumberFormat.getNumberInstance().format(sum));
    }
}
