package rl.knockout.taiei.model;

public class SystemMessage{
	String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return errorMessage;
	}
	
}