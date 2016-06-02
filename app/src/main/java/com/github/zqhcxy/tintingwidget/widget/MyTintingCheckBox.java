package com.github.zqhcxy.tintingwidget.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.github.zqhcxy.tintingwidget.R;

/**
 * Created by zqh-pc on 2016/6/2.
 */
public class MyTintingCheckBox extends AppCompatCheckBox {

    private int tintingColor;

    public MyTintingCheckBox(Context context) {
        super(context);
//        init(context, null);
    }

    public MyTintingCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        handleTypedArray(context, attrs);
        updataView();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TintingCheckbox);
        tintingColor =
                typedArray.getColor(R.styleable.TintingCheckbox_tintingColor, Color.BLUE);
        typedArray.recycle();
    }
    /**
     * 代码new一个对象，进行配置属性
     *
     * @param color 要着的颜色
     */
    public void configCheckBox(int color) {
        tintingColor=color;
        updataView();
    }

    private void updataView(){
           setSupportButtonTintList(getCheckBoxColList());
    }

    private ColorStateList getCheckBoxColList() {
        int checkColor = getPressColor(tintingColor);
        int disableColor = getDisableColor(tintingColor);//Color.DKGRAY
        return getHcColorList(checkColor, tintingColor, disableColor);
    }

    /**
     * 获取按钮按下的颜色（改变明暗度）
     * @param ColKey 进行调整的颜色值
     * @return 返回经过0.9f明暗度调整后的颜色值
     */
    public static int getPressColor(int ColKey) {
        float[] v = new float[3];
        Color.colorToHSV(ColKey, v);
        v[2] = 0.9f;
        return Color.HSVToColor(v);
    }

    /**
     *获取按钮无效时的颜色（改变饱和度）
     * @param ColKey 进行调整的颜色值
     * @return 返回经过0.3f饱和度调整后的颜色值
     */
    public static int getDisableColor(int ColKey) {
        float[] s= new float[3];
        Color.colorToHSV(ColKey, s);
        s[1] = 0.3f;
        return Color.HSVToColor(s);
    }


    public final static int[][] mPressedEnableStates = new int[][] {
            new int[] { android.R.attr.state_pressed },
            new int[] { android.R.attr.state_enabled },
            new int[] { -android.R.attr.state_enabled } };
    /**
     * 颜色适配selector
     *
     * @param pressedTrueCol 按下的颜色
     * @param enableKeyId    可以使用的颜色id
     * @param disableCol     不能使用的颜色
     * @return
     */
    public static ColorStateList getHcColorList(int pressedTrueCol,
                                                int enableKeyId, int disableCol) {
        int[] colors = new int[3];
        colors[0] = pressedTrueCol;
        colors[1] = enableKeyId;
        colors[2] = disableCol;
        return new ColorStateList(mPressedEnableStates, colors);
    }
}
