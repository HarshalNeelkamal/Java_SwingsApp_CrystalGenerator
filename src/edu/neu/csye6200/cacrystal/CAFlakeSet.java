package edu.neu.csye6200.cacrystal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class CAFlakeSet extends Observable implements ActionListener{
	
	private ArrayList<CAFlake> FlakeCollection = new ArrayList<CAFlake>();
	private int stepCount = 0;
	private int refCount = 0;
	private String ruleName = "A";
	private Timer javaTimer = null;
	private boolean inProccess = false;
	private boolean waitOver = true;

	
	private void startGenerationWith(int width, int height, String ruleName){
		
		this.ruleName = ruleName;
		stepCount = ((width > height)?height:width)/2;
		refCount = ((width > height)?height:width)/2;
		
		FlakeCollection.add(0,setInitialFlake(width,height));
		action(FlakeCollection.get(0));
		
		//timer activation snippet
			javaTimer = new Timer();
			javaTimer.scheduleAtFixedRate(
				new TimerTask() {
					@Override
					public void run() {
						if(!inProccess){
							generationLoop();
						}
					}
				}, 100, 200);
		//
	}
	
	public void action(CAFlake flake) {
		setChanged();//Something Happened
		notifyObservers(flake);
	}
	
	private CAFlake setInitialFlake(int width, int height) {
		CAFlake tempFlake = CARule.initializeFlake(width, height);
		return tempFlake;
	}
	
	private void setBlankFrame(){
		CAFlake blankFlake = new CAFlake(65, 65);
		setChanged();
		notifyObservers(blankFlake);
	}
	
	public void startButtonAction(int cellDime, String rule){
		waitOver = true;
		if(javaTimer != null){
			javaTimer.cancel();
			javaTimer = null;
		}
		System.out.println("here in start: "+cellDime);
		System.out.println("before: "+ FlakeCollection.size());
		FlakeCollection.clear();
		System.out.println("after: "+ FlakeCollection.size());
		startGenerationWith(cellDime, cellDime, rule);
	}
	
	public void stopButtonAction() {
		if(javaTimer != null){
			javaTimer.cancel();
			javaTimer = null;
		}
	}
	
	public void pauseButtonAction() {
		waitOver = false;
	}

	public void continueButtonAction() {
		waitOver = true;
	}
	
	public void resetButtonAction() {
		if(javaTimer != null){
			javaTimer.cancel();
			javaTimer = null;
		}
		setBlankFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void generationLoop(){
		if(waitOver){//checks if the generation is paused
			stepCount --;
			int i = refCount - stepCount;
			
			//processing
				inProccess = true;//prevents any calls from timer while pattern gets generated
				FlakeCollection.add(i,CARule.getNextFlake(FlakeCollection.get(i-1), ruleName));
				action(FlakeCollection.get(i));
				inProccess = false;
			//
			
			if(stepCount == 1){
				//deactivate timer
				if(javaTimer != null){
					javaTimer.cancel();
					javaTimer = null;
					System.out.println("timer Cancelled");
					setChanged();
					notifyObservers();
				}
				//
			}
		}
	}
	
}
