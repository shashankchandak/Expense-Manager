package com.shashank.expensemanager.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shashank.expensemanager.R;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    Button addIncomeButton;
    Button addExpenseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_fragment, container, false);

        addIncomeButton=v.findViewById(R.id.bottom_sheet_income_btn);
        addExpenseButton=v.findViewById(R.id.bottom_sheet_expense_btn);

        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Income",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Expense",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });


        return v;
    }
}