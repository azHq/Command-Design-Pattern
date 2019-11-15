package application;
	


import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;



public class Main extends Application {
	
	Pane mainPane;
	Color color=Color.RED;
	BorderPane root;
	ArrayList<CircleDrawer> list=new ArrayList<>();
	FactoryClass factory=new FactoryClass();
	public boolean transformFlag=false,resizeFlag=false;
	public int preDefferenceX,preDefferenceY;
	public boolean pressControl=false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			
			renderUI();
			Scene scene = new Scene(root,1200,800,Color.GRAY);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Command");
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	
	            	if(event.getCode()==KeyCode.Z&&pressControl) {
	            		
	            		
	            		factory.undo();
	            	}
	                if(event.getCode()==KeyCode.CONTROL) {
	                  
	                	pressControl=true;
	                	
	                }
	                if(event.getCode()==KeyCode.Y&&pressControl) {
	                	
	                	factory.redo();
	                }
	            }
	        });

	
			mainPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

				if(transformFlag) {
					
					
					int cX=(int)e.getX();
		            int cY=(int)e.getY();
		            
		           
		            CircleDrawer circle=list.get(list.size()-1);
		            factory.addCommand(new Transform(circle,cX,cY));
		            
		            
				}
	        	
	         });
			mainPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {

				if(resizeFlag) {
					
					
					int cX=(int)e.getX();
		            int cY=(int)e.getY();
		            
		           
		            CircleDrawer circle=list.get(list.size()-1);
		            
		            if(Math.abs((cX-circle.circle.getCenterX()))<preDefferenceX&&Math.abs((cY-circle.circle.getCenterY()))<preDefferenceY) {
		            	
		            	preDefferenceX=(int)Math.abs((cX-circle.circle.getCenterX()));
		            	preDefferenceY=(int)Math.abs((cX-circle.circle.getCenterX()));
		            	
		            	int radius=(int)circle.circle.getRadius()-1;
			            factory.addCommand(new Resize(circle,radius));
		            }
		            else {
		            	
		            	preDefferenceX=(int)Math.abs((cX-circle.circle.getCenterX()));
		            	preDefferenceY=(int)Math.abs((cX-circle.circle.getCenterX()));
		            	
		            	int radius=(int)circle.circle.getRadius()+1;
			            factory.addCommand(new Resize(circle,radius));
		            }
		            
		            
		            
		            
				}
	        	
	         });
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void renderUI() {
		
		
		 TilePane right = new TilePane();
		 right.setPadding(new Insets(10, 10, 10, 10));
		 right.setPrefColumns(2);
		 right.setStyle("-fx-background-color: #CD5C5C;");
		 VBox hbox2 = new VBox(18); // spacing = 8
		 hbox2.setAlignment(Pos.CENTER);
		 hbox2.setMinSize(200,400);
		 Button drawcircle = new Button("Draw Circle");
		 drawcircle.setMaxSize(120,Double.MAX_VALUE);
		 
		 drawcircle.setOnAction(new EventHandler<ActionEvent>() {
			    @Override 
			    public void handle(ActionEvent e) {
		
			    	CircleDrawer circle=new CircleDrawer(mainPane,color);
			    	list.add(circle);
			    	factory.addCommand(new Draw(circle));
			    	
			    	
			    }
		});
		 Button resize = new Button(" Resize "); 
		 resize.setMaxSize(120,Double.MAX_VALUE);
		 
		 resize.setOnAction(new EventHandler<ActionEvent>() {
			    @Override 
			    public void handle(ActionEvent e) {
		
			    	resizeFlag=true;
			  
			    	
			    }
		});
		 
		 Button erase = new Button(" Erase ");
		 erase.setMaxSize(120,Double.MAX_VALUE);
		 erase.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
		
			    	if(list.size()>0) {
			    		CircleDrawer circle=list.get(list.size()-1);
			    		factory.addCommand(new Erase(circle));
			    	}
			  
			    	
			    }
		});
		 
		 Button  trans= new Button(" Transform ");
		 trans.setMaxSize(120,Double.MAX_VALUE);
		 trans.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
		
			    	transformFlag=true;
			  
			    	
			    }
		});
		 
		 Button  fillcolor= new Button(" FillColor ");
		 fillcolor.setMaxSize(120,Double.MAX_VALUE);
		 fillcolor.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
		
			    	if(list.size()>0) {
			    		CircleDrawer circle=list.get(list.size()-1);
			    		factory.addCommand(new FillColor(circle,color));
			    	}
			  
			    	
			    }
		});
		 
		 final ColorPicker colorPicker = new ColorPicker();
	     colorPicker.setValue(Color.RED);
	     colorPicker.setMaxSize(120,Double.MAX_VALUE);
	     colorPicker.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	
	                color=colorPicker.getValue();
	                if(list.size()>0) {
	                	
			    		CircleDrawer circle=list.get(list.size()-1);
			    		factory.addCommand(new ColorChange(circle,color));
			    	}
	                
	            }
	       });
		
		 hbox2.getChildren().addAll(drawcircle, resize, erase,trans,fillcolor,colorPicker);
		 right.getChildren().add(hbox2);
		 
		 Pane left = new Pane();
		 VBox hbox3 = new VBox(18); // spacing = 8
		 hbox3.setMinSize(170,400);
		 hbox3.setAlignment(Pos.CENTER);
		 Button undo = new Button(" Undo ");
		 undo.setMaxSize(120,Double.MAX_VALUE);
		 undo.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
		
			    	
			    	factory.undo();
			    	
			    	
			    }
		});
		 
		 hbox3.setMaxSize(120,Double.MAX_VALUE);
		 Button redo = new Button(" Redo ");
		 redo.setMaxSize(120,Double.MAX_VALUE);
		 redo.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
		
			    	
			    	factory.redo();
			    	
			    	
			    }
		});
		 
		 left.setMinSize(200,400);
		 hbox3.getChildren().addAll(undo,redo);
		 left.getChildren().add(hbox3);
		 left.setStyle("-fx-background-color: #1565C0;");
		
		Pane anchorpane = new Pane();
		Button buttonSave = new Button("Save");
		Button buttonCancel = new Button("Cancel");
		anchorpane.setStyle("-fx-background-color: #A9A9A9;");
		HBox hb = new HBox();
		hb.getChildren().addAll(buttonSave, buttonCancel);
		anchorpane.getChildren().addAll(hb);
		anchorpane.setMinSize(300, 100);
		AnchorPane.setRightAnchor(hb, 10.0);
		
		root = new BorderPane();
		StackPane bottom=new StackPane();
		bottom.setStyle("-fx-background-color: #6A1B9A;");
		bottom.setMinSize(300, 100);
		
		mainPane=new Pane();
		mainPane.setStyle("-fx-background-color:black;");
		mainPane.setMinSize(600, 700);
	
		root.setCenter(mainPane);
		root.setBottom(bottom);
		
		root.setLeft(left);
		root.setRight(right);
		
		
		
		

	}
	
	
	
	
	
}
