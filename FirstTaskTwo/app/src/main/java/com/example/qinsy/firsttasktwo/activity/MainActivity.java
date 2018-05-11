package com.example.qinsy.firsttasktwo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qinsy.firsttasktwo.R;
import com.example.qinsy.firsttasktwo.fragment.NameFragment;
import com.example.qinsy.firsttasktwo.service.MusicService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ArrayAdapter<String>adapter;
    List<String>contactsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button)findViewById(R.id.play_service);
        Button stop = (Button)findViewById(R.id.stop_service);
        Button forceOffline=findViewById(R.id.force_offline);

        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.byebye");
                sendBroadcast(intent);
            }
        });
        Button.OnClickListener listener = new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(),MusicService.class);
                switch(v.getId()){
                    case R.id.play_service: startService(intent);break;
                    case R.id.stop_service: stopService(intent);break;
                }
            }
        };

        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        //  read contacts
      //  ListView contactsView=findViewById(R.id.contacts_view);
      //  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
       // contactsView.setAdapter(adapter);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContacts();
        }
    }

    public void readContacts(){
        Cursor cursor=null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {

                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();//fresh
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {if (cursor!=null){
            cursor.close();
        }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }

    }
    }

