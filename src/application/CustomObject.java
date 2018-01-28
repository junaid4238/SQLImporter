package application;

public class CustomObject {
	private String label, fullName;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "CustomObject [label=" + label + ", fullName=" + fullName + "]";
	}
}
