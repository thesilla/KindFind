/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemidchecker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author maxeg
 */
public class FXMLtestController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private ListView<?> inputWords;
    @FXML
    private ListView<?> descWords;
    @FXML
    private ListView<String> results;

  
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    @FXML
    private void button1_clicked(ActionEvent event) throws IOException {
        ItemIDChecker iic = new ItemIDChecker("hello"); // eventually this till take array. Then will build up to notepad file, then Excel file
       
       


                
                
        
        iic.runExcel();
        
        for (int i = 0; i < iic.getNumResults(); ++i){
            
            
           results.getItems().add(iic.codesList.get(i).getCode());
        }
       
        
        
    }
    
}
