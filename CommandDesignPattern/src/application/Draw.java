package application;

public class Draw extends ICommand{

	CircleDrawer circle;
	public Draw(CircleDrawer circle) {
		
		this.circle=circle;
	}
	@Override
	public void execute(){
		
		circle.draw();
	}
	@Override
	public void undo() {
		
		circle.erase();
	}
	@Override
	public void redo() {
		
		circle.draw();
	}
}
