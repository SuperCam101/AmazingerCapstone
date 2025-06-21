package core;

import java.awt.Color;

import processing.core.PApplet;

/**
 * This class represents an abstract Shape. Focuses on the many properties that a shape have.
 */
public abstract class Shape {
	
	//FIELDS
	private double x, y, width, height;
	private Color fillColor, strokeColor;
	private float strokeWeight;
	
	
	
	
	//CONSTRUCTORS
	/**
	 * Initialized all the values of a shape
	 * @param x The x-coordinate of the shape
	 * @param y The y-coordinate of the shape
	 */
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
		width = 0;
		height = 0;
		fillColor = Color.white;
		strokeColor = Color.black;
		strokeWeight = 1;
	}
	
	
	
	
	//METHODS
	
	
	/**
	 * Draws a Shape on the drawing surface
	 * 
	 * @param surface  The drawing surface that is drawn on
	 * @pre Uses current settings of the PApplet surface
	 * @post modifies the settings of the PApplet surface
	 */
	public void draw(PApplet surface) {
//		surface.fill(fillColor.getRGB());
//		surface.stroke(strokeColor.getRGB());
	}
	
	/**
	 * Prints out the fields of the shape
	 */
	public String toString() {
		return "x: " + x + ". y: " + y;
	}
	
	/**
	 * Checks if a point at (x, y) is inside the shape
	 * 
	 * @param x X coordinate of the point that is being checked
	 * @param y Y coordinate of the point that is being checked
	 * @return
	 */
	public abstract boolean isPointInside(double x, double y);
	
	/**
	 * Checks if the shape is touching another shape
	 * 
	 * @param other The other shape that is being checked
	 * @return Whether or not two shapes are touching
	 */
	public abstract boolean isTouching(Shape other);
	
	/**
	 * Scales the shape by factor
	 * 
	 * @param factor The amount that the shape is scaled by
	 */
	public void scale(double factor) {
		width *= factor;
		height *= factor;
	}
	
	/**
	 * Return the y value of the location of the shape
	 * @return the Shape's y value
	 */
	public double getY() {
		return y;
		
	}
	
	/**
	 * Return the x value of the location of the shape
	 * @return the Shape's x value
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Return the width of the shape
	 * @return the Shape's width
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Return the height of the shape
	 * @return the Shape's height
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Sets the height of the shape
	 * @param height the Shape's height to be set to
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Sets the width of the shape
	 * @param width the Shape's width to be set to
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * Sets the position of the shape to a new location
	 * @param x The x-coordinate of the new location
	 * @param y The y-coordinate of the new location
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set the value x to a different value
	 * @param x The new value that is set to x
	 */
	public void setX(double xVal) {
		x = xVal;
	}
	
	/**
	 * Set the value y to a different value
	 * @param y The new value that is set to y
	 */
	public void setY(double yVal) {
		y =yVal;
	}
	
//	public void setFillColor(Color c) {
//		fillColor = c;
//	}
	
	/**
	 * Sets the outline color of the Shape
	 * @param c The color of the new outline color
	 */
	public void setStrokeColor(Color c) {
		strokeColor = c;
	}
	
	/** Calculates and returns the perimeter of the shape
	 * 
	 * @return The perimeter of the shape
	 */
	public abstract double getPerimeter();
	
	/** Calculates and returns the area of the shape
	 * 
	 * @return The perimeter of the area
	 */
	public abstract double getArea();

}
