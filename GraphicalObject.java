/**
 * 
 * @author Fawaz Mohammad
 *
 *         GraphicalObject class represents a graphic contained in a rectangular
 *         box Each gobj is represented with a id number, width, height, offset,
 *         type, and a tree storing its pixels
 */
public class GraphicalObject {

	private int id; // identifier
	private int width; // width of containing rectangle
	private int height; // height on containing rectangle
	private String type; // whether this is a fixed, user, computer, or target
	// object
	private Location pos; // offset
	private BinarySearchTree bst; // BST storing pixels

	// constuctor to initialize a graphical object
	// with a id, width, height, type, offset, and bst
	public GraphicalObject(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.bst = new BinarySearchTree();

	}

	// adds pixel to the BST of this object
	// catches duplicated key exception thrown by the bst's put method
	public void addPixel(Pixel pix) throws DuplicatedKeyException {

		try {
			bst.put(bst.getRoot(), pix);
		} catch (DuplicatedKeyException e) {
			System.out.println("Error when inserting pixel");
		}

	}

	// checks whether or not this graphical object
	// intersects with an inputed graphical object
	public boolean intersects(GraphicalObject gobj) {
		// first check if rectangles intersect

		// offset of both this and other object
		Location thisOffset = pos;
		Location otherOffset = gobj.getOffset();

		// location of both this and other object
		Location thisLocation;
		Location otherLocation;

		// current pixel to compare
		Pixel currentPix;

		// initialize top left corner coordinates (offset) of both objects
		int xThisGobj = thisOffset.xCoord();
		int xOtherGobj = otherOffset.xCoord();
		int yThisGobj = thisOffset.yCoord();
		int yOtherGobj = otherOffset.yCoord();

		// horizontal space that each object uses
		int this_OffsetWidth = xThisGobj + width;
		int other_OffsetWidth = xOtherGobj + gobj.getWidth();

		// vertical space that each object uses
		int this_OffsetHeight = yThisGobj + height;
		int other_OffsetHeight = yOtherGobj + gobj.getHeight();

		// checks if containing rectangles overlap
		if (yThisGobj <= yOtherGobj) {
			if (yOtherGobj > this_OffsetHeight)
				return false;
		} else if (yThisGobj > other_OffsetHeight)
			return false;

		if (xThisGobj <= xOtherGobj) {
			if (xOtherGobj > this_OffsetWidth)
				return false;
		} else if (xThisGobj > other_OffsetWidth)
			return false;
		

		// iterates over ever pixel in this objects tree
		// checks if the location stored in pixel exists
		// in the inputed tree's BST

		try {
			currentPix = bst.smallest(bst.getRoot());
			while (currentPix != null) {
				thisLocation = currentPix.getLocation();
				int currX = thisLocation.xCoord();
				int currY = thisLocation.yCoord();
				int otherX = currX + xThisGobj - xOtherGobj;
				int otherY = currY + yThisGobj - yOtherGobj;
				otherLocation = new Location(otherX, otherY);

				if (gobj.bst.get(gobj.bst.getRoot(), otherLocation) != null) {
					return true;
				}

				else {
					currentPix = bst.successor(bst.getRoot(), thisLocation);
				}

			}

		} catch (EmptyTreeException e) {
			System.out.println("Tree is empty");
		}

		return false;

	}

	// get method for offset
	public Location getOffset() {
		return pos;
	}

	// set method for offset
	public void setOffset(Location pos) {
		this.pos = pos;
	}

	// get method for id
	public int getId() {
		return id;
	}

	// get method for width
	public int getWidth() {
		return width;
	}

	// get method for height
	public int getHeight() {
		return height;
	}

	// get method for type
	public String getType() {
		return type;
	}

	// set method for type
	public void setType(String type) {
		this.type = type;
	}

}
