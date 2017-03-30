package edu.neu.csye6200.cacrystal;

import javax.print.attribute.SetOfIntegerSyntax;

public class CARule {
	
	public static CAFlake initializeFlake(int width, int height) {
		CAFlake flake = new CAFlake(width,height);
		((flake.getFlake())[height/2][width/2]).setState(true);
		return flake;
	}
	
	public static CAFlake getNextFlake(CAFlake previousFlake, String ruleName) {
		CAFlake nextFlake = new CAFlake(previousFlake.getFlake()[0].length, previousFlake.getFlake().length);
		
		for(int i = 1 ; i < nextFlake.getFlake().length -1 ;i++ ){
			for (int j = 1; j < nextFlake.getFlake()[i].length -1; j++) {
				
				//extracting neighoubrs
				boolean top = previousFlake.getFlake()[i-1][j].isState();
				boolean topLeft = previousFlake.getFlake()[i-1][j-1].isState();
				boolean topRight = previousFlake.getFlake()[i-1][j+1].isState();
				boolean bottom = previousFlake.getFlake()[i+1][j].isState();
				boolean bottomLeft = previousFlake.getFlake()[i+1][j-1].isState();
				boolean bottomRight = previousFlake.getFlake()[i+1][j+1].isState();
				boolean left = previousFlake.getFlake()[i][j-1].isState();
				boolean right = previousFlake.getFlake()[i][j+1].isState();
				//
				
				int position = j%2; 
				boolean returnedValue = returnFlakeCellWithNeighoubrs(top, topLeft, topRight, bottom, bottomLeft, bottomRight, left, right, position, ruleName);
				int cellColorValue = 0;
				if(returnedValue == true){
					int xlon = nextFlake.getFlake()[0].length;
					int ylon = nextFlake.getFlake().length;
					cellColorValue = cellColorGenerator(xlon/2,ylon/2,j,i,xlon,ylon);
				}
				if(previousFlake.getFlake()[i][j].isState() == true){
					nextFlake.getFlake()[i][j].setState(true);
					nextFlake.getFlake()[i][j].setValue(previousFlake.getFlake()[i][j].getValue());
				}
				else{
					nextFlake.getFlake()[i][j].setState(returnedValue);
					nextFlake.getFlake()[i][j].setValue(cellColorValue);
				}
			}
		}
		
		return nextFlake;
	}
	
	private static int cellColorGenerator(int xc,int yc,int x1,int y1,int xlon, int ylon){
		int value = 0;
		int flakeCellDistance = distance(xc, yc, x1, y1);
		int longestFlakeDistance = distance(xc, yc, xlon, ylon);
		double factor = (double)flakeCellDistance/(double)longestFlakeDistance;
		int availableRange = 255-value;
		value = (int) (value + (availableRange*factor)); 
		if(value < 15){
			value = 15;
		}
		return value;
	}
	private static int distance(int x1, int y1, int x2, int y2){
		int dis = 0;
		dis = (int)Math.sqrt( (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2) );
		return dis;
	}
	
	private static boolean returnFlakeCellWithNeighoubrs(boolean top,boolean topLeft,boolean topRight,boolean bottom,boolean bottomLeft,boolean bottomRight,boolean left,boolean right,int position,String ruleName) {
		boolean value = false;
		
		switch (ruleName) {
		case "A":
			int cnt = 0;
			value = false;
			if(position == 0){
				//ignore upper-left and upper-right
				
				if(bottomLeft == true){
					cnt++;
				}
				if(bottomRight == true){
					cnt++;
				}
				
			}else{
				//ignore lower-left and lower-right
				
				if(topLeft == true){
					cnt++;
				}
				if(topRight == true){
					cnt++;
				}
			}
			if(top == true){
				cnt++;
			}
			if(bottom == true){
				cnt++;
			}
			if(left == true){
				cnt++;
			}
			if(right == true){
				cnt++;
			}
			if(cnt == 1 || cnt == 3){
				value = true; 	
			}
			break;
			
		case "B":
			cnt = 0;
			value = false;
			if(top == true){
				cnt++;
			}
			if(bottom == true){
				cnt++;
			}
			if(left == true){
				cnt++;
			}
			if(right == true){
				cnt++;
			}
			if(cnt == 1){
				value = true;
			}
			break;
			
		case "C":
			cnt = 0;
			value = false;
			if(position == 0){
				//ignore upper-left and upper-right
				
				if(bottomLeft == true){
					cnt++;
				}
				if(bottomRight == true){
					cnt++;
				}
				
			}else{
				//ignore lower-left and lower-right
				
				if(topLeft == true){
					cnt++;
				}
				if(topRight == true){
					cnt++;
				}
			}
			if(top == true){
				cnt++;
			}
			if(bottom == true){
				cnt++;
			}
			if(left == true){
				cnt++;
			}
			if(right == true){
				cnt++;
			}
			if(cnt == 1){
				value = true; 	
			}
			break;
			
		default:
			
			break;
		}
		
		return value;
	}
}
