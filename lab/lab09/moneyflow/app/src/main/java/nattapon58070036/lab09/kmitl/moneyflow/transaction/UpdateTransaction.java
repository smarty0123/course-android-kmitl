package nattapon58070036.lab09.kmitl.moneyflow.transaction;

/**
 * Created by SMART on 8/11/2560.
 */

import android.os.AsyncTask;

import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;


public class UpdateTransaction extends AsyncTask<Transaction, Void, Void> {

    private MoneyDB database;
    private OnUpdateSuccessListener listener;

    public UpdateTransaction(MoneyDB db, OnUpdateSuccessListener l) {
        this.database = db;
        this.listener = l;
    }

    @Override
    protected Void doInBackground(Transaction... transactions) {
        for (int i = 0; i < transactions.length; i++) {
            database.transactionDAO().update(transactions[i]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onUpdateSuccess();
    }

    public interface OnUpdateSuccessListener {
        void onUpdateSuccess();
    }
}
