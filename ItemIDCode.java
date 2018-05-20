/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemidchecker;

import java.util.Comparator;

/**
 *
 * @author maxeg
 */
public class ItemIDCode implements Comparable<ItemIDCode> {

    private String code;
    
    
    private int test1Score;//not used
    private int test2Score;//not used
    private int test3Score;//not used
    private int test4Score;//not used
    
    
    private double testTotal;

    private int rowNum;
    private int colNum;
    private String workbookName;

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public void setWorkbookName(String workbookName) {
        this.workbookName = workbookName;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public String getWorkbookName() {
        return workbookName;
    }

    //constructor
    public ItemIDCode(String code, double testTotal, int rn, int cn) {
        this.testTotal = testTotal;
        this.code = code;
        this.rowNum = rn;
        this.colNum = cn;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTest1Score() {
        return test1Score;
    }

    public void setTest1Score(int test1Score) {
        this.test1Score = test1Score;
    }

    public int getTest2Score() {
        return test2Score;
    }

    public void setTest2Score(int test2Score) {
        this.test2Score = test2Score;
    }

    public int getTest3Score() {
        return test3Score;
    }

    public void setTest3Score(int test3Score) {
        this.test3Score = test3Score;
    }

    public int getTest4Score() {
        return test4Score;
    }

    public void setTest4Score(int test4Score) {
        this.test4Score = test4Score;
    }

    public double getTestTotal() {
        return testTotal;
    }

    public void setTestTotal(double testTotal) {
        this.testTotal = testTotal;
    }
    
        @Override
    public int compareTo(ItemIDCode b) {

        if (this.getTestTotal() > b.getTestTotal()) {
            return -1;

        } else if (this.getTestTotal() < b.getTestTotal()) {
            return 1;
        } else {

            return 0;

        }

    }
}
