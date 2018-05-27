/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemidchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author maxeg
 */
public class FXMLtestController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private ListView<String> inputWords;
    @FXML
    private ListView<?> descWords;
    @FXML
    private ListView<String> results;

    ArrayList<String>  inputMasterArray;
    JFileChooser inputList;
    FileReader fr;
    BufferedReader br;
    File inputFile;
    String string;
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    //this will allow user to click on a word in input list and will run IIC for list of results on that particular string
    @FXML
   private void inputWords_clicked(ActionEvent event) throws IOException {
       System.out.println("clicked") ;
       
        //int number;
        
        //number = inputWords.getSelectionModel().getSelectedIndex();
        //string = inputMasterArray.get(inputWords.getSelectionModel().getSelectedIndex());
     
        //System.out.println(number);
        
          //ItemIDChecker iic = new ItemIDChecker(string); // eventually this till take array. Then will build up to notepad file, then Excel file
       
       


                
                
        
        //iic.runExcel();
        
       // for (int i = 0; i < iic.getNumResults(); ++i){
            
            
       //    results.getItems().add(iic.codesList.get(i).getCode());
       // }
        
        
    }
    
    
    //this will populate input list
    @FXML
    private void button1_clicked(ActionEvent event) throws IOException {
        
        inputMasterArray = new ArrayList<String>();//might need to increase this later on

        
        
        
        
        
        
        
        inputList = new JFileChooser();
        inputList.showDialog(null, "Please Select the File with searchable words: ");
        inputList.setVisible(true);
        
        inputFile = inputList.getSelectedFile();
        
        fr = new FileReader(inputFile);
        br = new BufferedReader(fr);
        String s;
        s = br.readLine();
        
        //read first line of file
        //if its not null, add to first line of array
        
        
        
        //create standard ArrayList<String> with input words in it from file list
        while (!(s == null)){
            
            inputMasterArray.add(s);
            s = br.readLine();
            
            
        }
        
        for (int i = 0; i < inputMasterArray.size(); ++i ){
            
            inputWords.getItems().add(inputMasterArray.get(i));
            
        }
        
        
        
    }
    
}
