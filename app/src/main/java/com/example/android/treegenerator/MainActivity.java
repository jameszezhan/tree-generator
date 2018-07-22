package com.example.android.treegenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint;
    private Bitmap mBitmap;
    private ImageView mImageView;

    private double angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeekBar mSeekBar;

        super.onCreate(savedInstanceState);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        // set up for the seekbar
        // TODO why can'r the program find the mSeekBar object
        // TODO how to refresh the tree image according to the angle from the seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Get the value from the seekBar from user input and store it in a double angle.
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean fromUser) {
                angle = seekBarValue;
            }

            // this function is not necessary.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "start seek bar", Toast.LENGTH_SHORT).show();

            }

            // this function is not necessary
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "end seek bar", Toast.LENGTH_SHORT).show();

            }
        });

        setContentView(R.layout.activity_main);
        //setContentView(new myClass(this));
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
