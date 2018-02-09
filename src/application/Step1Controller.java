package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Node;

import com.system.parsingengine.XMLParsingEngine;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Step1Controller{
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="vBoxStep1"
    private VBox vBoxStep1; // Value injected by FXMLLoader

    @FXML // fx:id="listViewObjects"
    private ListView<String> listViewObjects; // Value injected by FXMLLoader
    private XMLParsingEngine parsingEngine;
    
    public Step1Controller() {
    	parsingEngine = new XMLParsingEngine();
    	System.err.println("working step1");
	}
    @FXML // fx:id="fxTextFFileDir"
    private TextField fxTextFFileDir; // Value injected by FXMLLoader

    @FXML // fx:id="fxBtnChooseFile"
    private Button fxBtnChooseFile; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert vBoxStep1 != null : "fx:id=\"vBoxStep1\" was not injected: check your FXML file 'step1.fxml'.";
        assert listViewObjects != null : "fx:id=\"listViewObjects\" was not injected: check your FXML file 'step1.fxml'.";
        assert fxTextFFileDir != null : "fx:id=\"fxTextFFileDir\" was not injected: check your FXML file 'step1.fxml'.";
        assert fxBtnChooseFile != null : "fx:id=\"fxBtnChooseFile\" was not injected: check your FXML file 'step1.fxml'.";
        fxTextFFileDir.setEditable(false);
        
        fxBtnChooseFile.setOnAction(event);

    	addItemsToList();

    }
    
    void FXList() {
    
    }
    
    public String getFXListSelectedItem() {
    	/*System.err.println(listViewObjects.getSelectionModel().getSelectedIndex());
    	System.out.println(customObjects.get(listViewObjects.getSelectionModel().getSelectedIndex()).getFullName());
    	*/
    	int i = listViewObjects.getSelectionModel().getSelectedIndex();
    	return (i < 0) ? null : customObjects.get(listViewObjects.getSelectionModel().getSelectedIndex()).getFullName();
    }
    
    public File getFXChoosedFileDir() {
    	System.out.println(file);
    	return file;
    }
    
    void addItemsToList() {
    	
    	//listViewObjects.getItems().add("Object1");
    	List<Node> parsedObjects = parsingEngine.parseObjects("customobjects");
    	System.out.println("parsedNodes"+parsedObjects.size());
    	setParsedData(parsedObjects);
    }
    
    
    ArrayList<CustomObject> customObjects = new ArrayList<>();
    void setParsedData(List<Node> parsedNodes) {
    	CustomObject cObject;
    	//int i = 0;
        for (Node node : parsedNodes) {
        	cObject =  new CustomObject();
			System.out.println("\nCurrent Element :" + node.getName());	 
			System.out.println(node.selectSingleNode("fullName").getText());
			cObject.setFullName(node.selectSingleNode("fullName").getText());
			System.out.println(node.selectSingleNode("label").getText());
			cObject.setLabel(node.selectSingleNode("label").getText());
			customObjects.add(cObject);
			listViewObjects.getItems().add(cObject.getFullName());
         //  i++;
        }
        /*for (CustomObject customObject : customObjects) {
			System.out.println(customObject.getFullName());
		}*/
    }
    
    
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			System.err.println("working button");
			addFileChooser();
		}

		
    	
	};
	File file;
	private void addFileChooser() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv"));

    	Stage primaryStage = new Stage();
    	
    	file = fileChooser.showOpenDialog(primaryStage);
    	System.out.println(file);
    	System.out.println("out of this");
    	try {
    		fxTextFFileDir.setText(file.getAbsolutePath());
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
}











