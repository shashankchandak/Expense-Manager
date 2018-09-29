package com.shashank.expensemanager.transactionDb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shashank.expensemanager.utils.Constants;

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

//    The code below can be ignored

    public void updateTransaction(TransactionEntry transactionEntry){
        new updateTransactionDetails(appDatabase).execute(transactionEntry);
    }

   private static class updateTransactionDetails extends AsyncTask<TransactionEntry,Void,Void>{

        private AppDatabase mdb;

        public updateTransactionDetails(AppDatabase appDatabase){
            mdb = appDatabase;
        }
       @Override
       protected Void doInBackground(TransactionEntry... transactionEntries) {
            mdb.transactionDao().updateExpenseDetails(transactionEntries[0]);
           return null;
       }
   }


}