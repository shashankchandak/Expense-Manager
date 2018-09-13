package com.shashank.expensemanager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.expensemanager.R;
import com.shashank.expensemanager.adapters.CustomAdapter;
import com.shashank.expensemanager.transactionDb.TransactionEntry;
import com.shashank.expensemanager.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;



public class ExpenseFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<TransactionEntry> transactionEntries;
    private CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_expense,container,false);


        rv=view.findViewById(R.id.transactionRecyclerView);
        transactionEntries=new ArrayList<>();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


        // TODO: 13-09-2018  retrieve queries from database and add here ,for now just hardcoded irreveleant times.
        transactionEntries.add(new TransactionEntry(100,"Food","A random description to see how it looks", Calendar.getInstance().getTime(), Constants.expenseCategory));
        transactionEntries.add(new TransactionEntry(200,"Travel","Just roaming around",Calendar.getInstance().getTime(), Constants.expenseCategory));
        transactionEntries.add(new TransactionEntry(100,"Income","Pocket Money",Calendar.getInstance().getTime(), Constants.incomeCategory));
        transactionEntries.add(new TransactionEntry(100,"Income","Pocket Money",Calendar.getInstance().getTime(), Constants.incomeCategory));

        transactionEntries.add(new TransactionEntry(200,"Food","Mcdonalds",Calendar.getInstance().getTime(), Constants.expenseCategory));
        transactionEntries.add(new TransactionEntry(100,"Income","Pocket Money",Calendar.getInstance().getTime(), Constants.incomeCategory));

        customAdapter=new CustomAdapter(getActivity(),transactionEntries);
        rv.setAdapter(customAdapter);
        return view;
    }
}
