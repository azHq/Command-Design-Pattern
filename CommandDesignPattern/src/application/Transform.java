package application;

public class Transform extends ICommand{

	CircleDrawer circle;
	public int cX;
	public int cY;
	public int preCX;
	public int preCY;
	public Transform(CircleDrawer circle,int cX,int cY) {
		
		this.cX=cX;
		this.cY=cY;
		this.circle=circle;
	}
	@Override
	public void execute(){
	
		preCX=(int)circle.circle.getCenterX();
		preCY=(int)circle.circle.getCenterY();
		circle.transform(cX,cY);
	}
	@Override
	public void undo() {
		
		circle.transform(preCX,preCY);
	}
	@Override
	public void redo() {
		
		
		circle.transform(cX,cY);
	}
}
