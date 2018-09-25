package com.shashank.expensemanager.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.shashank.expensemanager.R;
import com.shashank.expensemanager.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.shashank.expensemanager.activities.MainActivity.fab;


public class BalanceFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    PieChart pieChart;
    Spinner spinner;
    Integer balance[] = {55,50,220,0};
    String category[] = {"Food", "Theatre", "Sports", "Petrol"} ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_balance,container,false);

        pieChart= view.findViewById(R.id.balancePieChart);
        spinner = view.findViewById(R.id.spinner);
        setupSpinner();
        spinner.setOnItemSelectedListener(this);
        return view;

    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.date_array,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            setupPieChart();
            fab.setVisibility(View.GONE);
        } else{
            fab.setVisibility(View.VISIBLE);
        }
    }

    private void setupPieChart() {

        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0 ; i < balance.length; i++){
            pieEntries.add(new PieEntry(balance[i],category[i]));
        }
        pieChart.setVisibility(View.VISIBLE);
        PieDataSet dataSet = new PieDataSet(pieEntries,null);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(20);

        pieChart.setData(pieData);
//        pieData.setValueTextColor

        pieChart.animateY(1000);
        pieChart.invalidate();



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.i("Item selected : ", (String) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
