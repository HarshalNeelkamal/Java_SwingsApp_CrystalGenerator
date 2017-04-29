package edu.neu.csye6200.cacrystal;

public class CAFlakeCell {
	private boolean state;
	private int value;
	private int classiqueValue;

	public CAFlakeCell() {
		this.state = false;
		this.value = 0; //for colour purpose
		this.classiqueValue = 0; //for colour purpose
	}
	
	
	public CAFlakeCell(boolean state) {
		super();
		this.state = state;
		this.value = 0;
	}
	
	
	public int getClassiqueValue() {
		return classiqueValue;
	}
	public void setClassiqueValue(int classiqueValue) {
		this.classiqueValue = classiqueValue;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isState() {
		return state;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
