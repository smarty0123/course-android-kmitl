package nattapon58070036.lab09.kmitl.moneyflow.transaction;

/**
 * Created by SMART on 8/11/2560.
 */

import android.os.AsyncTask;

import java.util.List;

import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;


public class FetchTransaction extends AsyncTask<Void, Void, List<Transaction>> {

    private MoneyDB database;
    private OnFetchSuccessListener listener;

    public FetchTransaction(MoneyDB db, OnFetchSuccessListener l) {
        this.database = db;
        this.listener = l;
    }

    @Override
    protected List<Transaction> doInBackground(Void... voids) {
        return database.transactionDAO().getTransactions();
    }

    @Override
    protected void onPostExecute(List<Transaction> transactionList) {
        super.onPostExecute(transactionList);
        listener.onFetchSuccess(transactionList);
    }

    public interface OnFetchSuccessListener {
        void onFetchSuccess(List<Transaction> transactionList);
    }

}
