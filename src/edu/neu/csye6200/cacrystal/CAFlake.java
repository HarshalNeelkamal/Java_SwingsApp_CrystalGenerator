package edu.neu.csye6200.cacrystal;

import java.util.Arrays;

public class CAFlake {
		
	private CAFlakeCell[][] flake;
	
	public CAFlake(int width, int height){
		flake = new CAFlakeCell[height][width];
		for (int i = 0; i< height ; i ++){
			for (int j = 0; j< width ; j ++){
				flake[i][j] = new CAFlakeCell(false);
			}
		}
	}
	
//	public void makeInitialFlake(int width, int height) {
//		flake = CARule.initializeFlake(width, height).flake;
//	}
	
//	public static CAFlake getNextFlake(CAFlake previouFlake, String ruleName) {
//		return CARule.getNextFlake(previouFlake, ruleName);
//	}

	public CAFlakeCell[][] getFlake() {
		return flake;
	}

	public void setFlake(CAFlakeCell[][] flake) {
		this.flake = flake;
	}

}
