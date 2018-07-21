package com.example.android.treegenerator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myClass(this));
    }

    public class myClass extends View{
        myClass(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // get the width and height of
            int x = getWidth();
            System.out.print("int x = " + x);
            int y = getHeight();
            System.out.print("int y = " + y);

            // initiate Paint object, and set the background color, painting style
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);

            //draw objects
            paint.setColor(Color.RED);
            //canvas.drawLine(x, y, x/2, y/2, paint);
            Tree obj = new Tree(canvas);
            obj.drawTree(x/2, y-y/6, 160, paint);

        }
    }











}
