package com.lhzcpan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.lhzcpan.f.fragment.util.SwitchFragment;
import com.lhzcpan.util.SymmetricEncoder;

/**
 * @author master
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SwitchFragment switchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // 初始化 fragment
        switchFragment = new SwitchFragment(getSupportFragmentManager());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bottom_navigation_view_1:
                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.HOME);
                }

                String data = SymmetricEncoder.SHA256Encode("0f607264fc6318a92b9e13c65db7cd3c");
                Log.e("TAG", "加密结果 : " + data);
                return true;
            case R.id.bottom_navigation_view_2:


                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.DIAMOND);
                }
                return true;
            case R.id.bottom_navigation_view_3:


                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.FOCUS);
                }
                return true;
            case R.id.bottom_navigation_view_4:


                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.SETTING);
                }
                return true;
        }
        return false;
    }
}
