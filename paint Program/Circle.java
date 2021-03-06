package ca.utoronto.utm.paint;

public class Circle {
	
	private Point centre;
	private int radius;

	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
