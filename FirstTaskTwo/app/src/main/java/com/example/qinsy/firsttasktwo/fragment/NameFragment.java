package com.example.qinsy.firsttasktwo.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.qinsy.firsttasktwo.R;
import com.example.qinsy.firsttasktwo.activity.NumberActivity;
import com.example.qinsy.firsttasktwo.bean.Contacts;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by qinsy on 5/8/18.
 */

public class NameFragment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.name_frag,container,false);

        //fill data
        RecyclerView nameRecyclerView=view.findViewById(R.id.name_recycle_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        nameRecyclerView.setLayoutManager(layoutManager);
        ContactsAdapter adapter=new ContactsAdapter(getContacts());
        nameRecyclerView.setAdapter(adapter);
        return view;

    }

    private List<Contacts>getContacts(){
        List<Contacts>contactsList=new ArrayList<>();
        for (int i=1;i<=50;i++) {
            Contacts contact = new Contacts();
            contact.setName("This is news title" + i);
            contact.setNumber(getRandomLengthContent("this is news content" + i + ","));
            contactsList.add(contact);

        }
        return contactsList;
    }
    private  String getRandomLengthContent(String content){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.number_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

        private List<Contacts> mcontactsList;
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView nameText;
            public ViewHolder(View view){
                super(view);
                nameText=view.findViewById(R.id.news_title);
            }
        }

        public ContactsAdapter(List<Contacts> contacts){
            mcontactsList=contacts;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Contacts contacts=mcontactsList.get(holder.getAdapterPosition());
                    if(isTwoPane){

                       NumberFragment numberFragment=(NumberFragment)getFragmentManager().findFragmentById(R.id.number_fragment);
                        numberFragment.refresh(contacts.getName(),contacts.getNumber());
                    }else{
                        NumberActivity.actionStart(getActivity(),contacts.getName(),contacts.getNumber());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Contacts contacts=mcontactsList.get(position);
            holder.nameText.setText(contacts.getName());
        }

        @Override
        public int getItemCount() {
            return mcontactsList.size();
        }
    }


}
