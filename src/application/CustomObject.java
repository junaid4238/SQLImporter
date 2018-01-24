package application;

public class CustomObject {
	private String label, fullName;

	public synchronized String getLabel() {
		return label;
	}

	public synchronized void setLabel(String label) {
		this.label = label;
	}

	public synchronized String getFullName() {
		return fullName;
	}

	public synchronized void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "CustomObject [label=" + label + ", fullName=" + fullName + "]";
	}	
}
