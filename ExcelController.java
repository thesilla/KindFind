/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemidchecker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Max Gillman
 */
public class ExcelController implements Initializable {

    @FXML
    private AnchorPane input;
    @FXML
    private AnchorPane descWords;
    @FXML
    private AnchorPane results;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
