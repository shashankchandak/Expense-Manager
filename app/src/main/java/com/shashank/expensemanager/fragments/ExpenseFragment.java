package com.shashank.expensemanager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.expensemanager.R;
import com.shashank.expensemanager.adapters.CustomAdapter;

import java.util.ArrayList;


public class ExpenseFragment extends Fragment {

    private RecyclerView rv;
    //private ArrayList<Messages> messageList;
    private CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_expense,container,false);



        return view;
    }
}
