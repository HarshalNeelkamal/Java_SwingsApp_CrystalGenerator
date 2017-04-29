package edu.neu.csye6200.cacrystal;

public class CAFlake {
		
	private CAFlakeCell[][] flake;
	
	public CAFlake(int dimension){
		flake = new CAFlakeCell[dimension][dimension];
		for (int i = 0; i< dimension ; i ++){
			for (int j = 0; j< dimension ; j ++){
				flake[i][j] = new CAFlakeCell(false);
			}
		}
	}

	public CAFlakeCell[][] getFlake() {
		return flake;
	}

	public void setFlake(CAFlakeCell[][] flake) {
		this.flake = flake;
	}

}
