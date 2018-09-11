package com.shashank.expensemanager.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shashank.expensemanager.R;
import com.shashank.expensemanager.utils.Constants;

public class AddExpenseActivity extends AppCompatActivity {

    TextInputEditText amountEditText;
    TextInputEditText descriptionEditText;
    TextView dateTextView;
    LinearLayout dateLinearLayout;

    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        amountEditText=findViewById(R.id.amountTextInputEditText);
        descriptionEditText=findViewById(R.id.descriptionTextInputEditText);
        dateTextView=findViewById(R.id.dateTextView);
        dateLinearLayout=findViewById(R.id.dateLinerLayout);
        //First task here is to determine from where this activity is launched from the 4 possibilities

        Intent intent=getIntent();

        if(intent.getStringExtra("from").equals(Constants.addIncomeString)){
            setTitle("Add Income");
        }
        else if(intent.getStringExtra("from").equals(Constants.addExpenseString)){
            setTitle("Add Expense");
        }



        dateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

    }

    public void showDatePicker(){

      //  datePickerDialog=new DatePickerDialog(this,this,)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_expense_activty_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.saveButton:
                // TODO: 10-09-2018 1.Retrieve and Save data to database and also update the recycler view
                finish();


        }
        return  true;
    }
}
