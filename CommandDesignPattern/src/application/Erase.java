package application;

public class Erase extends ICommand{

	CircleDrawer circle;

	public Erase(CircleDrawer circle) {
	
		this.circle=circle;
	}
	@Override
	public void execute(){

		circle.erase();
	}
	@Override
	public void undo() {
		
		circle.draw();
	}
	@Override
	public void redo() {
		
		
		circle.erase();
	}
}
