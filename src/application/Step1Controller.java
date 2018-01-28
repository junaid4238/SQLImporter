package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Node;

import com.system.parsingengine.XMLParsingEngine;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Step1Controller{
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listViewObjects"
    private ListView<String> listViewObjects; // Value injected by FXMLLoader
    private XMLParsingEngine parsingEngine;
    
    public Step1Controller() {
    	parsingEngine = new XMLParsingEngine();
    	System.err.println("working step1");
	}
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listViewObjects != null : "fx:id=\"listViewObjects\" was not injected: check your FXML file 'step1.fxml'.";
        addItemsToList();
    }
    
    void FXList() {
    	
    }
    
    void addItemsToList() {
    	
    	//listViewObjects.getItems().add("Object1");
    	List<Node> parsedObjects = parsingEngine.parseObjects("customobjects");
    	System.out.println("parsedNodes"+parsedObjects.size());
    	setParsedData(parsedObjects);
    }
    
    
    ArrayList<CustomObject> customObjects = new ArrayList<>();
    void setParsedData(List<Node> parsedNodes) {
    	CustomObject cObject =  new CustomObject();
    	//int i = 0;
        for (Node node : parsedNodes) {
			System.out.println("\nCurrent Element :" + node.getName());	 
			System.out.println(node.selectSingleNode("fullName").getText());
			cObject.setFullName(node.selectSingleNode("fullName").getText());
			System.out.println(node.selectSingleNode("label").getText());
			cObject.setLabel(node.selectSingleNode("label").getText());
			customObjects.add(cObject);
			listViewObjects.getItems().add(cObject.getFullName());
         //  i++;
        }
        
    }
}











