package com.sslcs.smaple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.sslcs.timeline.R;

public class MainActivity extends FragmentActivity implements TimeLineFragment.ReplaceListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new TimeLineFragment());
    }

    private void addFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment,"TL");
        ft.commit();
    }

    @Override
    public void replace()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(fm.findFragmentByTag("TL"));
        ft.replace(R.id.container, TestFragment.newInstance("TEST"));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("TL");
        if(fragment != null)
        {
            System.out.println("MainActivity.onBackPressed "+ fragment.isVisible());
        }

        super.onBackPressed();
    }
}
