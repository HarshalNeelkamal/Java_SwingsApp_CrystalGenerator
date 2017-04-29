package edu.neu.csye6200.cacrystal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CANorthPanel{
	
	private JButton startButton = null;
	private JButton resetButton = null;
	private JButton pauseButton = null;
	private JTextField cellDime = null;
	private JComboBox<String> ruleBox = null;
	private JComboBox<String> startOptions = null;
	private JComboBox<String> colorOptions = null; 
	private JComboBox<String> background = null; 
	private JPanel panel = null;
	private JPanel upperPanel = null;
	private JPanel lowerPanel = null;
	private JLabel Countlabel = null;
	private JLabel flakeSteps = null;
	private JSlider slider = null;

	
	public CANorthPanel() {

		panel = new JPanel(new GridLayout(2, 1));
		upperPanel = new JPanel(new GridLayout(1, 6));
		lowerPanel = new JPanel(new GridLayout(1, 5));
				
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		resetButton = new JButton("Reset");
		Countlabel = new JLabel();
		flakeSteps = new JLabel(" Flake Steps: ");
		slider = new JSlider();
		cellDime = new JTextField();
		ruleBox = new JComboBox<String>();
		background = new JComboBox<String>();
		startOptions = new JComboBox<String>();
		colorOptions = new JComboBox<String>();
		
		cellDime.setColumns(4);
		pauseButton.setEnabled(false);
		setSliderVisibility(false, 0);
		Countlabel.setFont(new Font("Arial", Font.BOLD, 20));
		setPhaseCount(0);
		flakeSteps.setHorizontalAlignment(SwingConstants.CENTER);
		
		ruleBox.addItem("Hex-1");
		ruleBox.addItem("Hex-2");
		ruleBox.addItem("Hex-3");
		ruleBox.addItem("Square");
		
		startOptions.addItem("single");
		startOptions.addItem("double");
		startOptions.addItem("tripple");
		
		colorOptions.addItem("Classic");
		colorOptions.addItem("Blue");
		colorOptions.addItem("Green");
		colorOptions.addItem("Red");
		
		background.addItem("Black-Background");
		background.addItem("White-Background");
		
		slider.setMinimum(0);

		initialize();
	}
	
	public JComboBox<String> getBackground() {
		return background;
	}

	public void setBackground(JComboBox<String> background) {
		this.background = background;
	}

	public JComboBox<String> getStartOptions() {
		return startOptions;
	}

	public void setStartOptions(JComboBox<String> startOptions) {
		this.startOptions = startOptions;
	}

	public JSlider getSlider() {
		return slider;
	}

	public void setSlider(JSlider slider) {
		this.slider = slider;
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
	
	public JComboBox<String> getColorOptions() {
		return colorOptions;
	}

	public void setColorOptions(JComboBox<String> colorOptions) {
		this.colorOptions = colorOptions;
	}
	
	//Control Methods***

	public void setPhaseCount(int cnt) {
		Countlabel.setText("Stage: "+cnt);
	}
	
	public void showSliderPosition(int cnt){
		Countlabel.setText("Stage: "+cnt);
	}
	
	public void setSliderVisibility(boolean visibility, int cnt){
		slider.setVisible(visibility);
		if (visibility == true){
			slider.setMaximum(cnt);
			slider.setValue(cnt);
		}
	}
	
	private void initialize(){
		panel.setBackground(Color.GRAY);
		
		upperPanel.add(resetButton,0,0);
		upperPanel.add(pauseButton,1,0);
		upperPanel.add(startButton,2,0);
		upperPanel.add(cellDime,3,0);
		upperPanel.add(flakeSteps,4,0);
		upperPanel.add(ruleBox,5,0);
		
		lowerPanel.add(background,0,0 );
		lowerPanel.add(colorOptions,1,0);
		lowerPanel.add(startOptions,2,0);
		lowerPanel.add(slider,3,0);
		lowerPanel.add(Countlabel,4,0);
		
		upperPanel.setLayout(new FlowLayout());
		lowerPanel.setLayout(new FlowLayout());

		panel.add(upperPanel);
		panel.add(lowerPanel);
		
	}
	
	//***

	
}
