/**
 * @author Fawaz Mohammad
 * 
 * BinaryNode class represents a node of a binary tree which stores
 * a value (Pixel object), its left and right children, and its parent
 */
public class BinaryNode {
	
	private	Pixel value; //data stored in node (Pixel object)
	private BinaryNode left; //left child (BinaryNode
	private BinaryNode right; //right child (BinaryNode)
	private BinaryNode parent; //parent (BinaryNode)
	
	// Constructor to initialize a BinaryNode given:
	// 1. value 2. left child 3. right child 4. parent
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
		
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
		
	}
	
	// Constructor to initialize a leaf node with no parent (root)
	
	public BinaryNode(){
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	// get method for value
	public Pixel getValue() {
		return value;
	}

	// set method for value
	public void setValue(Pixel value) {
		this.value = value;
	}

	// get method for left child
	public BinaryNode getLeft() {
		return left;
	}

	// set method for left child
	public void setLeft(BinaryNode left) {
		this.left = left;
	}

	// get method for right child
	public BinaryNode getRight() {
		return right;
	}

	// set method for right child
	public void setRight(BinaryNode right) {
		this.right = right;
	}

	// get method for parent
	public BinaryNode getParent() {
		return parent;
	}

	// set method for parent
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	
	
	//returns whether this node is a leaf (has no children/data stored) or not
	public boolean isLeaf(){
		if (this.value == null){
			return true;
		}
		else return false;
	}
	


}
