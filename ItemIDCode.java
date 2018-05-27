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

//THIS IS A POTENTIAL MATCHING CODE RESULT FOUND IN SEARCHABLE FILE
public class ItemIDCode implements Comparable<ItemIDCode> {

    private String code;
    
    
    
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

    
    
    

    public double getTestTotal() {
        return testTotal;
    }

    public void setTestTotal(double testTotal) {
        this.testTotal = testTotal;
    }
    
    //indicates this object should be measured by testTotal
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
