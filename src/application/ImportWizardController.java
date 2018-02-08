package application;

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
        
        loadStep1();
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				validateStep1();
			}
		});
    }
    Step1Controller step1Controller;
    void loadStep1() {
    	try {
    		step1Controller = new Step1Controller();
			//paneContent.getChildren().add(FXMLLoader.load(getClass().getResource("/design/step1.fxml")));
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/design/step1.fxml"));
			loader.setController(step1Controller);
			//firstAnchorPan.getChildren().add(loader.load());
			paneContent.getChildren().add(loader.load());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    Step2Controller step2Controller;
    void validateStep1() {
    	System.out.println(step1Controller.getFXListSelectedItem());
    }
    void loadStep2() {
    	try {
    		step2Controller = new Step2Controller();
			//paneContent.getChildren().add(FXMLLoader.load(getClass().getResource("/design/step1.fxml")));
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/design/step2.fxml"));
			loader.setController(step2Controller);

			//firstAnchorPan.getChildren().add(loader.load());
			paneContent.getChildren().add(loader.load());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}