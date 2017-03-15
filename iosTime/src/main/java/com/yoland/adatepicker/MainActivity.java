package com.yoland.adatepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 创建者：admin
 * <p>时间：2017/3/14 14:11
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void but1(View view) {
        startActivity(new Intent(this,IOSTimeActivity.class));
    }

    public void but2(View view) {
        startActivity(new Intent(this,BanDuCalendarActivity.class));
    }
}
