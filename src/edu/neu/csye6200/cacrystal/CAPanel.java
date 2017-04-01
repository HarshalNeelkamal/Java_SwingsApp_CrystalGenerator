package edu.neu.csye6200.cacrystal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CAPanel extends JPanel{
	
	private CAFlake flake = null;
	private String presentRule = "";
	private String color = "Blue";
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public CAPanel(int width,int height){
		flake = new CAFlake(width, height);
	}
	
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
		int leastOfTwoDime = size.width > size.height?size.height:size.width;
		int cellDime = leastOfTwoDime/width;
		int extraSpaceVertical = (size.height - cellDime*width)/2;
		int extraSpaceHorizontal = (size.width - cellDime*width)/2;

		for(int i=0; i < flake.getFlake().length; i++){
			for(int j=0; j < flake.getFlake()[i].length; j++){
				if(flake.getFlake()[i][j].isState() == false){
					g2d.setColor(Color.black);
				}else{
					switch (color) {
					case "Blue":
						g2d.setColor(new Color(255-flake.getFlake()[i][j].getValue(), 255-flake.getFlake()[i][j].getValue(), 235));
						break;
					case "Green":
						g2d.setColor(new Color(255-flake.getFlake()[i][j].getValue(), 235, 255-flake.getFlake()[i][j].getValue()));
						break;
					case "Red":
						g2d.setColor(new Color(235, 255-flake.getFlake()[i][j].getValue(), 255-flake.getFlake()[i][j].getValue()));
						break;

					default:
						break;
					}
				}
				if(j%2 == 0){
					if(presentRule == "Hex-1" || presentRule == "Hex-2"){
						g2d.fillRect((cellDime*j)+extraSpaceHorizontal, (cellDime*i)+extraSpaceVertical + cellDime/2 , cellDime, cellDime);
					}else{
						g2d.fillRect((cellDime*j)+extraSpaceHorizontal, (cellDime*i)+extraSpaceVertical, cellDime, cellDime);
					}
				}else{
					g2d.fillRect((cellDime*j)+extraSpaceHorizontal, (cellDime*i)+extraSpaceVertical, cellDime, cellDime);
				}
			}
		}
	}
	
}
