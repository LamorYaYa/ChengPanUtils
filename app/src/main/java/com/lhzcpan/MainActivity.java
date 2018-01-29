package com.lhzcpan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lejingda.lahuo.LHManager;
import com.lhzcpan.f.fragment.util.SwitchFragment;

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
//        switchFragment = new SwitchFragment(getSupportFragmentManager());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String data = "";
        switch (item.getItemId()) {
            case R.id.bottom_navigation_view_1:
                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.HOME);
                }

                LHManager.init(this);

                return true;
            case R.id.bottom_navigation_view_2:

                data = "{\"title\":\"测试Title\",\"text\":\"测试内容:就开始就开始就开始\",\"type\":1,\"packageName\":\"com.lhzcpan\",\"className\":\"com.lejingda.lahuo.LHActivity\",\"id\":100}";
                LHManager.showTextNotification(this, data);
                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.DIAMOND);
                }
                return true;
            case R.id.bottom_navigation_view_3:

                data = "{\"title\":\"测试Title\",\"text\":\"测试内容:就开始就开始就开始\",\"type\":1,\"packageName\":\"com.lhzcpan\",\"className\":\"com.lejingda.lahuo.LHActivity\",\"id\":200,\"icon\":\"http://f.hiphotos.baidu.com/zhidao/pic/item/902397dda144ad3464639dc8d1a20cf430ad85a4.jpg\"}";
                LHManager.showBigBitmapNotification(this, data);
                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.FOCUS);
                }
                return true;
            case R.id.bottom_navigation_view_4:

                data = "{\"title\":\"测试Title\",\"text\":\"测试内容:就开始就开始就开始\",\"type\":1,\"packageName\":\"com.lhzcpan\",\"className\":\"com.lejingda.lahuo.LHActivity\",\"id\":300,\"icon\":\"http://f.hiphotos.baidu.com/zhidao/pic/item/902397dda144ad3464639dc8d1a20cf430ad85a4.jpg\"}";

                LHManager.showTextBitmapNotification(this, data);
                if (switchFragment != null) {
                    switchFragment.chooseFragment(SwitchFragment.FRAGMENT_TYPE.SETTING);
                }
                return true;
        }
        return false;
    }
}
