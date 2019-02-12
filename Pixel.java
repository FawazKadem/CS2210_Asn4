/*
 * author: Fawaz Mohammad
 * represents the data item which wil be stored in the binary search tree
 * Each items consists of a color and a Location object
 */

public class Pixel {

	private int color; // color of pixel.
	
	private Location location; // location(x and y coordinate) of pixel
	
	//constructor to initialize a new Pixel object with a given location and color
	public Pixel(Location p, int color){
			this.location = p;
			this.color = color;
		}
		
		
	// get method for color
	public int getColor() {
		return color;
	}
	
	//set method for color
	public void setColor(int color) {
		this.color = color;
	}
	
	//get method for location
	public Location getLocation() {
		return location;
	}
	
	//set method for location
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
