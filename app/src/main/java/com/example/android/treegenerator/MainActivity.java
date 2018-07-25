package com.example.android.treegenerator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private Canvas mCanvas;
    private Paint mPaint;
    private Bitmap mBitmap;
    private ImageView mImageView;
    public static SeekBar mSeekBar;
    public TextView mTextView;
    public static Tree mTreeObj;

    public static int x = 1500;
    public static int y = 2000;

    private double angle = 0;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mTextView = (TextView) findViewById(R.id.textView);

        initGUI();
        new treeGeneration().execute();

        // attach the bitmap to the imageview
        mImageView.setImageBitmap(mBitmap);




        // set up for the seekbar
        // TODO Why doesn't the tree refresh?

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Get the value from the seekBar from user input and store it in a double angle.
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean fromUser) {
                angle = (double) seekBarValue;
                // initTree(angle);
                mTreeObj = new Tree(mCanvas, (float)15);
                mTreeObj.generateTree(x/2, y-y/6, 160, mPaint);
                mTreeObj.drawTree(mPaint);
            }

            // this function is not necessary.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "start seek bar", Toast.LENGTH_SHORT).show();
            }

            // this function is not necessary
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "end seek bar", Toast.LENGTH_SHORT).show();


            }
        });
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class treeGeneration extends AsyncTask<Void, Void, Tree>{

        @Override
        protected Tree doInBackground(Void... voids) {
            initTree();
            return mTreeObj;
        }

        @Override
        protected void onPostExecute(Tree tree) {
            super.onPostExecute(tree);
            tree.drawTree(mPaint);

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void initTree(){
        //draw objects
        mPaint.setColor(Color.RED);
        mTreeObj = new Tree(mCanvas, (float)15);
        mTreeObj.generateTree(x/2, y-y/6, 160, mPaint);
    }

    public void initGUI(){

        //Create bitmap and use that bitmap object as an argument for creating a canvas.

        mBitmap = Bitmap.createBitmap(x,y,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        // initiate Paint object, and set the background color, painting style
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mCanvas.drawPaint(mPaint);
    }

    //TODO how do I update the begin x and y coordinates as well?
    public void refreshTree(float selectedAngle, Tree treeObj){
        treeObj.branchList.get(0).updateBranchWithNewAngle(
                treeObj.branchList.get(0).begin_X,
                treeObj.branchList.get(0).begin_Y,
                selectedAngle);

        for (int i = 1;i <=treeObj.branchList.size()-1;i++){
            treeObj.branchList.get(i).angle = selectedAngle;
            treeObj.branchList.get(i).begin_X = treeObj.branchList.get(i).parent.end_X;
            treeObj.branchList.get(i).begin_Y = treeObj.branchList.get(i).parent.end_Y;

            treeObj.branchList.get(i).updateBranchWithNewAngle(
                            treeObj.branchList.get(i).begin_X,
                            treeObj.branchList.get(i).begin_Y,
                            treeObj.branchList.get(i).angle);
//            );

        }
    }


}
