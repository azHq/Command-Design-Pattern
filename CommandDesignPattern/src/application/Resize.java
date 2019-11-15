package application;

public class Resize extends ICommand {
	
	CircleDrawer circle;
	public int radius;
	public int preRadius;
	
	public Resize(CircleDrawer circle,int radius) {
		
		this.radius=radius;
		this.circle=circle;
	}
	@Override
	public void execute(){
	
		preRadius=(int)circle.circle.getRadius();
		circle.resize(radius);
	}
	@Override
	public void undo() {
		
		circle.resize(preRadius);
	}
	@Override
	public void redo() {
		
		
		circle.resize(radius);
	}

}
