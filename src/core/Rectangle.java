package core;
import java.awt.Color;

import processing.core.PApplet;

/** Circle uses the Processing library to model a circle.
 * 
 * @author Jack Reilly
 * @version 10/9/2024
 * 
 */
public class Rectangle extends Shape {

	private double width, height;
	
	
	
	/**
	 * Creates a default instance of a Rectangle object with all dimensions set to zero.
	 */
	public Rectangle() {
		super(0, 0);
		width = 0;
		height = 0;
	}
	
	/**
	 * Creates a new instance of a Rectangle object with the left and right
	 * edges of the rectangle at x and x + width. The top and bottom edges
	 * are at y and y + height.
	 * 
	 * @param x The X coordinate of the left of the rectangle
	 * @param y The Y coordinate of the top of the rectangle
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	/** Calculates and returns the perimeter of the rectangle
	 * 
	 * @return The perimeter of the rectangle
	 */
	public double getPerimeter() {
		return 2*Math.abs(height) + 2*Math.abs(width);
	}
	
	/** Calculates and returns the area of the rectangle
	 * 
	 * @return The area of the rectangle
	 */
	public double getArea() {
		return Math.abs(width*height);
	}
	
	/** Determines whether the point (x,y) is contained inside this Rectangle
	 * 
	 * @param x The X coordinate of the test point
	 * @param y The Y coordinate of the test point
	 * @return Whether the test point is inside of the rectangle
	 */
	public boolean isPointInside(double x, double y) {
		double x2 = getX();
		double y2 = getY();
		
		if ((x > x2 && y > y2) && (x2 + width > x && y2 + height > y)) {
			return true;
		}
		return false;
	}
	
	
	
	/** Draws an instance of a Rectangle object
	* @param marker The drawing surface to draw on 
	* @pre The rectangle will use settings of the drawer that are set in advance:
	*/
	public void draw(PApplet marker) {
		double x = getX();
		double y = getY();
		super.draw(marker);
		
		marker.rect((float)x, (float)y,(float)width, (float)height);
	}
	
	/**
	 * Returns a String containing debugging info for the Rectangle (the values of fields)
	 */
	public String toString() {
		double x = getX();
		double y = getY();
		
		return "[" + super.toString() + ", width: " + width + ", height: " + height + "]";
	}
	
	/**
	 * Scales the rectangle by a given factor 
	 * 
	 * @param factor The scaling factor of the rectangle
	 */
	public void scale(double factor) {
		width *= factor;
		height *= factor;
	}

	
	/**Determines whether this rectangle is touching the other rectangle
	 * 
	 * @param other The other Shape that the method checks if it touches the implicit parameter Shape
	 * @return Whether or not the Rectangles touch
	 * @pre The explicit parameter and implicit parameter must have the same data type
	 */
	@Override
	public boolean isTouching(Shape other) {
		Rectangle r = (Rectangle)other;
		
		double x = getX();
		double y = getY();
		double otherX = r.getX();
		double otherY = r.getY();
		
		return otherX + r.width >= x &&
				otherX <= x + this.width &&
				otherY + r.height >= y &&
				otherY <= y + this.height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
		
}
