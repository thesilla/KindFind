package itemidchecker;

//MASTER TODO:
//decide what input will be (notepad txt file, excel, ect.....maybe all? pick one and add more later
//decide how results will be displayed (excel file
//1


//Completed Scores:
//1. Edit distance
//2. Total Max total characters from original string in a row/or not in a row within VendorID
//Tests under construction:
//1. 
import java.lang.Math;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;//for xls
import org.apache.poi.xssf.usermodel.XSSFWorkbook;//for xlsx
import org.apache.poi.hssf.util.CellReference;
import java.io.*;
import java.net.URL;
import javax.swing.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author mgillman
 */
public class ItemIDChecker {

    
    /*
    
    @FXML
    private Button run;
    @FXML
    private Button inputUp;
    @FXML
    private Button inputDown;
    @FXML
    private Button resultsDown;
    @FXML
    private Button resultsUp;
    @FXML
    private TextField inputDisplay;
    @FXML
    private TextField resultsDisplay;
    @FXML
    private ListView<?> lvInput;
    @FXML
    private ListView<?> lvDesc;
    @FXML
    private ListView<?> lvResults;
    
    */
    
    
    
    //Key Data points:
    int numCharNotInARow;
    int maxCount;
    File file;
    String spaces;
    
    int numResults;
    
    int currentRow;
    int currentColumn;

    int differenceInCounts = 0;

    //Other Variables
    Boolean IDsEqual = false;
    String testStringP21;
    String testStringVendor;
    JFileChooser jfc;
    String vendorFileName;
    double totalScore;

    int diff;
    int inARowCount;

    char[] VendorStringToCharArray;
    int vendorStringLength;
    char[] P21StringToCharArray;
    int P21StringLength;
    
    FileReader fr;
    
    int SubCheckCharP21;
    int SubCheckCharVendor;

