package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Node;

import com.system.parsingengine.XMLParsingEngine;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Step2Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="fxGridPane_Mapping"
    private GridPane fxGridPane_Mapping; // Value injected by FXMLLoader

    @FXML // fx:id="tfField4"
    private TextField tfField4; // Value injected by FXMLLoader
    
    private XMLParsingEngine parsingEngine;
    
    public Step2Controller() {
    	parsingEngine = new XMLParsingEngine();
    	System.err.println("working step2");
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert fxGridPane_Mapping != null : "fx:id=\"fxGridPane_Mapping\" was not injected: check your FXML file 'step2.fxml'.";
        assert tfField4 != null : "fx:id=\"tfField4\" was not injected: check your FXML file 'step2.fxml'.";
        List<Node> parsedFields = parsingEngine.parseFields("employee");
        try {
			setParsedData(parsedFields);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void setParsedData(List<Node> parsedNodes) throws IOException {
    	int i = 2;
    	
    	CSVEngine csvEngine = new CSVEngine();
    	List<String> headers = new ArrayList<>();
    	Iterator<String> itr = null;
		try {
			itr = csvEngine.readCSVHeaders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itr.forEachRemaining(headers :: add);
    	MappedField[] mappedField = new MappedField[parsedNodes.size()];
    	String dbFieldFullName;
    	int j = 0;
    	for (Node node : parsedNodes) {
    		dbFieldFullName = node.selectSingleNode("fullName").getText();
    		mappedField[j] = new MappedField(headers, dbFieldFullName); 
			
    		System.out.println("List Size: "+headers.size());
    		//mappedField = new MappedField(, dbFieldFullName);
    		
    		System.out.println("\nCurrent Element :" + node.getName());	 
       	 	System.out.println(node.selectSingleNode("fullName").getText());
       	 	
       	 	fxGridPane_Mapping.add(new Label(node.selectSingleNode("label").getText()
       	 			+" ("+node.selectSingleNode("fullName").getText()+")"), 0, i);
	      	fxGridPane_Mapping.add(mappedField[j].getChoiceBox(), 1, i);
	       	System.out.println(node.selectSingleNode("label").getText());
	       	i++;
	       	//break;
        }
    	
    }
}
