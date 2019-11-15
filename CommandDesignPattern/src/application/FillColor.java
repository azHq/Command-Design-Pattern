package application;

import javafx.scene.paint.Color;

public class FillColor extends ICommand {
	
	CircleDrawer circle;
	Color color;
	Color preColor;
	public FillColor(CircleDrawer circle,Color color) {
		
		this.color=color;
		this.circle=circle;
	}
	@Override
	public void execute(){
	
		preColor=(Color)circle.circle.getFill();
		circle.fillColor(null);
	}
	@Override
	public void undo() {
		
		circle.fillColor(preColor);
	}
	@Override
	public void redo() {
		
		
		circle.fillColor(null);
	}
}
