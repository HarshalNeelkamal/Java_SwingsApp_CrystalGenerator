package edu.neu.csye6200.cacrystal;

import java.util.Random;

public abstract class CARule2 {

	abstract public CAFlake getNextFlake(CAFlake previousFlake);

	public CAFlake initializeFlake(int dimensions, String option) {
		CAFlake flake = new CAFlake(dimensions);
		if(option == "single"){
			((flake.getFlake())[dimensions/2][dimensions/2]).setState(true);
		}else if(option == "double"){
			((flake.getFlake())[(dimensions/4)][dimensions/4]).setState(true);
			((flake.getFlake())[(dimensions/4)*3][(dimensions/4)*3]).setState(true);
		}else{
			((flake.getFlake())[(dimensions/4)][(dimensions/4)]).setState(true);
			((flake.getFlake())[(dimensions/2)][(dimensions/4)*3]).setState(true);
			((flake.getFlake())[(dimensions/4)*3][(dimensions/4)]).setState(true);
		}
		return flake;
	}
	protected int cellColorGenerator(int xc,int yc,int x1,int y1,int xlon, int ylon){
		int value = 0;
		int flakeCellDistance = distance(xc, yc, x1, y1);
		int longestFlakeDistance = distance(xc, yc, xlon, ylon);
		double factor = (double)flakeCellDistance/(double)longestFlakeDistance;
		int availableRange = 255-value;
		value = (int) (value + (availableRange*factor));
		if(value <15){
			value = 15;
		}
		if (value > 125) {
			value = 125;
		}
		return value;
	}
	protected int cellColorGenerator(){
		int value = 0;
		Random r = new Random();
		int min = 0;
		int max = 125;
		value = r.nextInt(max-min) + min;
		return value;
	}
	
	private static int distance(int x1, int y1, int x2, int y2){
		int dis = 0;
		dis = (int)Math.sqrt( (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2) );
		return dis;
	}	
}
