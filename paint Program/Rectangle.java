package ca.utoronto.utm.paint;

public class Rectangle {
	
	private Point start_point;
	private Point end_point;
	private int width;
	private int height;
	
	public Rectangle(Point start_point,Point end_point, int width, int height) {
		this.start_point = start_point;
		this.end_point = end_point;
		this.width = width;
		this.height = height;

	}

	public Point getstart_point() {
		return start_point;
	}

	public void setstart_point(Point start_point) {
		this.start_point = start_point;
	}

	public Point getend_point() {
		return end_point;
	}

	public void setend_point(Point end_point) {
		this.end_point = end_point;
	}
	
	public int getwidth() {
		return width;
	}

	public void setwidth(int width) {
		this.width = width;
	}
	public int getheight() {
		return height;
	}

	public void setheight(int height) {
		this.height = height;
	}

}
