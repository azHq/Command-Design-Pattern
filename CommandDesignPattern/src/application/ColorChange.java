package application;

import javafx.scene.paint.Color;

public class ColorChange extends ICommand {
	
	CircleDrawer circle;
	Color color;
	Color preColor;
	public ColorChange(CircleDrawer circle,Color color) {
		
		this.color=color;
		this.circle=circle;
	}
	@Override
	public void execute(){
	
		preColor=(Color)circle.circle.getFill();
		circle.changeColor(color);
	}
	@Override
	public void undo() {
		
		circle.changeColor(preColor);
	}
	@Override
	public void redo() {
		
		
		circle.changeColor(color);
	}
}