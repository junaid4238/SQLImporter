package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVRecord;
import org.dom4j.Node;

import com.system.dbconfig.DbConfig;
import com.system.parsingengine.XMLParsingEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Step2Controller.
 */
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
    
    File file;
    String seletedObj_NM;

    /**
     * Instantiates a constructor.
     *
     * @param file the csv file
     * @param seletedObj_NM the name of the object which will be used to parse the XML Metadata file.
     * 						Consider seletedObj_NM = The metadata file of the object
     */
    public Step2Controller(File file, String seletedObj_NM) {
    	this.file = file;
    	this.seletedObj_NM = seletedObj_NM;
    	parsingEngine = new XMLParsingEngine();
    	System.err.println("working step2");
	}
    
    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert fxGridPane_Mapping != null : "fx:id=\"fxGridPane_Mapping\" was not injected: check your FXML file 'step2.fxml'.";
        assert tfField4 != null : "fx:id=\"tfField4\" was not injected: check your FXML file 'step2.fxml'.";
        List<Node> parsedFields = parsingEngine.parseFields(seletedObj_NM.toLowerCase());
        try {
			setParsedData(parsedFields);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Sets the parsed data.
     *
     * @param parsedNodes the new parsed data
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void setParsedData(List<Node> parsedNodes) throws IOException {
    	int i = 2;
    	
    	CSVEngine csvEngine = new CSVEngine();
    	List<String> headers = new ArrayList<>();
    	Iterator<String> itr = null;
		try {
			itr = csvEngine.readCSVHeaders(file.getAbsolutePath());
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
    		mappedField[j] = new MappedField(node, headers, dbFieldFullName); 
			
    		System.out.println("List Size: "+headers.size());
    		//mappedField = new MappedField(, dbFieldFullName);
    		
    		System.out.println("\nCurrent Element :" + node.getName());	 
       	 	System.out.println(node.selectSingleNode("fullName").getText());
       	 	
//       	 	fxGridPane_Mapping.add(new Label(node.selectSingleNode("label").getText()
//       	 			+" ("+node.selectSingleNode("fullName").getText()+")"), 0, i);
	      	fxGridPane_Mapping.add(mappedField[j].getLabelField(), 0, i);
       	 	fxGridPane_Mapping.add(mappedField[j].getChoiceBox(), 1, i);
	       //	System.out.println(node.selectSingleNode("label").getText());
	       	i++;
	       	j++;
	       	//break;
        }
    	Button btn = new Button("testing");
    	fxGridPane_Mapping.add(btn, 3, 1);
    	btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				List<MappedField> list = new ArrayList<>(Arrays.asList(mappedField));
				list.removeIf((MappedField mp) -> mp.getFileMappedLabel() == null);
				StringBuilder columns = new StringBuilder();
				int i = 0;
				for(MappedField map : list) {
					System.out.println("DB Field: "+map.getDBLabel());
					System.out.println("Mapped Field: "+map.getFileMappedLabel());
						
					if(i>0)
						columns.append(",");
					columns.append(map.getDBLabel());
					i++;
				}
				/*for (MappedField mappedField2 : mappedField) {
					System.out.println("DB Field: "+mappedField2.getDBLabel());
					System.out.println("Mapped Field: "+mappedField2.getFileMappedLabel());
				}*/
				System.out.println(list.size());
				System.out.println(columns.toString());
				
				DbConfig dbConfig = new DbConfig();
				StringBuilder values = new StringBuilder();
				
				List<CSVRecord> csvRecords = null;
				try {
					csvRecords = csvEngine.readFileRecords(file.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(CSVRecord record : csvRecords) {
					
					i = 0;
					for(MappedField map : list) {
						map.setValue(record.get(map.getFileMappedLabel()));
						if(i>0)
							values.append(",");
						values.append(map.getValueForQuery());
						i++;
					}
					
					dbConfig.insert_into_Object("employee", columns.toString(), values.toString());
					values = null;
					values = new StringBuilder();
				}
				
			}
		});
    	
    }
    
    
    /**
     * Prepare columns.
     */
    private void prepareColumns() {
    	
    }
    
}
