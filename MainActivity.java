package com.example.himanshu.splitexpenses;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Expenses expenses;
    public static final String EXPENSES_LIST="expenses_list";
    AlertDialog.Builder builder;
public   ArrayList<Expenses> OriginalArrayList = new ArrayList<>();
    CharSequence[] tempArray={"","","","","","","","","",""};
static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add,btn_del,btn_show,btn_finish,btn_edit;

        btn_add= (Button)findViewById(R.id.button_Add);
        btn_del=(Button)findViewById(R.id.button_Delete);
        btn_edit=(Button)findViewById(R.id.button_Edit);
        btn_finish=(Button)findViewById(R.id.button_Finish);
        btn_show=(Button)findViewById(R.id.button_Show);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count=count+1;
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                i.putParcelableArrayListExtra(EXPENSES_LIST,OriginalArrayList);
                startActivity(i);
            }
        });
if(getIntent().getExtras()!=null) {
    OriginalArrayList = getIntent().getParcelableArrayListExtra(AddActivity.EXPENSE_KEY);
    OriginalArrayList = getIntent().getParcelableArrayListExtra(EditActivity.EXPENSE_KEY);
   // OriginalArrayList.add(new Expenses(expenses.expense_name,expenses.expense_category,expenses.expense_amount,expenses.expense_date));
    for(int i=0;i<OriginalArrayList.size();i++){
        Log.d("demo","In Main activity:"+OriginalArrayList.get(i).uri_path);
    }
}


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*for(int i=0;i<=OriginalArrayList.size();i++)
                {
                    tempArray[i]=OriginalArrayList.get(i).expense_name;
                }*/

                Intent i = new Intent(MainActivity.this,EditActivity.class);
                i.putParcelableArrayListExtra(EXPENSES_LIST,OriginalArrayList);
                startActivity(i);
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DeleteActivity.class);
                i.putParcelableArrayListExtra(EXPENSES_LIST,OriginalArrayList);
                startActivity(i);
            }
        });

btn_show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this,ShowActivity.class);
        i.putParcelableArrayListExtra(EXPENSES_LIST,OriginalArrayList);
        startActivity(i);
    }
});
    }
}
