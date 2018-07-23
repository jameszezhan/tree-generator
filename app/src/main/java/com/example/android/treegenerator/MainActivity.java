package com.example.android.treegenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint;
    private Bitmap mBitmap;
    private ImageView mImageView;
    public static SeekBar mSeekBar;
    public Button mButton;


    private double angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mImageView = (ImageView) findViewById(R.id.imageView);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        // below is only for testing duirng dev
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // get the width and height of the imageView
                int x = mImageView.getWidth();
                int y = mImageView.getHeight();

                //Create bitmap and use that bitmap object as an argument for creating a canvas.
                Bitmap imageBitmap = Bitmap.createBitmap(x,y,Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(imageBitmap);

                // initiate Paint object, and set the background color, painting style
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                mCanvas.drawPaint(paint);

                //draw objects
                paint.setColor(Color.RED);
                Tree obj = new Tree(mCanvas, (float)15);
                obj.drawTree(x/2, y-y/6, 160, paint);

                // attach the bitmap to the imageview
                mImageView.setImageBitmap(imageBitmap);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////




        // set up for the seekbar
        // TODO how to refresh the tree image according to the angle from the seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Get the value from the seekBar from user input and store it in a double angle.
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean fromUser) {
                angle = (float) seekBarValue;
                int x = mImageView.getWidth();
                int y = mImageView.getHeight();

                //Create bitmap and use that bitmap object as an argument for creating a canvas.
                Bitmap imageBitmap = Bitmap.createBitmap(x,y,Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(imageBitmap);

                // initiate Paint object, and set the background color, painting style
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                mCanvas.drawPaint(paint);

                //draw objects
                paint.setColor(Color.RED);
                Tree obj = new Tree(mCanvas, (float)angle);
                obj.drawTree(x/2, y-y/6, 160, paint);

                // attach the bitmap to the imageview
                mImageView.setImageBitmap(imageBitmap);
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
    }
}
