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
	private String backgroundColor = "Black-Background";

	
	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public CAPanel(int dimension){
		flake = new CAFlake(dimension);
		System.out.println("panel initialised");
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
		drawCanvas(g);
	}
	
	private void drawCanvas(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		if(backgroundColor == "Black-Background")
			g2d.setColor(Color.BLACK);
		else
			g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, size.width, size.height);
		int width = flake.getFlake()[0].length;
		int leastOfTwoDime = size.width > size.height?size.height:size.width;
		int cellDime = leastOfTwoDime/width;
		int extraSpaceVertical = (size.height - cellDime*width)/2;
		int extraSpaceHorizontal = (size.width - cellDime*width)/2;

		for(int i=0; i < flake.getFlake().length; i++){
			for(int j=0; j < flake.getFlake()[i].length; j++){
				if(flake.getFlake()[i][j].isState() == false){
					if(backgroundColor == "Black-Background")
						g2d.setColor(Color.BLACK);
					else
						g2d.setColor(Color.WHITE);
				}else{
					g2d.setColor(getColorFor(color, i, j));
				}
				if(j%2 == 0){
					if(presentRule == "Hex-1" || presentRule == "Hex-2" || presentRule == "Hex-3"){
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
	
	private Color getColorFor(String option,int i,int j){
		switch (option) {
		case "Green":
			return (new Color(125-flake.getFlake()[i][j].getValue(), 125+flake.getFlake()[i][j].getValue(), 125));
		case "Blue":
			return (new Color(125-flake.getFlake()[i][j].getValue(), 125, 125+flake.getFlake()[i][j].getValue()));
		case "Red":
			return (new Color(200, 125-flake.getFlake()[i][j].getValue(), 125-flake.getFlake()[i][j].getValue()));
		case "Classic":
			return (new Color(255-flake.getFlake()[i][j].getClassiqueValue(), 255-flake.getFlake()[i][j].getClassiqueValue(), 220));
		default:
			return Color.BLACK;
		}
	}
	
}
