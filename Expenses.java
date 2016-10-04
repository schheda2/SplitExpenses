package com.example.himanshu.splitexpenses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Himanshu on 9/14/2016.
 */
public class Expenses implements Parcelable {
        String expense_name, expense_category, expense_amount, expense_date;
    String uri_path;

    public Expenses(String expense_name, String expense_category, String expense_amount, String expense_date, String uri_path)
    {
        super();
        this.expense_name=expense_name;
        this.expense_category=expense_category;
        this.expense_amount=expense_amount;
        this.expense_date=expense_date;
        this.uri_path=uri_path;
        //this.expense_uri=expense_uri;
    }

    public static final Creator<Expenses> CREATOR = new Creator<Expenses>() {
        @Override
        public Expenses createFromParcel(Parcel in) {
            return new Expenses(in);
        }

        @Override
        public Expenses[] newArray(int size) {
            return new Expenses[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expense_name);
        dest.writeString(expense_category);
        dest.writeString(expense_amount);
        dest.writeString(expense_date);
        dest.writeString(uri_path);
        //dest.writeString(expense_uri);
    }

    public Expenses(Parcel in)
    {
        this.expense_name=in.readString();
        this.expense_category=in.readString();
        this.expense_amount=in.readString();
        this.expense_date=in.readString();
        this.uri_path=in.readString();
        //this.expense_uri=in.readString();
    }
}

