package nattapon58070036.lab09.kmitl.moneyflow.transaction;

/**
 * Created by SMART on 8/11/2560.
 */

import android.os.AsyncTask;

import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;


public class AddTransaction extends AsyncTask<Transaction, Void, Void> {

    private MoneyDB database;
    private OnAddSuccessListener listener;

    public AddTransaction(MoneyDB db, OnAddSuccessListener l) {
        this.database = db;
        this.listener = l;
    }

    @Override
    protected Void doInBackground(Transaction... transactions) {
        for (int i = 0; i < transactions.length; i++) {
            database.transactionDAO().insert(transactions[i]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onAddSuccess();
    }

    public interface OnAddSuccessListener {
        void onAddSuccess();
    }
}
