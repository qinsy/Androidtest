package com.example.qinsy.firsttasktwo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qinsy.firsttasktwo.R;
import com.example.qinsy.firsttasktwo.fragment.NumberFragment;

public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        String name=getIntent().getStringExtra("name");
        String number=getIntent().getStringExtra("number");
        NumberFragment numberFragment=(NumberFragment) getSupportFragmentManager().findFragmentById(R.id.number_fragment);
        numberFragment.refresh(name,number);
    }

    public static void actionStart(Context context, String name,String number){

        Intent intent=new Intent(context,NumberActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("number",number);
        context.startActivity(intent);

    }
}
