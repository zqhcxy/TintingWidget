package com.github.zqhcxy.tintingwidget;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.github.zqhcxy.tintingwidget.widget.MyTintingCheckBox;

/**
 * 自定义控件着色
 *
 * @author zqhcxy
 */
public class MainActivity extends AppCompatActivity {
    private LinearLayout mylinear_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //代码实现着色
        mylinear_ly = (LinearLayout) findViewById(R.id.mylinear_ly);
        MyTintingCheckBox myTintingCheckBox = new MyTintingCheckBox(this);
        myTintingCheckBox.configCheckBox(Color.GREEN);
        mylinear_ly.addView(myTintingCheckBox);
    }
}
