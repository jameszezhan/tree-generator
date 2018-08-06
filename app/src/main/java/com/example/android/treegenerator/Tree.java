package com.example.android.treegenerator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree{

    public static int mStickLength = 150;
    public static List<Branch> branchList = new ArrayList<Branch>();
    public static int lengthStep = 12;
    public static float mAngleStep;

    Tree(float angle){
        this.mAngleStep = angle;
    }


    //abstract void drawTree();

    /*
     * this is legacy function.
     * takes into the beginning coordinates of a branch, the angle, and the length.
     * Calculates the ending position coordinates
     * But I managed to put this function in the constructor of Branch object.
     */
    public static ArrayList<Integer> traverseTree(int end_X, int end_Y, double angle, int branchLength) {
        //stickLength = Math.sqrt(Math.pow((end_Y-begin_Y), 2) + Math.pow(end_X-begin_X, 2));
        branchLength = branchLength-10;
        int[] angleList = {5,10, 15, 20,25,30};
        int index = new Random().nextInt(angleList.length);
        int xsize_A = (int)(Math.cos(Math.toRadians(angle-10))*(branchLength));
        int xsize_B = (int)(Math.cos(Math.toRadians(angle+10))*(branchLength));
        int ysize = (int)(Math.sin(Math.toRadians(angle-10))*(branchLength));
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        resultList.add(ysize+end_Y);
        resultList.add(xsize_A+end_X);
        resultList.add(xsize_B+end_X);
        return resultList;
    }

    /*
     * childBranch function takes into a Branch object and a Paint object as parameters.
     * It calculates the new begin_x and begin_y for the children branches, and initiate them.
     *
     * @param root the parent of which the branches are being created in this function
     * @param paint the paint object which the Branch constructor needs for creating a Branch object.
     *
     */
    public static ArrayList<Branch> childBranch(Branch root, Paint paint) {
        //float[] angleList = {5,10, 15, 20,21,22,23};
        ArrayList<Branch> childrenBranches = new ArrayList<Branch>();
       // int indexplus = new Random().nextInt(angleList.length);
        childrenBranches.add(new Branch(root.end_X, root.end_Y,
                root.angle+mAngleStep, root.branchLength-12, paint, root));
        //int indexminus = new Random().nextInt(angleList.length);
        childrenBranches.add(new Branch(root.end_X, root.end_Y,
                root.angle-mAngleStep, root.branchLength-12, paint, root));
        return childrenBranches;
    }

    /*
    drawTree() function takes into 4 paraments:
    @param begin_X the x coordinate of the root of the tree object.
    @param begin_Y the y coordinate of the root of the tree object.
    @param stickLength the length of the root of the tree object.
    @param paint the Paint object which the Branch constructor needs for creating a Branch object

    This function comprises of two parts:
     the first part is generating all the Branch objects and store them in an ArrayList<Branch>;
     the second part is parsing through the ArrayList<Branch> generated earlier, and call drawBranch
        function in the Branch object.

     */

    void generateTree(float begin_X, float begin_Y, float stickLength, Paint paint){
        // Generate the ArrayList<Branch>
        branchList.add(new Branch(begin_X, begin_Y, 90, stickLength, paint, null));
        while(mStickLength>=0) {
            for (int i = branchList.size()-1;i>=0;i--) {
                if (branchList.get(i).hasChildren==false) {
                    branchList.add(childBranch(branchList.get(i), paint).get(0));
                    branchList.add(childBranch(branchList.get(i), paint).get(1));
                    branchList.get(i).hasChildren = true;
                }
            }
            mStickLength = mStickLength-lengthStep;
        }
    }




    void drawTree(Paint paint, Canvas canvas){
        // parse through the ArrayList<Branch> and draw branches
        for (int i = branchList.size()-1;i>=0;i--) {
            branchList.get(i).drawBranch(canvas, paint);
            if (branchList.get(i).branchLength <= 130 && i%2==0) {
                addFlower(canvas,branchList.get(i), paint);
            }
        }

    }
    /*
     * addFlower function adds a small circle at the end of the branch
//     */
    public void addFlower(Canvas canvas, Branch endBranch, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(endBranch.end_X, endBranch.end_Y, 3 , paint);
    }
	/*
	public void setGradientColor(Graphics g, Branch branch) {

	}
	*/
}

