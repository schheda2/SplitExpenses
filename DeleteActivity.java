package com.example.himanshu.splitexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class DeleteActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    AlertDialog alert;
    CharSequence[] ExpensesNames_list= {"","","","","","",""};
    EditText editText_expenseName, editText_expenseAmount;
    public static final String EXPENSE_KEY="expense_key";
    Spinner spinner_category;
    TextView textView_dateResult;
    static Handler handler;
    public static Message message;
    static String date;
    static int flag;
    Uri uri_final;
    int temp;
    ImageButton imageButton_date;
    ImageView imageView_receipt;

    Button button_selectExpense,button_delete, button_cancel;
    public static ArrayList<Expenses> OriginalArrayList = new ArrayList<>();
    int sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        if(getIntent().getExtras()!=null) {
            OriginalArrayList = getIntent().getParcelableArrayListExtra(MainActivity.EXPENSES_LIST);
        }

        editText_expenseName = (EditText)findViewById(R.id.editText_expenseName);
        editText_expenseAmount=(EditText)findViewById(R.id.editText_amount);
        spinner_category=(Spinner)findViewById(R.id.spinner_category);
        button_delete=(Button)findViewById(R.id.button_delete);
        button_cancel=(Button)findViewById(R.id.button_cancel);
        imageView_receipt=(ImageView)findViewById(R.id.imageView_receipt);
editText_expenseName.setEnabled(false);
        editText_expenseAmount.setEnabled(false);
        spinner_category.setEnabled(false);
//       imageButton_date.setClickable(false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter);

        textView_dateResult = (TextView)findViewById(R.id.textView_dateResult);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what == 100){
                    String tempdate = message.getData().getString("DATE");
                    textView_dateResult.setText(tempdate);
                }


                return false;
            }
        });

        for(int i=0;i<OriginalArrayList.size();i++)
        {
            ExpensesNames_list[i]=OriginalArrayList.get(i).expense_name;
   /* Log.d("demo","Expense names   "+OriginalArrayList.get(i).expense_name);
    Log.d("demo","Expense category   "+OriginalArrayList.get(i).expense_category);
    Log.d("demo","Expese amounts   "+OriginalArrayList.get(i).expense_amount);
    Log.d("demo","Expense dates   "+OriginalArrayList.get(i).expense_date);*/
        }

        button_selectExpense = (Button)findViewById(R.id.button_Editexpense);
        button_selectExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(DeleteActivity.this);
                builder.setTitle("Pick an Expense");
                builder.setItems(ExpensesNames_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       /*) Log.d("demo","List of names   "+OriginalArrayList.get(which).expense_category.toString());
                        Log.d("demo","Expense names   "+OriginalArrayList.get(which).expense_name.toString());
                        Log.d("demo","Expense category   "+OriginalArrayList.get(which).expense_category.toString());
                        Log.d("demo","Expense amounts   "+OriginalArrayList.get(which).expense_amount.toString());
                        Log.d("demo","Expense dates   "+OriginalArrayList.get(which).expense_date.toString()); */
                        temp=which;

                        uri_final= Uri.parse(OriginalArrayList.get(which).uri_path);
                        spinner_category.setSelection(sp);

                        sp=Integer.parseInt(OriginalArrayList.get(which).expense_category);
                        editText_expenseName.setText(OriginalArrayList.get(which).expense_name.toString());
                        editText_expenseAmount.setText(OriginalArrayList.get(which).expense_amount.toString());
                        textView_dateResult.setText(OriginalArrayList.get(which).expense_date.toString());
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
                            imageView_receipt.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });
                alert = builder.create();
                alert.show();
            }
        });

button_delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        OriginalArrayList.remove(temp);
        //OriginalArrayList.add(new Expenses(editText_expenseName.getText().toString(),spinner_category.getSelectedItemPosition()+"",editText_expenseAmount.getText().toString(),textView_dateResult.getText().toString()));
        //Expenses expenses = new Expenses(editText_expenseName.getText().toString(),  spinner_category.getSelectedItem().toString(),editText_expenseAmount.getText().toString(), textView_dateResult.getText().toString());
        Intent i = new Intent(DeleteActivity.this,MainActivity.class);
        i.putParcelableArrayListExtra(EXPENSE_KEY, OriginalArrayList);
        startActivity(i);
    }
});

button_cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        //Intent i = new  Intent(DeleteActivity.this,MainActivity.class);
        //startActivity(i);
        finish();

    }
});


    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        AddActivity a=new AddActivity();
        @Override

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker

            date="";
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            date=(month+1)+"/"+day+"/"+year;
            //a.textView_dateResult.setText(date);
            flag=1;
            Log.d("demo",date);
            message = new Message();
            Bundle data = new Bundle();
            data.putString("DATE",date);
            message.what = 100;
            message.setData(data);
            handler.sendMessage(message);
        }



    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }




}
