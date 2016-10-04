package com.example.himanshu.splitexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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




public class AddActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    int columnIndex;
    String picturePath;
    Uri uri;
    private int PICK_IMAGE_REQUEST = 1;
    public EditText editText_expenseName,editText_amount;
    public static final String EXPENSE_KEY="expense_key";
    static Handler handler;
    public static Message message;
    Spinner spinner_category;
    String bufferURI;
    AlertDialog.Builder builder;
    AlertDialog alert;
    Button btn_addExpense;
    ImageButton imageButton_date;
    ImageView imageView_receipt;
    public TextView textView_dateResult;
    static String date;
    static int flag;
  public static ArrayList<Expenses> ExpenseInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        if(getIntent().getExtras()!=null) {
            ExpenseInfo = getIntent().getParcelableArrayListExtra(MainActivity.EXPENSES_LIST);
        }
            flag=0;

        editText_amount=(EditText)findViewById(R.id.editText_amount);
        spinner_category=(Spinner)findViewById(R.id.spinner_category);
        editText_expenseName=(EditText)findViewById(R.id.editText_expenseName);
        btn_addExpense= (Button)findViewById(R.id.button_delete);
        imageButton_date=(ImageButton)findViewById(R.id.imageButton_date);
        imageView_receipt=(ImageView)findViewById(R.id.imageView_receipt);
        textView_dateResult=(TextView)findViewById(R.id.textView_dateResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter);



       //ExpenseInfo.add(new Expenses(editText_expenseName.getText().toString()));

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what == 100){
                    String tempdate = message.getData().getString("DATE");
                    textView_dateResult.setText(tempdate);
                }
                if(message.what==101)
                {
                    bufferURI = (String) message.getData().get("URI");
                    Log.d("demo","AA gaya kya URI yaha pe"+bufferURI);
                }


                return false;
            }
        });


imageView_receipt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        Log.d("demo","image view ke andar"+uri);

    }
});



        btn_addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editText_amount.getText().toString().equals("")) || editText_expenseName.getText().toString().equals("") || textView_dateResult.getText().toString().equals(""))
                {
                    Log.d("demo","False");
                    builder = new AlertDialog.Builder(AddActivity.this);
                    builder.setTitle("One of the Fileds is missing");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert = builder.create();
                    alert.show();

                }
                else {

                    Log.d("demo","In else");
                    ExpenseInfo.add(new Expenses(editText_expenseName.getText().toString(), spinner_category.getSelectedItemPosition() + "", editText_amount.getText().toString(), textView_dateResult.getText().toString(),bufferURI));
                    Expenses expenses = new Expenses(editText_expenseName.getText().toString(), spinner_category.getSelectedItem().toString(), editText_amount.getText().toString(), textView_dateResult.getText().toString(), bufferURI);
                    Intent i = new Intent(AddActivity.this, MainActivity.class);
                    i.putParcelableArrayListExtra(EXPENSE_KEY, ExpenseInfo);
                    startActivity(i);

                }
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

           uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));String[] projection = { MediaStore.Images.Media.DATA };

                String[] projection = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                cursor.moveToFirst();

                Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));
                columnIndex = cursor.getColumnIndex(projection[0]);
                picturePath = cursor.getString(columnIndex); // returns null

                Log.d("demo","the picture path is"+uri);


                imageView_receipt.setImageBitmap(bitmap);

                message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("URI",uri.toString());
                message.what = 101;
                message.setData(bundle);
                handler.sendMessage(message);
                cursor.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


