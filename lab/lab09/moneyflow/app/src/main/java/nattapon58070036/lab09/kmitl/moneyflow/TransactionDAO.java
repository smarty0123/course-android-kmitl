package nattapon58070036.lab09.kmitl.moneyflow;

/**
 * Created by SMART on 8/11/2560.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nattapon58070036.lab09.kmitl.moneyflow.model.Summary;
import nattapon58070036.lab09.kmitl.moneyflow.model.Transaction;


@Dao
public interface TransactionDAO {

    @Query("SELECT * FROM `Transaction` ORDER BY id ASC")
    List<Transaction> getTransactions();

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT total_income, total_outcome FROM " +
            "(SELECT SUM(amount) AS total_income FROM `Transaction` WHERE type == 'income')" +
            "JOIN " +
            "(SELECT SUM(amount) AS total_outcome FROM `Transaction` WHERE type == 'outcome')")
    Summary getSummary();
}
