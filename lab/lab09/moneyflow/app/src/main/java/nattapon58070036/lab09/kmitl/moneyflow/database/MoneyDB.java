package nattapon58070036.lab09.kmitl.moneyflow.database;

/**
 * Created by SMART on 8/11/2560.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import nattapon58070036.lab09.kmitl.moneyflow.TransactionDAO;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;


@Database(entities = Transaction.class, version = 1)
public abstract class MoneyDB extends RoomDatabase {

    public abstract TransactionDAO transactionDAO();

}
