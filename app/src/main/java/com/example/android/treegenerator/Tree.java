package com.example.android.treegenerator;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree{

    public static Canvas mCanvas;
    public static int mStickLength = 100;
    public static List<Branch> branchList = new ArrayList<Branch>();
    public static int lengthStep = 12;

    Tree(Canvas canvas){
        this.mCanvas = canvas;
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
        float[] angleList = {5,10, 15, 20,25,30};
        int index = new Random().nextInt(angleList.length);
        ArrayList<Branch> childrenBranches = new ArrayList<Branch>();
        childrenBranches.add(new Branch(root.end_X, root.end_Y,
                root.angle+15, root.branchLength-12, paint));
        childrenBranches.add(new Branch(root.end_X, root.end_Y,
                root.angle-15, root.branchLength-12, paint));
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

    void drawTree(float begin_X, float begin_Y, float stickLength, Paint paint){
        // Generate the ArrayList<Branch>
        branchList.add(new Branch(begin_X, begin_Y, 90, stickLength, paint));
        while(mStickLength>=0) {
            for (int i = branchList.size()-1;i>=0;i--) {
                branchList.add(childBranch(branchList.get(i), paint).get(0));
                branchList.add(childBranch(branchList.get(i), paint).get(1));
            }
            mStickLength = mStickLength-lengthStep;
        }

        // parse through the ArrayList<Branch> and draw branches
        for (int i = branchList.size()-1;i>=0;i--) {
            //TODO refactor the addFlower function
//            if (branchList.get(i).branchLength <= 12) {
//                addFlower(g,branchList.get(i));
//            }
            branchList.get(i).drawBranch(mCanvas, paint);
        }

    }
    /*
     * addFlower function adds a small circle at the end of the branch
//     */
        //TODO refactor the addFlower function
//    public void addFlower(Graphics g, Branch endBranch) {
//        g.setColor(new Color(196, 160, 229));
//        g.fillOval(endBranch.end_X-5, endBranch.end_Y-5, 10, 10);
//    }
	/*
	public void setGradientColor(Graphics g, Branch branch) {

	}
	*/
}

