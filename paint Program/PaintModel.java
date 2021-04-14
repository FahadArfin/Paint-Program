package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Rectangle> Rectangle = new ArrayList<Rectangle>();
	private ArrayList<Square> Square = new ArrayList<Square>();

	public void addPoint(Point p) {
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void addCircle(Circle c) {
		this.circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}
	
	public void addRectangle(Rectangle r) {
		this.Rectangle.add(r);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Rectangle> getRectangle() {
		return Rectangle;
	}
	public void addSquare(Square s) {
		this.Square.add(s);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Square> getSquare() {
		return Square;
	}
}
