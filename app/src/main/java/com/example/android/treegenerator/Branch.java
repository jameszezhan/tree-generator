package com.example.android.treegenerator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Branch {

    public float begin_X;
    public float begin_Y;
    public float end_X;
    public float end_Y;
    public float branchLength;
    public float angle;

    public Paint mPaint;
    public boolean hasChildren=false;



    public void drawBranch(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawLine(begin_X, begin_Y, end_X, end_Y, paint);
    }


    // Constructor: Branch object takes into 5 arguments as param
    // @param begin_X the beginning X coordinates
    // @param begin_Y the beginning Y coordinates
    // @param angle the angle of the branch
    // @param branchLength the length of the branch
    // @param paint the Paint object needed to draw branch
    //
    // In the constructor of Branch object, end position coordinates(end_X,end_Y)
    // will the calculated automatically, and assigned to the variables.
    // var finished will be signed true upon initiating the Branch object, to avoid repeat painting of the same object.

    public Branch(float begin_X, float begin_Y, float angle, float branchLength, Paint paint) {
        this.begin_X = begin_X;
        this.begin_Y = begin_Y;
        this.angle = angle;
        this.branchLength = branchLength;
        this.end_X = (float)(Math.cos(Math.toRadians(angle))*branchLength)+begin_X;
        this.end_Y = begin_Y-(float)(Math.sin(Math.toRadians(angle))*branchLength);
        this.mPaint = paint;
    }

}
