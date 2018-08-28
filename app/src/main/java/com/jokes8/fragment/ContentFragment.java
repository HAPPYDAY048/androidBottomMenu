package com.jokes8.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jokes8.activity.R;


/**
 * Created by HAPPYDAY048 on 2018/8/27.
 */
public class ContentFragment extends Fragment {
    private String mTitle;

    public static ContentFragment getInstance(String title) {
        ContentFragment sf = new ContentFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, null);
        TextView v_content = (TextView) v.findViewById(R.id.tv_content);
        v_content.setText(mTitle);

        return v;
    }
}