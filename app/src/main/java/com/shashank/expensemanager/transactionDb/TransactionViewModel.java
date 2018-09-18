package com.shashank.expensemanager.transactionDb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    public final LiveData<List<TransactionEntry>> expenseList;

    private AppDatabase appDatabase;

    public TransactionViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());

        expenseList = appDatabase.transactionDao().loadAllTransactions();
    }

    public LiveData<List<TransactionEntry>> getExpenseList() {
        return expenseList;
    }


}