/*
 * author: Fawaz Mohammad
 * Represents position of pixel using its x and y coordinate.
 */
public class Location {

	private int x; //x coordinate
	private int y; //y coordinate

	//constructor to initialize a Location object and give it an x and y value
	public Location(int x, int y) {

		this.x = x;
		this.y = y;

	}

	// get method for x coordinate
	public int xCoord() {
		return x;
	}

	// get method for y coordinate
	public int yCoord() {
		return y;
	}

	// determines whether a location is larger or smaller than this location
	// uses column order
	public int compareTo(Location p) {
		int result;

		if (this.x > p.xCoord()) {
			result = 1;
		} else if (this.x < p.xCoord()) {
			result = -1;
		} else { //if x coordinates are equal, compares y coordinates
			if (this.y > p.yCoord()) {
				result = 1;
			} else if (this.y < p.yCoord()) {
				result = -1;
			} else
				result = 0;

		}

		return result;
	}
}
