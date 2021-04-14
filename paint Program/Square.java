package ca.utoronto.utm.paint;

public class Square extends Rectangle{
	private int width;
	
	public Square(Point start_point,Point end_point, int width) {
		super(start_point,end_point,width, width);
	}
	

}
