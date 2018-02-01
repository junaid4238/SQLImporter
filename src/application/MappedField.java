package application;

import java.util.List;

import javafx.scene.control.ChoiceBox;

public class MappedField {
	
	ChoiceBox<String> choiceBox;
	String dbLabel;
	//String fileMappedLabel;
	public MappedField(List<String> choice, String dbLabel) {
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

}
