package application;

import java.util.List;

import org.dom4j.Node;

import com.system.record.Fields;

import javafx.scene.control.ChoiceBox;

// TODO: Auto-generated Javadoc
/**
 * The Class MappedField maps DataBase field.
 */
public class MappedField extends Fields{
	
	/**
	 *  The headers of CSV file. 
	 *  */
	ChoiceBox<String> choiceBox;
	
	/**
	 * The column name of the DataBase. 
	 * */
	String dbLabel;
	
	/**
	 * Instantiates a new mapped field.
	 * 
	 * This constructor
	 *
	 * @param xmlNode The xml Node of single Field
	 * @param choice the choice
	 * @param dbLabel the db label
	 */
	//String fileMappedLabel;
	public MappedField(Node xmlNode, List<String> choice, String dbLabel) {
		/*sets*/
		super(xmlNode, dbLabel);
		choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll(choice);
		this.dbLabel = dbLabel;
	}
	
	/**
	 * Get the JavaFX ChoiceBox, used to add CSV headers.
	 *
	 * @return the choice box
	 */
	public ChoiceBox<String> getChoiceBox() {
		return choiceBox;
	}
	
	/**
	 * The headers of CSV file.
	 *
	 * @param headers The List of CSV headers.
	 */
	public void setChoiceBox(List<String> headers) {
		choiceBox.getItems().addAll(headers);
	}
	
	/**
	 * Returns the Database column name.
	 *
	 * @return dbLabel The name of Database column.
	 */
	public String getDBLabel() {
		return dbLabel;
	}
	
	/**
	 * Sets the column name of the Database.
	 *
	 * @param dbLabel the column name of the Database.
	 */
	public void setDBLabel(String dbLabel) {
		this.dbLabel = dbLabel;
	}
	
	/**
	 * Returns the CSV file header which is mapped with the label.
	 *
	 * @return String the file mapped label
	 */
	public String getFileMappedLabel() {
		return choiceBox.getSelectionModel().getSelectedItem();
	}
	
	/* (non-Javadoc)
	 * @see com.system.record.Fields#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}
	
	/* 
	 * @see com.system.record.Fields#getValueForQuery()
	 */
	@Override
	public String getValueForQuery() {
		try {
			/*
			 * NOTE:
			 * 	Always put value in number field otherwise you will get
			 * 	 Exception
			 * */
			String type = super.getType();
			switch (super.getType().toLowerCase()) {
			case "date":
				return "'"+super.getValue()+"'";
			case "checkbox":
				return "'"+super.getValue()+"'";
			case "picklist":
				return "'"+super.getValue()+"'";
			case "":
				return "'"+super.getValue()+"'";
			default:
				return super.getValue();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
