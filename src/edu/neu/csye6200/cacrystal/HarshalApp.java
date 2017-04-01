package edu.neu.csye6200.cacrystal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

import edu.neu.csye6200.ui.CAApp;

public class HarshalApp extends CAApp implements Observer,ChangeListener{

	private CAPanel centerPanel = null;
	private CAFlakeSet2 flakeSetObj = null;
	private HashMap<String, CARule2> ruleMap = new HashMap<String, CARule2>(); 
	static CANorthPanel northPanel = null;
	
	//Constructor***
	public HarshalApp() {
		super();
		frame.setSize(700, 700);
		centerPanel = new CAPanel(50,50);//random dimensions to initialise
		frame.add(centerPanel,BorderLayout.CENTER);
		frame.setVisible(true);
		flakeSetObj = new CAFlakeSet2();
		flakeSetObj.addObserver(this);
		initializeRuleColection();
		setButtonActionListeners();
	}
	//***
	
	//main***
	public static void main(String[] args) {
		System.out.println("Harshal is main");
		HarshalApp obj = new HarshalApp();
	}
	
	//***
	
	//control methods***
	
		private void initializeRuleColection(){
			ruleMap.put("Hex-1", new CARuleHex1());
			ruleMap.put("Hex-2", new CARuleHex2());
			ruleMap.put("Square", new CARuleSqu1());
		}
		private void printFlake(CAFlake flake){
			centerPanel.setFlake(flake);
			System.out.println("try");
			centerPanel.repaint();
		}
		private void setButtonActionListeners(){
			northPanel.getStartButton().addActionListener(this);
			northPanel.getPauseButton().addActionListener(this);
			northPanel.getResetButton().addActionListener(this);
			northPanel.getSlider().addChangeListener(this);
		}
		private void resetActions() {
			flakeSetObj.resetAction();
			northPanel.getCellDime().setText("");
			northPanel.setPhaseCount(0);
			northPanel.setSliderVisibility(false, 0);
			northPanel.getStartButton().setActionCommand("Start");
			northPanel.getStartButton().setText("Start");
			northPanel.getPauseButton().setActionCommand("Pause");
			northPanel.getPauseButton().setText("Pause");
			northPanel.getPauseButton().setEnabled(false);
		}
		private void startAction() {
			String rule = (String)northPanel.getRuleBox().getSelectedItem();
			String stepCount = northPanel.getCellDime().getText();
			int stepCountInteger = 0;
			try{
				stepCountInteger = Integer.parseInt(stepCount);
				if(stepCountInteger >= 35 && stepCountInteger <= 175){
						String option = (String)northPanel.getStartOptions().getSelectedItem();
						flakeSetObj.startAction(stepCountInteger, ruleMap.get(rule), option);
						centerPanel.setPresentRule(rule);
						northPanel.getStartButton().setActionCommand("Stop");
						northPanel.getStartButton().setText("Stop");
						northPanel.getPauseButton().setEnabled(true);
						northPanel.getPauseButton().setActionCommand("Pause");
						northPanel.getPauseButton().setText("Pause");
						northPanel.getPauseButton().setEnabled(true);
						northPanel.setPhaseCount(0);
						northPanel.setSliderVisibility(false, 0);
					
				}else{
					//dialog
					JOptionPane.showMessageDialog(centerPanel, "flake stages should be greater then 35 and less then 175");
				}
			}catch (Exception e) {
				//dialog
				JOptionPane.showMessageDialog(centerPanel, "flake stages should be an integer value");
			}
			
			
		}
		private void stopAction() {
			flakeSetObj.stopAction();
			northPanel.getStartButton().setActionCommand("Start");
			northPanel.getStartButton().setText("Start");
			northPanel.getPauseButton().setEnabled(false);
			northPanel.setSliderVisibility(true, flakeSetObj.getCount());
		}
		private void pauseAction() {
			flakeSetObj.pauseAction();
			northPanel.getPauseButton().setActionCommand("Continue");
			northPanel.getPauseButton().setText("Continue");
			northPanel.setSliderVisibility(true, flakeSetObj.getCount());
		}
		private void continueAction() {
			flakeSetObj.continueAction();
			northPanel.getPauseButton().setActionCommand("Pause");
			northPanel.getPauseButton().setText("Pause");
			northPanel.setSliderVisibility(false, 0);
		}
		private void generationCompleted() {
			northPanel.getStartButton().setActionCommand("Start");
			northPanel.getStartButton().setText("Start");
			northPanel.getPauseButton().setActionCommand("Pause");
			northPanel.getPauseButton().setText("Pause");
			northPanel.getPauseButton().setEnabled(false);
			northPanel.setSliderVisibility(true, flakeSetObj.getCount());
		}
	//***
	
	//Abstract method definition***
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Start":
			startAction();
			break;
		case "Stop":
			stopAction();
			break;
		case "Pause":
			pauseAction();
			break;
		case "Continue":
			continueAction();
			break;
		case "Reset":			
			resetActions();
		break;

		default:
			break;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		flakeSetObj.pauseAction();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		flakeSetObj.continueAction();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getNorthPanel() {
		northPanel = new CANorthPanel();
		return northPanel.getPanel();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass().getName() == "edu.neu.csye6200.cacrystal.CAFlakeSet2"){
			if(arg != null){
				northPanel.setPhaseCount(flakeSetObj.getCount());
				CAFlake flake = (CAFlake) arg;
				String color = (String)northPanel.getColorOptions().getSelectedItem();
				centerPanel.setColor(color);
				printFlake(flake);
			}else{
				generationCompleted();
			}
		}		
	}
	//***
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		int value = (int)slider.getValue();
		if(value <= flakeSetObj.getCount()){
			System.out.println(value);
			flakeSetObj.notifyPatternAtIndex(value);
			northPanel.showSliderPosition(value);
		}
	}
	

}
