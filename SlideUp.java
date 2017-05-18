package com.android.magic.View;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.magic.R;

/**
 * Created by kanghui on 2017/5/15.
 */

public class SlideUp extends LinearLayout implements View.OnTouchListener {


    private Context Mcontext;
    private int rawX = 0;
    private int rawY = 0;
    private int lastX = 0;
    private int lastY = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    Listener listener;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = (int) (motionEvent.getRawY());
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                rawY = (int) motionEvent.getRawY();
                offsetY += rawY - lastY;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if (offsetY < -150) {
            offsetY = -150;
        }
        if (offsetY > -10) {
            offsetY = -10;
        }
        requestLayout();

        return false;


    }

    public interface Listener {
        void getCount(String y, String height);
    }

    public SlideUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Mcontext = context;
        initView();
    }

    public SlideUp(Context context, AttributeSet attrs) {
        super(context, attrs);
        Mcontext = context;
        initView();
    }

    public SlideUp(Context context) {
        super(context);
        Mcontext = context;
        initView();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void initView() {


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600, 600);
        params.setMargins(10, 10, 10, 50);
        for (int i = 0; i <= 1; i++) {
            LinearLayout view = new LinearLayout(Mcontext);
            params.gravity = Gravity.CENTER_VERTICAL;
            view.setLayoutParams(params);
            view.setOrientation(LinearLayout.HORIZONTAL);
            GridView gridView = new GridView(Mcontext);
            gridView.setAdapter(new Adpter(Mcontext));
            if (i == 1) {
                view.setTag("red");

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(40, 10, 0, 0);
                gridView.setHorizontalSpacing(20);
                gridView.setGravity(View.TEXT_ALIGNMENT_CENTER);
                gridView.setVerticalSpacing(30);
                gridView.setLayoutParams(layoutParams);
                gridView.setColumnWidth(500);
                gridView.setNumColumns(3);
                gridView.setOnTouchListener(this);


                view.addView(gridView);
                view.setBackground(getResources().getDrawable(R.drawable.conker));
                view.setClickable(true);
                view.setOnTouchListener(this);
            } else {


                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 200, 0, 0);
                gridView.setHorizontalSpacing(10);
                gridView.setGravity(View.TEXT_ALIGNMENT_CENTER);
                gridView.setVerticalSpacing(10);
                gridView.setLayoutParams(layoutParams);
                gridView.setColumnWidth(400);

                gridView.setNumColumns(5);
                gridView.setOnTouchListener(this);
                view.addView(gridView);
                view.setBackground(getResources().getDrawable(R.drawable.conker));

            }
            addView(view);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int viewCount = getChildCount();

        for (int count = 0; count < viewCount; count++) {

            View child = getChildAt(count);

            if (child.getTag() == "red") {
                child.layout(l + 20,
                        t + 250 + offsetY,
                        r,
                        b - 20 + offsetY);

            } else {

                if (listener != null) {
                    listener.getCount((float) (0.5 + (float) (Math.abs(offsetY) / 200)) + "", "" + Math.abs(offsetY) / 200);
                }
                child.setAlpha((float) (0.5 + (float) Math.abs(offsetY) / 200));
                child.layout(l + 50 + offsetY / 5, t + 250, r - 30 - offsetY / 5, b);

            }

        }


    }


}