    //arrays correspond to each ascii code - fill each with 0s
    int[] P21Ascii;
    int[] VendorAscii;
    int[] diffArray;
    FileInputStream fis;
    Workbook wb;

    
    ArrayList<ItemIDCode> codesList = new ArrayList<ItemIDCode>(); //maybe need to determine number of occupied cells first then figure out max

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }
    
    
    
    public ItemIDChecker(String P21ID)  throws IOException  {

        P21Ascii = new int[256]; //95 total characters needing checking
        VendorAscii = new int[256]; //95 total characters needing checking
        diffArray = new int[256];
        testStringP21 = P21ID;

        
        //SETTINGS
        //later these settings will be selected in a menu within GUI
        //FIXME******----> input needs to be automatically set to count of words in document if number of words is LESS than number of results requested
        numResults = Integer.parseInt(JOptionPane.showInputDialog(null, 
 "What is the number of ordered matches?",
 "Enter the number of results you would like to display: ",
 JOptionPane.QUESTION_MESSAGE));
        
      
            
            
            
            
        
        
        
        
        
        spaces = JOptionPane.showInputDialog(null,
 "Check to consider spaces or no?",
 "Enter y or n",
 JOptionPane.QUESTION_MESSAGE);
        
       
        jfc = new JFileChooser();
        //vendorFileName = jfc.getInitialFileName();
        
        jfc.showDialog(null, "Please Select the File: ");
        jfc.setVisible(true);
        
        
        file = jfc.getSelectedFile();
        fr = new FileReader(file);
        //"C:\\Users\\Max Gillman\\School Stuff\\Independent Projects\\ItemIDChecker\\test.xlsx"
        //C:\Users\Max Gillman\School Stuff\Independent Projects\ItemIDChecker\test.xlsx
        //System.out.println(vendorFileName);

        fis = new FileInputStream(file);//in the future this will be JOptionPane input
        
        try{
        wb = new XSSFWorkbook(fis); //or new XSSFWorkbook("/somepath/test.xlsx")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "File load Error!", "File Error", JOptionPane.ERROR_MESSAGE);
        
// suppose your formula is in A1
        //CellReference cellReference = new CellReference("A1");
       // Row sheetRow = sheet.getRow(cellReference.getRow());
       // Column cell = row.getCell(cellReference.getCol());

        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    }
    }
    /**
     * @param args the command line arguments
     */
    
    public void prepCell(Cell c){
        
        //get cell value
        testStringVendor = c.getStringCellValue(); 
        //P21 String already defined in constructor
        
        //convert to lower case
        testStringP21 = testStringP21.toLowerCase(); 
        testStringVendor = testStringVendor.toLowerCase(); 
        
        //IF spaces = y,
        //REMOVE SPACES from IDs 
        
        if (spaces.equalsIgnoreCase("n")){
            testStringP21 = testStringP21.replaceAll("\\s+", "");
        testStringVendor = testStringVendor.replaceAll("\\s+", "");
        }
        
        
         //add to char arrays
        P21StringToCharArray = testStringP21.toCharArray();
        P21StringLength = P21StringToCharArray.length;
        VendorStringToCharArray = testStringVendor.toCharArray();
        
        //reset variables to 0
        differenceInCounts = 0;
        int SubCheckCharP21 = 0;
        int SubCheckCharVendor = 0;
        
        
        
        
        
        
    }
    
    public int immediateEquality(String s) {

        


        if (testStringP21.equals(testStringVendor)) {
            IDsEqual = true;
            //System.out.println("IDs are equal right off the bat!");
            return 100000;

        }
        return 0;
    }

    //FIXME - CONTAINS ALL TESTS IN THIS METHOD FOR NOW
    public double charTotalsCheck(String s) {

        testStringVendor = s;
        //System.out.println("Value in first cell: " + c.getStringCellValue());
/*
        //fill Aschii count arrays to Zero.
        for (int i = 0; i < P21Ascii.length; ++i) {
            P21Ascii[i] = 0;
        }
        for (int i = 0; i < VendorAscii.length; ++i) {
            VendorAscii[i] = 0;
        }


       ;

        //****************CHECK TOTAL MATCHING CHARACTERS REGARDLESS OF ORDER*************
        //***********************************************************************************************************************************************
        //for this^^^ one we will likely need to perform counts on all ASCII valueS that COULD POSSIBLY exist in an ID.
        //So we would iterate through all ASCII numbers, store counts of each in an ARRAY. i.e. 'a' count = 0, 'b' count = 2, 'c' count = 0, etc.
        //Then we would compare these arrays for both IDs. i.e. both IDs had 2 'h', 1 'g',
        // //test ascii to char, test ascii values between 32 and 
        //System.out.println((char) 66); --> Will print 'B'
        //We will eventually convert this into a percent for report
        //This check will be useful because it checks for Upper and Lower case
        //look at (first) character in p21 ID
        for (int i = 0; i < P21StringLength; ++i) {

            //check through all ascii characters between 32 and 126
            for (int j = 32; j < 127; ++j) {
                //if ID character is equal to an ascii character, tick up 1 on counter and store into array
                if ((char) j == P21StringToCharArray[i]) {
                    P21Ascii[j] = P21Ascii[j] + 1;

                }

            }
        }

        for (int i = 0; i < VendorStringToCharArray.length; ++i) {

            //check through all ascii characters between 32 and 126
            for (int j = 32; j < 127; ++j) {
                //if ID character is equal to an ascii character, tick up 1 and store into array
                if ((char) j == VendorStringToCharArray[i]) {
                    VendorAscii[j] = VendorAscii[j] + 1;

                }

            }
        }
        /*
            //test System.out.println("P21 TABLE");the above
            System.out.println("P21 TABLE");
            for (int i = 0; i < P21Ascii.length; ++i) {
                
                System.out.println(((char) i) + ": " + P21Ascii[i]);
            }
            System.out.println("VENDOR TABLE");
            for (int i = 0; i < VendorAscii.length; ++i) {
                System.out.println(((char) i) + ": " + VendorAscii[i]);
            }
        

        for (int i = 0; i < 256; ++i) {

            differenceInCounts += Math.abs(VendorAscii[i] - P21Ascii[i]);

        }
        System.out.println("Total dIfference Count: " + differenceInCounts);
        
        //***********************************************************************************************************************************************
        //****************END OF CHECK TOTAL MATCHING CHARACTERS REGARDLESS OF ORDER*************
        //
        */
 
        //
//*****************inARowCount*****************************************************************************************************************
        //***********************************************************************************************************************************************
        //hello vs xxxhelloxx
        inARowCount = 0; // starts at -1 because it takes two numbers to make 1 in a row


        maxCount = 0;

        for (int P21Char = 0; P21Char < P21StringLength; ++P21Char) {

            for (int VendorChar = 0; VendorChar < VendorStringToCharArray.length; ++VendorChar) {
                if (P21StringToCharArray[P21Char] == VendorStringToCharArray[VendorChar]) { //lock on to H vs H to make sure original loop iterates through all of the VendorID Loop
                    //dont need to increment here, only want to if there are 2 in a row
                    inARowCount = 0;
                    //now we found that H's are equal, we must move to E's
                    SubCheckCharP21 = P21Char + 1;//look at next P21 Character
                    SubCheckCharVendor = VendorChar + 1; //look at next Vendor ID Character
                    while (SubCheckCharP21 < P21StringLength) {//starts at next P21ID Char, breaks out if non-match

                        if (SubCheckCharVendor < VendorStringToCharArray.length) {//ensure no OOB error
                            if (P21StringToCharArray[SubCheckCharP21] == VendorStringToCharArray[SubCheckCharVendor]) {
                                inARowCount += 1;
                                if (inARowCount > maxCount) {
                                    maxCount = inARowCount;

                                }

                            } else {

                                break;
                            }

                        }

                        SubCheckCharP21 += 1;
                        SubCheckCharVendor += 1;
                    }

                }

            }

            //System.out.println("InARow Count " + P21Char + " :" + (maxCount + 1));
        }

        if (maxCount > 0){
            maxCount = maxCount +1;
        }
        
        numCharNotInARow = P21StringLength - (maxCount);

        //System.out.println("Total In-A-Row count: " + (maxCount) + " out of "+P21StringLength + " possible characters." );
        //System.out.println("Num Characters NOT in a row within Vendor String: " + numCharNotInARow);
        //System.out.println("InARow Count: " + inARowCount);
        //System.out.println("totalInARowCount: " + totalInARowCount);

        //***********************************************************************************************************************************************
        //*************************************************************************************************************************************************
 
        
        
        
        
        //TEMP - CHECKING NEW STRINGS

        //System.out.println(
              //  "P21 ID: " + testStringP21);
       // System.out.println(
        //        "vendor ID: " + testStringVendor);
        //*****************************************************************************************************

        /* 
        for (char output : P21StringToCharArray) {
            System.out.println(output);

        }

        for (char output : VendorStringToCharArray) {
            System.out.println(output);

        }
         */
        //System.out.println("INDEX: " + (double)maxCount/(double)P21StringLength);
        return (double)maxCount/(double)P21StringLength;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    //dynamic iteration version
    public int editDistance(String y) {
        int[][] dp = new int[testStringP21.length() + 1][y.length() + 1];

        for (int i = 0; i <= testStringP21.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                            + costOfSubstitution(testStringP21.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        //System.out.println("The Edit Distance is: " + dp[testStringP21.length()][y.length()]);
        return dp[testStringP21.length()][y.length()];
        

    }
    //Run the Excel version of the program.
    public void runExcel() {

        for (Sheet sheet : wb) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    
                    //set VemdorID, clean up string for analysis
                    this.prepCell(cell);
                    //System.out.println("Vendor code: " + testStringVendor);
                    
                    
                    //Run the different tests, STORE RESULTS ACCORDINGLY
                   
                    totalScore = this.charTotalsCheck(testStringVendor) - (double)+this.editDistance(testStringVendor) + this.immediateEquality(testStringVendor);
                    currentRow = cell.getRowIndex();
                    currentColumn = cell.getColumnIndex();
                    
                    this.codesList.add(new ItemIDCode(this.testStringVendor, totalScore, currentRow, currentColumn));
                    
                    
                     
                    
                    //System.out.println("==========================================================================================================");
                }
            }
        }
        Collections.sort(codesList);
        
        if (codesList.size()  <  numResults){
            numResults = codesList.size();
        }
        
        for (int i = 0; i < numResults; ++i){
            
           System.out.print("SCORE: "+codesList.get(i).getTestTotal() + " :::: ");
           System.out.print("Cell location: (" + codesList.get(i).getRowNum() + ",");
           System.out.print(codesList.get(i).getColNum() + ") --> ID Number: ");
           System.out.println(codesList.get(i).getCode());
        }

    }

    
    
    
    
   

}