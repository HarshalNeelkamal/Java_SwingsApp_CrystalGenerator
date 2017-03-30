package edu.neu.csye6200.cacrystal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CAFlakeMain{
	
	
	public CAFlakeMain(){
		System.out.println("Main Method Initialised");
	}

	public void run() throws NumberFormatException, IOException{
		
		CAFlakeSet flakeSetObj = new CAFlakeSet();
		CAFlakePrinter printer = new CAFlakePrinter();
		flakeSetObj.addObserver(printer);
		printer.getStartButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				printer.setCanvasRule();
				flakeSetObj.startButtonAction(Integer.parseInt(printer.getCellDime().getText()), (String) printer.getRuleBox().getSelectedItem());
			}
			
		});
		printer.getStopButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flakeSetObj.stopButtonAction();
			}
			
		});
		printer.getPauseButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flakeSetObj.pauseButtonAction();
			}
			
		});
		printer.getContinueButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flakeSetObj.continueButtonAction();
			}
			
		});
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		CAFlakeMain testObj = new CAFlakeMain();
		//testObj.run();
		HarshalApp obj = new HarshalApp();
	}
	
}
