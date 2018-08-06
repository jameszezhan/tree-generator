package com.example.android.treegenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TreeCustomView extends View {


    public TreeCustomView(Context context) {
        super(context);
        init(null);
    }

    public TreeCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TreeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TreeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set){
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(this.getWidth(), this.getHeight());
//
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Canvas temp = //Canvas(MainActivity.mBitmap);
        //Bitmap testObj = MainActivity.mBitmap;
        canvas.drawColor(Color.BLACK);
        MainActivity.mTreeObj.drawTree(MainActivity.mPaint, canvas);
        //canvas.drawBitmap(MainActivity.mBitmap, canvas.getWidth()/2,canvas.getHeight()/2, paintObject );
    }



}
