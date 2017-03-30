package edu.neu.csye6200.cacrystal;

public class CAFlakeCell {
	private boolean state;
	private int value;

	public CAFlakeCell() {
		this.state = false;
		this.value = 0;
	}
	
	
	public CAFlakeCell(boolean state) {
		super();
		this.state = state;
		this.value = 0;
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
