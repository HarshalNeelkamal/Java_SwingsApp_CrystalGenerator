package edu.neu.csye6200.cacrystal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.neu.csye6200.ui.CAApp;

public class HarshalApp extends CAApp implements Observer{

	private Canvas centerPanel = null;
	CAFlakeSet flakeSetObj = new CAFlakeSet();
	static CANorthPanel northPanel = null;
	private int cnt = 0;

	//Constructor***
	public HarshalApp() {
		super();
		flakeSetObj.addObserver(this);
		frame.setSize(700, 700);
		centerPanel = new Canvas(50,50);
		frame.add(centerPanel,BorderLayout.CENTER);
		frame.setVisible(true);
		setButtonActions();
	}
	//***
	
	//control methods***
		private void printFlake(CAFlake flake){
			centerPanel.setFlake(flake);
			System.out.println("try");
			centerPanel.repaint();
		}
		private void setButtonActions(){
			northPanel.getStartButton().addActionListener(this);
			northPanel.getPauseButton().addActionListener(this);
			northPanel.getResetButton().addActionListener(this);
		}
		private void resetActions() {
			northPanel.getStartButton().setActionCommand("Start");
			northPanel.getStartButton().setText("Start");
			northPanel.getPauseButton().setActionCommand("Pause");
			northPanel.getPauseButton().setText("Pause");
			northPanel.getPauseButton().setEnabled(false);
			cnt = 0;
		}
	//***
	
	//Abstract method definition***
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Start":
			String rule = (String)northPanel.getRuleBox().getSelectedItem();
			String cellDime = northPanel.getCellDime().getText();
			centerPanel.setPresentRule(rule);
			flakeSetObj.startButtonAction(Integer.parseInt(cellDime), rule);
			northPanel.getStartButton().setActionCommand("Stop");
			northPanel.getStartButton().setText("Stop");
			northPanel.getPauseButton().setEnabled(true);
			northPanel.setPhaseCount(0);
			break;
		case "Stop":
			flakeSetObj.stopButtonAction();
			northPanel.getStartButton().setActionCommand("Start");
			northPanel.getStartButton().setText("Start");
			northPanel.getPauseButton().setEnabled(false);
			break;
		case "Pause":
			flakeSetObj.pauseButtonAction();
			northPanel.getPauseButton().setActionCommand("Continue");
			northPanel.getPauseButton().setText("Continue");
			break;
		case "Continue":
			flakeSetObj.continueButtonAction();
			northPanel.getPauseButton().setActionCommand("Pause");
			northPanel.getPauseButton().setText("Pause");
			break;
		case "Reset":
			flakeSetObj.resetButtonAction();
			northPanel.getCellDime().setText("");
			resetActions();
			northPanel.setPhaseCount(0);
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
		flakeSetObj.pauseButtonAction();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		flakeSetObj.continueButtonAction();
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
		if(o.getClass().getName() == "edu.neu.csye6200.cacrystal.CAFlakeSet"){
			if(arg != null){
				cnt++;
				northPanel.setPhaseCount(cnt);
				CAFlake flake = (CAFlake) arg;
				printFlake(flake);
			}else{
				resetActions();
			}
		}		
	}
	//***
	

}
