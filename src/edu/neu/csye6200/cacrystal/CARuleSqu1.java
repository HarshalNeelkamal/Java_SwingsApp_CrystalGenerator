package edu.neu.csye6200.cacrystal;

public class CARuleSqu1 extends CARule2{

	@Override
	public CAFlake getNextFlake(CAFlake previousFlake) {
		CAFlake nextFlake = new CAFlake(previousFlake.getFlake()[0].length, previousFlake.getFlake().length);
		
		for(int i = 1 ; i < nextFlake.getFlake().length -1 ;i++ ){
			for (int j = 1; j < nextFlake.getFlake()[i].length -1; j++) {
				
				//extracting neighbours
				boolean top = previousFlake.getFlake()[i-1][j].isState();
				boolean topLeft = previousFlake.getFlake()[i-1][j-1].isState();
				boolean topRight = previousFlake.getFlake()[i-1][j+1].isState();
				boolean bottom = previousFlake.getFlake()[i+1][j].isState();
				boolean bottomLeft = previousFlake.getFlake()[i+1][j-1].isState();
				boolean bottomRight = previousFlake.getFlake()[i+1][j+1].isState();
				boolean left = previousFlake.getFlake()[i][j-1].isState();
				boolean right = previousFlake.getFlake()[i][j+1].isState();
				//
				
				int position = j%2; //tells if its even column or odd
				boolean returnedValue = returnFlakeCellWithNeighoubrs(top, topLeft, topRight, bottom, bottomLeft, bottomRight, left, right, position);
				int cellColorValue = 0;
				if(returnedValue == true){
					int xlon = nextFlake.getFlake()[0].length;
					int ylon = nextFlake.getFlake().length;
					cellColorValue = super.cellColorGenerator(xlon/2,ylon/2,j,i,xlon,ylon);
					if(i == 1 || j == 1){
						System.out.println("breaking");
						return previousFlake;
					}
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
	private static boolean returnFlakeCellWithNeighoubrs(boolean top,boolean topLeft,boolean topRight,boolean bottom,boolean bottomLeft,boolean bottomRight,boolean left,boolean right,int position) {
		int cnt = 0;
		boolean value = false;
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
	
	return value;
}

	

}
