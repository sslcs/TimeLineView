package com.sslcs.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * Created by CS on 2014/11/24 16:15.
 * TODO :
 */
public class TimeLineFragment extends Fragment
{
    private ReplaceListener mListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (ReplaceListener) activity;
        }
        catch (ClassCastException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view)
    {
        System.out.println("TimeLineFragment.initLayout");
        TimeLineView timeLineView = (TimeLineView) view.findViewById(R.id.time_line);
        timeLineView.setOnItemClickListener(new TimeLineView.OnItemClickListener()
        {
            @Override
            public void onItemClick(int pos)
            {
                mListener.replace();
            }
        });
        QuickAdapter<String> adapter = new QuickAdapter<String>(getActivity(), R.layout.item_image)
        {
            @Override
            protected void convert(BaseAdapterHelper helper, String lesson)
            {

            }
        };
        for (int i = 0; i < 10; i++)
        {
            adapter.add("Lesson " + i);
        }
        timeLineView.setAdapter(adapter);
    }

    public interface ReplaceListener
    {
        public void replace();
    }
}
