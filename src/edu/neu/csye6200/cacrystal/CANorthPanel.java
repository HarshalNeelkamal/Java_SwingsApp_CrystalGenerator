package edu.neu.csye6200.cacrystal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CANorthPanel{
	
	
	private JButton startButton = null;
	private JButton resetButton = null;
	private JButton pauseButton = null;
	private JTextField cellDime = null;
	private JComboBox<String> ruleBox = null;
	private JPanel panel = null;
	private JPanel upperPanel = null;
	private JPanel lowerPanel = null;
	private JLabel Countlabel = null;

	
	public CANorthPanel() {

		panel = new JPanel(new GridLayout(2, 1));
		upperPanel = new JPanel(new GridLayout(1, 6));
		lowerPanel = new JPanel(new GridLayout(1, 6));

		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		resetButton = new JButton("Reset");
		Countlabel = new JLabel();
		setPhaseCount(0);
		cellDime = new JTextField();
		ruleBox = new JComboBox<String>();
		pauseButton.setEnabled(false);

		ruleBox.addItem("A");
		ruleBox.addItem("B");
		ruleBox.addItem("C");

		initialize();
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JTextField getCellDime() {
		return cellDime;
	}

	public void setCellDime(JTextField cellDime) {
		this.cellDime = cellDime;
	}

	public JComboBox<String> getRuleBox() {
		return ruleBox;
	}

	public void setRuleBox(JComboBox<String> ruleBox) {
		this.ruleBox = ruleBox;
	}
	
	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}
	
	public void setPhaseCount(int cnt) {
		Countlabel.setText("Stage: "+cnt);
	}
	
	private void initialize(){
		panel.setBackground(Color.GRAY);
		
		upperPanel.add(resetButton,0,0);
		upperPanel.add(pauseButton,1,0);
		upperPanel.add(startButton,3,0);
		upperPanel.add(cellDime,4,0);
		upperPanel.add(ruleBox,5,0);
		
		lowerPanel.add(Countlabel, 0);
		
		panel.add(upperPanel);
		panel.add(lowerPanel);
		
	}
	
}
