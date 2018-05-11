package com.example.qinsy.firsttasktwo.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.qinsy.firsttasktwo.R;

/**
 * Created by qinsy on 5/8/18.
 */

public class NumberFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.number_frag, container, false);
        return view;
    }

    //show title and content
    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView nameText = view.findViewById(R.id.name);
        TextView numberText = view.findViewById(R.id.number);
        nameText.setText(newsTitle);
        numberText.setText(newsContent);

    }
}