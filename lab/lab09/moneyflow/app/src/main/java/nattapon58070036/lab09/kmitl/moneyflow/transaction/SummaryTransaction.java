package nattapon58070036.lab09.kmitl.moneyflow.transaction;

/**
 * Created by SMART on 8/11/2560.
 */

import android.os.AsyncTask;

import nattapon58070036.lab09.kmitl.moneyflow.database.MoneyDB;
import nattapon58070036.lab09.kmitl.moneyflow.model.Summary;


public class SummaryTransaction extends AsyncTask<Void, Void, Summary> {

    private MoneyDB database;
    private OnSummarySuccessListener listener;

    public SummaryTransaction(MoneyDB db, OnSummarySuccessListener l) {
        this.database = db;
        this.listener = l;
    }

    @Override
    protected Summary doInBackground(Void... voids) {
        return database.transactionDAO().getSummary();
    }

    @Override
    protected void onPostExecute(Summary summary) {
        super.onPostExecute(summary);
        listener.onSummarySuccess(summary);
    }

    public interface OnSummarySuccessListener {
        void onSummarySuccess(Summary summary);
    }
}
