package com.shashank.expensemanager.transactionDb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface TransactionDao {

    @Query("select * from transactionTable order by id")
    LiveData<List<TransactionEntry>> loadAllTransactions();

    @Insert
    void insertExpense(TransactionEntry transactionEntry);

    @Delete
    void removeExpense(TransactionEntry transactionEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExpenseDetails(TransactionEntry transactionEntry);
}
