package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.system.parsingengine.XMLParsingEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ImportWizardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="hboxIndicators"
    private HBox hboxIndicators; // Value injected by FXMLLoader

    @FXML // fx:id="paneContent"
    private Pane paneContent; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert hboxIndicators != null : "fx:id=\"hboxIndicators\" was not injected: check your FXML file 'Wizard.fxml'.";
        assert paneContent != null : "fx:id=\"paneContent\" was not injected: check your FXML file 'Wizard.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Wizard.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'Wizard.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'Wizard.fxml'.";
        
        btnCancel.setDisable(true);
        loadStep1();
        btnBack.setDisable(true);
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				backToStep1();
			}
		});
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println(validateStep1());
				loadStep2();
			}
		});
    }
    Step1Controller step1Controller;
    FXMLLoader step1Loader;
    void loadStep1() {
    	try {
    		step1Controller = new Step1Controller();
			//paneContent.getChildren().add(FXMLLoader.load(getClass().getResource("/design/step1.fxml")));
    		step1Loader = new FXMLLoader(this.getClass().getResource("/design/step1.fxml"));
    		step1Loader.setController(step1Controller);
			//firstAnchorPan.getChildren().add(loader.load());
			paneContent.getChildren().add(step1Loader.load());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    File file;
    String selectedObject;
    Step2Controller step2Controller;
    Boolean validateStep1() {
    	file = step1Controller.getFXChoosedFileDir();
    	selectedObject = step1Controller.getFXListSelectedItem();
    	
    	try {
    		if(file.getAbsolutePath() == null || selectedObject == null)
        		return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
    	return true;
    }
    
    void loadStep2() {
    	System.out.println(validateStep1());
    	if(validateStep1()) {
    		try {
        		step2Controller = new Step2Controller( file, selectedObject);
    			//paneContent.getChildren().add(FXMLLoader.load(getClass().getResource("/design/step1.fxml")));
    			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/design/step2.fxml"));
    			loader.setController(step2Controller);

    			//firstAnchorPan.getChildren().add(loader.load());
    			paneContent.getChildren().clear();
    			paneContent.getChildren().add(loader.load());
    			
    			btnBack.setDisable(false);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    }
    
    void backToStep1() {
    	paneContent.getChildren().clear();
    	paneContent.getChildren().add(step1Loader.getRoot());
    	btnBack.setDisable(true);
    }
    
    
}