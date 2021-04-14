package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle; // the rectangle we are building
	private Square square;
	
	private Canvas canvas;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: blue");

		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "Circle"; // bad code here?

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		g.setStroke(Color.WHITE);
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}

		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
			g.strokeOval(x-radius, y-radius, 2*radius, 2*radius);
		}
		
		// Draw Rectangle
		ArrayList<Rectangle> rectangle = this.model.getRectangle();
		for (Rectangle r : rectangle) {
			int x = r.getstart_point().getX();
			int y = r.getstart_point().getY();
			int width = r.getwidth();
			int height = r.getheight();
			if (r.getend_point().getX() < r.getstart_point().getX()) {
				x = x - width;
			}
			if (r.getend_point().getY() < r.getstart_point().getY()) {
				y = y - height;
			}
			
			g.strokeRect(x, y, width, height);
			
		}
		
		// Draw Square
		ArrayList<Square> Square = this.model.getSquare();
		for (Square s : Square) {
			int x = s.getstart_point().getX();
			int y = s.getstart_point().getY();
			int width = s.getwidth();

			if (s.getend_point().getX() < s.getstart_point().getX()) {
				x = x - width;
			}
			if (s.getend_point().getY() < s.getstart_point().getY()) {
				y = y - width;
			}
			
			g.strokeRect(x, y, width, width);
					
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}
	}

	private void mouseMoved(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		} else if (this.mode == "Rectangle") {

		} else if (this.mode == "Square") {
			
		}
	}

	private void mouseDragged(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.model.addPoint(new Point((int) e.getX(), (int) e.getY()));
			
		} else if (this.mode == "Circle") {
			// set radius to current mouse position so it circle stretch in real time.
			int radius = (int) Math.sqrt(((int) e.getX()-(int) this.circle.getCentre().getX())*((int) e.getX()-(int) this.circle.getCentre().getX()) + ((int) e.getY()-(int) this.circle.getCentre().getY())*((int) e.getY()-(int) this.circle.getCentre().getY()));
			this.circle.setRadius(radius);
			this.model.addCircle(this.circle);
			repaint();
			
		} else if (this.mode == "Rectangle") {
			
			this.rectangle.setend_point(new Point((int)e.getX(),(int)e.getY()));
			
			int width =(int) Math.abs((int) e.getX()- (int) this.rectangle.getstart_point().getX());
			int height =(int) Math.abs((int) e.getY()- (int) this.rectangle.getstart_point().getY());

			this.rectangle.setwidth(width);
			this.rectangle.setheight(height);
			this.model.addRectangle(this.rectangle);
			repaint();
		
		} else if (this.mode == "Square") {

			this.square.setend_point(new Point((int)e.getX(),(int)e.getY()));
				
			int width =(int) Math.abs((int) e.getX()- (int) this.square.getstart_point().getX());
			int height =(int) Math.abs((int) e.getY()- (int) this.square.getstart_point().getY());
				
			if (width < height) {
				this.square.setwidth(height);
				this.square.setheight(height);
			} else {
				this.square.setwidth(width);
				this.square.setheight(width);
			}
			this.model.addSquare(this.square);
			repaint();
		}
		
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		} else if (this.mode == "Rectangle") {

		}
	}

	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			// Problematic notion of radius and centre!!
			
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;
			this.circle = new Circle(centre, radius);
		
		} else if (this.mode == "Rectangle") {
			
			Point start_point = new Point((int) e.getX(), (int) e.getY());
			Point end_point = new Point((int) e.getX(), (int) e.getY());
			int width =0;
			int height =0;
			this.rectangle = new Rectangle(start_point,end_point, width, height);
		
		} else if (this.mode == "Square") {
			
			Point start_point = new Point((int) e.getX(), (int) e.getY());
			Point end_point = new Point((int) e.getX(), (int) e.getY());
			int width =0;
			this.square = new Square(start_point,end_point, width);
		}
		
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int radius = (int) Math.sqrt(((int) e.getX()-(int) this.circle.getCentre().getX())*((int) e.getX()-(int) this.circle.getCentre().getX()) + ((int) e.getY()-(int) this.circle.getCentre().getY())*((int) e.getY()-(int) this.circle.getCentre().getY()));

				this.circle.setRadius(radius);
				this.model.addCircle(this.circle);
				this.circle = null;
			}
		
		} else if (this.mode == "Rectangle") {
			if (this.rectangle != null) {
				
				this.rectangle.setend_point(new Point((int)e.getX(),(int)e.getY()));
				
				int width =(int) Math.abs((int) e.getX()- (int) this.rectangle.getstart_point().getX());
				int height =(int) Math.abs((int) e.getY()- (int) this.rectangle.getstart_point().getY());

				this.rectangle.setwidth(width);
				this.rectangle.setheight(height);
				this.model.addRectangle(this.rectangle);
				this.rectangle = null;
			}
		
		} else if (this.mode == "Square") {
			if (this.square != null) {
				
				this.square.setend_point(new Point((int)e.getX(),(int)e.getY()));
				
				int width =(int) Math.abs((int) e.getX()- (int) this.square.getstart_point().getX());
				int height =(int) Math.abs((int) e.getY()- (int) this.square.getstart_point().getY());
				
				if (width < height) {
					this.square.setwidth(height);
					this.square.setheight(height);
				} else {
					this.square.setwidth(width);
					this.square.setheight(width);
				}

				this.model.addSquare(this.square);
				this.square = null;
			}
		}
				
	}

	private void mouseEntered(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		} else if (this.mode == "Rectangle") {

		} else if (this.mode == "Square") {

		}
	}

	private void mouseExited(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		} else if (this.mode == "Rectangle") {

		} else if (this.mode == "Square") {
		
		}
	}
}
