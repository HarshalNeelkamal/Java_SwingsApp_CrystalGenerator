package edu.neu.csye6200.cacrystal;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class CAFlakeSet2 extends Observable{

	private ArrayList<CAFlake> FlakeCollection = new ArrayList<CAFlake>();
	private int stepCount = 0;
	private CARule2 ruleClass = null; 
	private String option = "";
	private static boolean pause = false;
	private static boolean stopInstruction = false;
	private int cnt = 0;
	private int refCount = 0;
	private boolean inProccess = false;
	private Timer javaTimer = null;	
	
	public CAFlakeSet2() {
		super();
	}

	private void startGeneration(){
		cnt = 0;
		FlakeCollection.add(0,ruleClass.initializeFlake(stepCount*2,option));
		action(FlakeCollection.get(0));
		javaTimer = new Timer();
		javaTimer.scheduleAtFixedRate(
			new TimerTask() {
				@Override
				public void run() {
					if(inProccess == false){
						newFlakeSnippet();
					}
				}
			}, 100, 200);
	}

	
	private void action(CAFlake caFlake) {
		// TODO Auto-generated method stub
		setChanged();
		notifyObservers(caFlake);
	}
	
	private void setBlankFrame(){
		CAFlake blankFlake = new CAFlake(65, 65);
		setChanged();
		notifyObservers(blankFlake);
	}

	//public access methods***
	public int getCount() {
		return FlakeCollection.size()-1;
	}
	public void stopAction() {
		stopInstruction = true;
	}
	public void startAction(int stepCount, CARule2 ruleClass, String option) {
		this.stepCount = stepCount;
		refCount = stepCount;
		this.ruleClass = ruleClass;
		this.option = option;
		pause = false;
		if(javaTimer != null){
			javaTimer.cancel();
			javaTimer = null;
		}
		FlakeCollection.clear();
		startGeneration();
	}
	public void pauseAction() {
		pause = true;
	}
	public void continueAction() {
		pause = false;
	}
	public void resetAction() {
		if(javaTimer != null){
			javaTimer.cancel();
			javaTimer = null;
		}
		setBlankFrame();
	}
	public void notifyPatternAtIndex(int index) {
		setChanged();
		notifyObservers(FlakeCollection.get(index));
	}
	//***
	
	//private methods***
	private void newFlakeSnippet(){
		
		if(!pause){//checks if the generation is paused
			refCount --;
			int i = stepCount - refCount;
			
			//processing
				inProccess = true;//prevents any calls from timer while pattern gets generated
				cnt++;
				FlakeCollection.add(i,ruleClass.getNextFlake(FlakeCollection.get(i-1)));
				action(FlakeCollection.get(i));
				inProccess = false;
			//
			
			if(refCount == 1){
				//deactivate timer
				if(javaTimer != null){
					javaTimer.cancel();
					javaTimer = null;
					System.out.println("timer Cancelled");
					setChanged();
					notifyObservers();//notifies completion of generation cycle
				}
				//
			}
		}
		
	}
	//***

}
