package com.shashank.expensemanager.fragments;

import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.shashank.expensemanager.R;
import com.shashank.expensemanager.activities.MainActivity;
import com.shashank.expensemanager.transactionDb.AppDatabase;
import com.shashank.expensemanager.transactionDb.AppExecutors;
import com.shashank.expensemanager.transactionDb.TransactionViewModel;
import com.shashank.expensemanager.utils.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.shashank.expensemanager.activities.MainActivity.fab;


public class BalanceFragment extends Fragment implements AdapterView.OnItemSelectedListener{


    private AppDatabase mAppDb;
    PieChart pieChart;
    Spinner spinner;

    private TextView balanceTv,incomeTv,expenseTv;

    private TextView dateTv;

    private int balanceAmount,incomeAmount,expenseAmount;
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

        mAppDb = AppDatabase.getInstance(getContext());
        balanceTv = view.findViewById(R.id.totalAmountTextView);
        expenseTv = view.findViewById(R.id.amountForExpenseTextView);
        incomeTv = view.findViewById(R.id.amountForIncomeTextView);

        dateTv = view.findViewById(R.id.dateTextView);

        getBalanceAmount();
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

    private void getBalanceAmount(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                int income = mAppDb.transactionDao().getAmountByTransactionType(Constants.incomeCategory);
                incomeAmount = income;
                int expense = mAppDb.transactionDao().getAmountByTransactionType(Constants.expenseCategory);
                expenseAmount = expense;
                int balance = income - expense;
                balanceAmount = balance;
                Log.d("TAGGGD",String.valueOf(balance));
            }
        });
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                balanceTv.setText("\u20B9" +String.valueOf(balanceAmount));
                incomeTv.setText("\u20B9" +String.valueOf(incomeAmount));
                expenseTv.setText("\u20B9" +String.valueOf(expenseAmount));
            }
        });


    }

    private void getWeekExtremes(){
        // get Current Week of the year
        Calendar calendar;
        calendar=Calendar.getInstance();
        Log.v("Current Week", String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
        int current_week=calendar.get(Calendar.WEEK_OF_YEAR);
        int week_start_day=calendar.getFirstDayOfWeek(); // this will get the starting day os week in integer format i-e 1 if monday
        Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is"+week_start_day,Toast.LENGTH_SHORT).show();


        // get the starting and ending date
        // Set the calendar to sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // Print dates of the current week starting on Sunday
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "", endDate = "";

        startDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 6);
        endDate = df.format(calendar.getTime());

        System.out.println("Start Date = " + startDate);
        System.out.println("End Date = " + endDate);

        String dateString = startDate + " - " + endDate;
        dateTv.setText(dateString);
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

        if (adapterView.getSelectedItemPosition() == 1){
            getWeekExtremes();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
