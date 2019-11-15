package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleDrawer {
	
	public int cX=300,cY=300;
	public int radius;
	public Color color;
	public Pane mainPane;
	Circle circle=new Circle();
	public CircleDrawer(Pane mainPane,Color color) {
		
		this.color=color;
		this.mainPane=mainPane;
	}
	
	public void draw() {
		
		
		circle.setCenterX(cX);
		circle.setCenterY(cY);
		circle.setFill(color);
		circle.setRadius(80);
		circle.setStrokeWidth(3);
		circle.setStroke(Color.RED);
		mainPane.getChildren().add(circle);
	}
	
	public void erase() {
		
		mainPane.getChildren().remove(circle);
	}
	
	public void resize(int radius) {
		
		circle.setRadius(radius);
	}
	
	public void changeColor(Color color) {
		
		circle.setFill(color);
	}
	
	public void transform(int cX,int cY) {
		
		circle.setCenterX(cX);
		circle.setCenterY(cY);
		//circle.relocate(cX,cY);
		
	}
	
	public void fillColor(Color color) {
	
		circle.setFill(color);
	}
	

}
