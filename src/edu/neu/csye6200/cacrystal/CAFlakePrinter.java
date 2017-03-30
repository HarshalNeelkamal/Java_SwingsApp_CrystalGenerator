package edu.neu.csye6200.cacrystal;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CAFlakePrinter implements Observer{

	private JFrame frame = null;
	private JPanel sidePanel = null;
	private Canvas centerPanel = null;
	private JButton startButton = null;
	private JButton continueButton = null;
	private JButton stopButton = null;
	private JButton pauseButton = null;
	private JTextField cellDime = null;
	private JComboBox<String> ruleBox = null;	
	
	public JButton getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(JButton continueButton) {
		this.continueButton = continueButton;
	}

	public JComboBox<String> getRuleBox() {
		return ruleBox;
	}

	public void setRuleBox(JComboBox<String> ruleBox) {
		this.ruleBox = ruleBox;
	}

	public JTextField getCellDime() {
		return cellDime;
	}

	public void setCellDime(JTextField cellDime) {
		this.cellDime = cellDime;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public CAFlakePrinter(){
		
		frame = new JFrame("Automata printer");
		frame.setSize(700, 700);
		sidePanel = getPannel();
		sidePanel.setBounds(0, 0, 200, frame.getHeight());
		JPanel temp = new JPanel();
		temp.add(sidePanel);
		frame.add(temp, BorderLayout.WEST);
		centerPanel = new Canvas(50,50);
		frame.add(centerPanel,BorderLayout.CENTER);
		//frame.setResizable(false);
		frame.setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		CAFlake flake = (CAFlake) arg;
		printFlake(flake);
	}
	
	public void setCanvasRule() {
		centerPanel.setPresentRule((String)ruleBox.getSelectedItem());
	}
	
	private void printFlake(CAFlake flake){
		centerPanel.setFlake(flake);
		System.out.println("try");
		centerPanel.repaint();
	}
	
	private JPanel getPannel(){
		
		JPanel panel = new JPanel(new GridLayout(6, 1));
		panel.setBackground(Color.GRAY);

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		pauseButton = new JButton("Pause");
		continueButton = new JButton("Continue");
		cellDime = new JTextField();
		ruleBox = new JComboBox<String>();
		ruleBox.addItem("A");
		ruleBox.addItem("B");
		ruleBox.addItem("C");
		
		panel.add(continueButton,0,0);
		panel.add(pauseButton,1,0);
		panel.add(stopButton,2,0);
		panel.add(startButton,3,0);
		panel.add(cellDime,4,0);
		panel.add(ruleBox,5,0);
		
		return panel;
	}

}
