package com.sslcs.smaple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sslcs.timeline.R;

/**
 * Created by CS on 2014/11/13 9:33.
 * TODO :
 */
public class TestFragment extends Fragment
{
    private String mName;

    public static TestFragment newInstance(String name)
    {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mName = getArguments().getString("name");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        StringBuilder sb = new StringBuilder();
        sb.append(mName).append("\n").append(mName).append("\n").append(mName).append("\n").append(mName).append("\n");
        tv.setText(sb);
        return view;
    }
}