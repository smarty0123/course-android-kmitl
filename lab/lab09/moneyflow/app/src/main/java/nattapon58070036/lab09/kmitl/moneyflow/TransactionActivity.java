package nattapon58070036.lab09.kmitl.moneyflow;

/**
 * Created by SMART on 8/11/2560.
 */

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;
import nattapon58070036.lab09.kmitl.moneyflow.transaction.AddTransaction;
import nattapon58070036.lab09.kmitl.moneyflow.transaction.DeleteTransaction;
import nattapon58070036.lab09.kmitl.moneyflow.transaction.UpdateTransaction;


public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    private MoneyDB database;
    private Transaction mTransaction;

    private RadioGroup radioType;
    private EditText describeEditText;
    private EditText amountEditText;
    private Button saveButton;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initDB();
        initInstances();
        setupInfo();
    }

    private void initInstances() {
        radioType = findViewById(R.id.radioType);
        describeEditText = findViewById(R.id.describeEditText);
        amountEditText = findViewById(R.id.amountEditText);
        saveButton = findViewById(R.id.saveButton);
        delButton = findViewById(R.id.delButton);
        radioType.check(R.id.income);
        saveButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        mTransaction = getIntent().getParcelableExtra(Transaction.class.getSimpleName());
    }

    private void initDB() {
        database = Room.databaseBuilder(getApplicationContext(), MoneyDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();
    }

    private void setupInfo() {
        if (mTransaction != null) {
            setTitle(getString(R.string.title_edit));
            if (mTransaction.getType().equals(getString(R.string.type_income))) {
                radioType.check(R.id.income);
            } else {
                radioType.check(R.id.outcome);
            }
            describeEditText.setText(mTransaction.getDescribe());
            amountEditText.setText(String.valueOf(mTransaction.getAmount()));
            delButton.setVisibility(View.VISIBLE);
        } else {
            setTitle(getString(R.string.title_add));
            delButton.setVisibility(View.GONE);
        }
    }

    private void saveTransaction() {
        String type;
        String describe;
        int amount;
        int checkedRadioButtonId = radioType.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.income) {
            type = getString(R.string.type_income);

        } else if (checkedRadioButtonId == R.id.outcome) {
            type = getString(R.string.type_outcome);

        } else {
            type = "";
        }

        describe = describeEditText.getText().toString();

        try {
            amount = Integer.parseInt(amountEditText.getText().toString());
        } catch (IllegalArgumentException ignore) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        Transaction transaction = new Transaction(type, describe, amount);

        if (mTransaction != null) {
            mTransaction.updateInfo(transaction);
            new UpdateTransaction(database, new UpdateTransaction.OnUpdateSuccessListener() {
                @Override
                public void onUpdateSuccess() {
                    finish();
                }
            }).execute(mTransaction);

        } else {
            new AddTransaction(database, new AddTransaction.OnAddSuccessListener() {
                @Override
                public void onAddSuccess() {
                    finish();
                }
            }).execute(transaction);
        }
    }

    private void deleteTransaction() {
        new DeleteTransaction(database, new DeleteTransaction.OnDeleteSuccessListener() {
            @Override
            public void onDeleteSuccess() {
                finish();
            }
        }).execute(mTransaction);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveButton) {
            saveTransaction();
        }else if(view.getId() == R.id.delButton) {
            deleteTransaction();
        }
    }
}
