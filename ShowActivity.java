package com.example.himanshu.splitexpenses;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
TextView textView_name,textView_amount,textView_category,textView_date;
    ImageView imageView_receipt;
ImageButton imageButton_first,imageButton_previous,imageButton_next,imageButton_last;
    Spinner spinner_category;
int count=0;
    Bitmap bitmap;
    Uri uri_final;
    int sp=0;

    static ArrayList<Expenses> FinalShow= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textView_name= (TextView)findViewById(R.id.textView_nameResult);
        textView_category=(TextView)findViewById( R.id.textView_categoryResult);
        textView_amount=(TextView)findViewById(R.id.textView_amountResult);
        textView_date=(TextView)findViewById(R.id.textView_dateResult);
        imageView_receipt=(ImageView)findViewById(R.id.imageView);
        textView_category= (TextView) findViewById(R.id.textView_categoryResult);

        if(getIntent().getExtras()!=null) {
            FinalShow = getIntent().getParcelableArrayListExtra(MainActivity.EXPENSES_LIST);
        }

        sp=Integer.parseInt(FinalShow.get(count).expense_category);
        if(sp==0){
            textView_category.setText("Groceries");
        }
        else if(sp==1){
            textView_category.setText("Invoice");
        }
        else if(sp==2){
            textView_category.setText("Transportation");
        }
        else if(sp==3){
            textView_category.setText("Shopping");
        }
        else if(sp==4){
            textView_category.setText("Rent");

        }
        else if(sp==5){
            textView_category.setText("Trips");
        }
        else if(sp==6){
            textView_category.setText("Utilities");
        }
        else if(sp==7){
            textView_category.setText("Others");
        }
else
        {}
        imageButton_previous=(ImageButton)findViewById(R.id.imageButton_previous);
        imageButton_next=(ImageButton)findViewById(R.id.imageButton_next);
        imageButton_first=(ImageButton)findViewById(R.id.imageButton_first);
        imageButton_last=(ImageButton)findViewById(R.id.imageButton_last);
        textView_amount.setText(FinalShow.get(0).expense_amount);
        textView_name.setText(FinalShow.get(0).expense_name);
        textView_date.setText(FinalShow.get(0).expense_date);

        uri_final= Uri.parse(FinalShow.get(0).uri_path);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
            imageView_receipt.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageButton_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                textView_amount.setText(FinalShow.get(0).expense_amount);
                textView_name.setText(FinalShow.get(0).expense_name);
                textView_date.setText(FinalShow.get(0).expense_date);
                sp=Integer.parseInt(FinalShow.get(count).expense_category);
                if(sp==0){
                    textView_category.setText("Groceries");
                }
                else if(sp==1){
                    textView_category.setText("Invoice");
                }
                else if(sp==2){
                    textView_category.setText("Transportation");
                }
                else if(sp==3){
                    textView_category.setText("Shopping");
                }
                else if(sp==4){
                    textView_category.setText("Rent");

                }
                else if(sp==5){
                    textView_category.setText("Trips");
                }
                else if(sp==6){
                    textView_category.setText("Utilities");
                }
                else if(sp==7){
                    textView_category.setText("Others");
                }
                //imageView_receipt.setImageBitmap(bitmap);
                uri_final= Uri.parse(FinalShow.get(0).uri_path);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
                    imageView_receipt.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        imageButton_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count<=0){
                    Toast.makeText(getApplicationContext(),"No previous value",Toast.LENGTH_LONG).show();
                }
                else
                {
                    count=count-1;
                    textView_amount.setText(FinalShow.get(count).expense_amount);
                    textView_name.setText(FinalShow.get(count).expense_name);
                    textView_date.setText(FinalShow.get(count).expense_date);
                    sp=Integer.parseInt(FinalShow.get(count).expense_category);
                    if(sp==0){
                        textView_category.setText("Groceries");
                    }
                    else if(sp==1){
                        textView_category.setText("Invoice");
                    }
                    else if(sp==2){
                        textView_category.setText("Transportation");
                    }
                    else if(sp==3){
                        textView_category.setText("Shopping");
                    }
                    else if(sp==4){
                        textView_category.setText("Rent");

                    }
                    else if(sp==5){
                        textView_category.setText("Trips");
                    }
                    else if(sp==6){
                        textView_category.setText("Utilities");
                    }
                    else if(sp==7){
                        textView_category.setText("Others");
                    }
                    uri_final= Uri.parse(FinalShow.get(count).uri_path);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
                        imageView_receipt.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });



        imageButton_last.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count=FinalShow.size()-1;
                textView_amount.setText(FinalShow.get(count).expense_amount);
                textView_name.setText(FinalShow.get(count).expense_name);
                textView_date.setText(FinalShow.get(count).expense_date);
                sp=Integer.parseInt(FinalShow.get(count).expense_category);
                if(sp==0){
                    textView_category.setText("Groceries");
                }
                else if(sp==1){
                    textView_category.setText("Invoice");
                }
                else if(sp==2){
                    textView_category.setText("Transportation");
                }
                else if(sp==3){
                    textView_category.setText("Shopping");
                }
                else if(sp==4){
                    textView_category.setText("Rent");

                }
                else if(sp==5){
                    textView_category.setText("Trips");
                }
                else if(sp==6){
                    textView_category.setText("Utilities");
                }
                else if(sp==7){
                    textView_category.setText("Others");
                }
                uri_final= Uri.parse(FinalShow.get(count).uri_path);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
                    imageView_receipt.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
imageButton_next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(count>=FinalShow.size()-1){
            Toast.makeText(getApplicationContext(),"No next value",Toast.LENGTH_LONG).show();
        }
        else {
            count=count+1;
            textView_amount.setText(FinalShow.get(count).expense_amount);
            textView_name.setText(FinalShow.get(count).expense_name);
            textView_date.setText(FinalShow.get(count).expense_date);
            sp=Integer.parseInt(FinalShow.get(count).expense_category);
            uri_final= Uri.parse(FinalShow.get(count).uri_path);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri_final);
                imageView_receipt.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(sp==0){
                textView_category.setText("Groceries");
            }
            else if(sp==1){
                textView_category.setText("Invoice");
            }
            else if(sp==2){
                textView_category.setText("Transportation");
            }
            else if(sp==3){
                textView_category.setText("Shopping");
            }
            else if(sp==4){
                textView_category.setText("Rent");

            }
            else if(sp==5){
                textView_category.setText("Trips");
            }
            else if(sp==6){
                textView_category.setText("Utilities");
            }
            else if(sp==7){
                textView_category.setText("Others");
            }
        }
    }
});
Button btn_finish= (Button)findViewById(R.id.button_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                moveTaskToBack(true);
            }
        });

    }
}
