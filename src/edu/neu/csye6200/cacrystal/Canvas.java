package edu.neu.csye6200.cacrystal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	
	private CAFlake flake = null;
	private String presentRule = "";
	
	public CAFlake getFlake() {
		return flake;
	}

	public void setFlake(CAFlake flake) {
		this.flake = flake;
	}

	public String getPresentRule() {
		return presentRule;
	}

	public void setPresentRule(String presentRule) {
		this.presentRule = presentRule;
	}
	
	public Canvas(int width,int height){
		flake = new CAFlake(width, height);
	}
	
	public void paint(Graphics g) {
		System.out.println("repaint");
		drawCanvas(g);
	}
	
	private void drawCanvas(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);
		int width = flake.getFlake()[0].length;
		int cellDime = size.width/width;
		int extraSpace = (size.width%width)/2;

		for(int i=0; i < flake.getFlake().length; i++){
			for(int j=0; j < flake.getFlake()[i].length; j++){
				if(flake.getFlake()[i][j].isState() == false){
					g2d.setColor(Color.black);
				}else{
						g2d.setColor(new Color(255-flake.getFlake()[i][j].getValue(), 255-flake.getFlake()[i][j].getValue(), 255));
				}
				if(j%2 == 0){
					if(presentRule == "A" || presentRule == "C"){
						g2d.fillRect((cellDime*j)+extraSpace, (cellDime*i)+extraSpace + cellDime/2 , cellDime, cellDime);
					}else{
						g2d.fillRect((cellDime*j)+extraSpace, (cellDime*i)+extraSpace, cellDime, cellDime);
					}
				}else{
					g2d.fillRect((cellDime*j)+extraSpace, (cellDime*i)+extraSpace, cellDime, cellDime);
				}
			}
		}
	}
	
}
