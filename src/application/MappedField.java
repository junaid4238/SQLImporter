package application;

import java.util.List;

import org.dom4j.Node;

import com.system.record.Fields;

import javafx.scene.control.ChoiceBox;

public class MappedField extends Fields{
	
	ChoiceBox<String> choiceBox;
	String dbLabel;
	//String fileMappedLabel;
	public MappedField(Node xmlNode, List<String> choice, String dbLabel) {
		super(xmlNode, dbLabel);
		choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll(choice);
		this.dbLabel = dbLabel;
	}
	
	public ChoiceBox<String> getChoiceBox() {
		return choiceBox;
	}
	private void setChoiceBox(ChoiceBox<String> choiceBox) {
		this.choiceBox = choiceBox;
	}
	public String getDBLabel() {
		return dbLabel;
	}
	public void setDBLabel(String dbLabel) {
		this.dbLabel = dbLabel;
	}
	public String getFileMappedLabel() {
		return choiceBox.getSelectionModel().getSelectedItem();
	}
	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}
	@Override
	public String getValueForQuery() {
		try {
			
			/*
			 * Future : Convert it into switch case
			 * */
			
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
			
			/*if(super.getType().equalsIgnoreCase("number")) {
				
				return super.getValue();
			} else if(super.getType().equalsIgnoreCase("picklist")) {
				
				return "'"+super.getValue()+"'";
			} else if(super.getType().equalsIgnoreCase("checkBox")) {
				
				
				return "'"+super.getValue()+"'";
			} else if(super.getType().equalsIgnoreCase("date")){
				
				return "'"+super.getValue()+"'";
			} else if(super.getType().equalsIgnoreCase("lookup")){
				return super.getValue();
			} else {
				
				return "'"+super.getValue()+"'";
			}*/
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
