package com.example.android.treegenerator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    private TreeCustomView mTreeCustomView;
    public static Paint mPaint;
    public static SeekBar mSeekBar;
    public TextView mTextView;
    public static Tree mTreeObj;
    public static Button button1, button2, button3, button4;
    public static ArrayList<Branch> treeArray;
    public Toast mToast;

    //public static int x = ;
    //public static int y = ;

    private double angle = 15;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVars();
        initGUI();
        initTree((float)angle);

        //TODO: find out how to update the treeCustomView after clicking the buttons.
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = 10;
                mTreeCustomView.invalidate();;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = 15;
                mTreeCustomView.invalidate();;

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = 20;
                initTree((float)angle);
                mTreeCustomView.invalidate();;

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = 25;
                mTreeCustomView.invalidate();;

            }
        });
        /*
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // set up for the seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Get the value from the seekBar from user input and store it in a double angle.
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean fromUser) {
                angle = (double) seekBarValue;
            }

            // this function is not necessary.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "start seek bar", Toast.LENGTH_SHORT).show();
            }

            // this function is not necessary
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPaint.setColor(Color.BLUE);
                mImageView.invalidate();

            }
        });*/
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class treeGeneration extends AsyncTask<Void, Void, Tree>{

        @Override
        protected Tree doInBackground(Void... voids) {
            initTree((float)angle);
            return mTreeObj;
        }

        @Override
        protected void onPostExecute(Tree tree) {
            super.onPostExecute(tree);
            //tree.drawTree(mPaint);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void initTree(float treeAngle){
        //draw objects
        mPaint.setColor(Color.RED);
        mTreeObj = new Tree((float)treeAngle);
        mTreeObj.generateTree(1500/2, 2000-2000/6, 160, mPaint);
    }

    public void initGUI(){

//        // initiate Paint object, and set the background color, painting style
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public void initVars(){
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        mTreeCustomView = (TreeCustomView)findViewById(R.id.treeView);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
