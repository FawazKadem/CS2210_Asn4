
/**
 * @author Fawaz Mohammad class BinarySearchTree represents a Tree Data
 *         Structure in which: 1. Each node has a maximum of two children 2.
 *         Each node is larger than its left child and larger than its right
 *         child (if it does have children) 3. Each non-leaf node stores a
 *         value, a left child, a right child, and its parent
 * 
 *         Has methods to get, add, and remove nodes Has methods to find the
 *         smallest and largest node in tree/subtree Has methods to find
 *         successor of predecessor of a given node
 * 
 */
public class BinarySearchTree {

	// root variable represents root of this tree
	private BinaryNode root;

	// Constructor to initialize a tree with an empty root
	public BinarySearchTree() {

		root = new BinaryNode();

	}

	// input: root of tree, key to be searched for
	// output: Pixel value corresponding to the location if it exists
	// Null if location is not stored in tree
	public Pixel get(BinaryNode r, Location key) {

		BinaryNode nodeToGet = getNode(this.root, key);

		if (nodeToGet.isLeaf()) {
			return null;
		}
		return nodeToGet.getValue();
	}

	// input: root of tree, key to be searched for
	// output: Node corresponding to key (can be a leaf)
	// note:private
	private BinaryNode getNode(BinaryNode r, Location key) {

		if (r.isLeaf())
			return r;

		Location currentLoc = r.getValue().getLocation();

		// recursively searches tree to find node/leaf where node should be
		if (currentLoc.compareTo(key) == 0) {
			return r;
		} else if (key.compareTo(currentLoc) == 1) {
			return getNode(r.getRight(), key);
		} else {
			return getNode(r.getLeft(), key);
		}

	}

	// input: root of tree, data to insert into tree
	// output: Adds new node with data if it doesn't already exist within tree
	// throws duplicated key error if data is already stored in tree
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

		Location key = data.getLocation();

		if (root.isLeaf()) { // adds value and children to root if tree is empty
			root.setLeft(new BinaryNode(null, null, null, root));
			root.setRight(new BinaryNode(null, null, null, root));
			root.setValue(data);

		} else {

			Location currentLocation = r.getValue().getLocation();

			if (currentLocation.compareTo(key) == 0) {
				throw new DuplicatedKeyException("Cannot insert key because it already exists in tree");
			} else {

				// searches tree until it finds the leaf where the node should
				// be
				if (key.compareTo(currentLocation) == -1) {
					if (r.getLeft().isLeaf()) {
						r.getLeft().setParent(r);
						r.getLeft().setLeft(new BinaryNode(null, null, null, r.getLeft()));
						r.getLeft().setRight(new BinaryNode(null, null, null, r.getLeft()));
						r.getLeft().setValue(data);

					} else
						put(r.getLeft(), data);

				} else {
					if (r.getRight().isLeaf()) {
						r.getRight().setParent(r);
						r.getRight().setLeft(new BinaryNode(null, null, null, r.getRight()));
						r.getRight().setRight(new BinaryNode(null, null, null, r.getRight()));
						r.getRight().setValue(data);
					} else
						put(r.getRight(), data);
				}

			}
		}

	}

	// input: root of tree and key
	// output: removes node that stores the key or error if key isnt stored in
	// tree
	public void remove(BinaryNode r, Location key) throws InexistentKeyException, EmptyTreeException {

		// node to remove and its parent node
		BinaryNode NodeToRemove = getNode(r, key);
		BinaryNode ParentNode;

		// node not found

		if (NodeToRemove.isLeaf()) {
			throw new InexistentKeyException("Key is not in tree");
		}

		ParentNode = NodeToRemove.getParent();

		// if node to remove has no children
		if (NodeToRemove.getLeft().isLeaf() && NodeToRemove.getRight().isLeaf()) {

			// no children and is root
			if (ParentNode == null) {
				root.setLeft(null);
				root.setRight(null);
				root.setValue(null);
			}

			// no children and is not root
			else {
				if (ParentNode.getLeft() == NodeToRemove) {
					ParentNode.setLeft(new BinaryNode(null, null, null, ParentNode));
				} else {
					ParentNode.setRight(new BinaryNode(null, null, null, ParentNode));
				}
			}

		}

		// Node has two children

		else if (!(NodeToRemove.getLeft().isLeaf()) && !(NodeToRemove.getRight().isLeaf())) {
			Pixel smallestOfRightSubtree = (this.smallest(NodeToRemove.getRight()));
			Location smallestLocation = smallestOfRightSubtree.getLocation();

			this.remove(this.root, smallestLocation);

			NodeToRemove.setValue(smallestOfRightSubtree);

		}

		// node has one child
		else if (!(NodeToRemove.getLeft().isLeaf()) || !(NodeToRemove.getRight().isLeaf())) {

			// child is left child
			if (!(NodeToRemove.getLeft().isLeaf())) {
				if (ParentNode.getRight() == NodeToRemove)
					ParentNode.setRight(NodeToRemove.getLeft());
				else if (ParentNode.getLeft() == NodeToRemove)
					ParentNode.setLeft(NodeToRemove.getLeft());
			}

			// child is right child
			if (!(NodeToRemove.getRight().isLeaf())) {
				if (ParentNode.getRight() == NodeToRemove) {
					ParentNode.setRight(NodeToRemove.getRight());
					NodeToRemove.getRight().setParent(ParentNode);
				} else if (ParentNode.getLeft() == NodeToRemove) {
					ParentNode.setLeft(NodeToRemove.getRight());
					NodeToRemove.getLeft().setParent(ParentNode);
				}

			}

		}

	}

	// input: root of tree
	// output: smallest value in the tree
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {

		BinaryNode currentNode = r;

		if (currentNode.isLeaf()) {
			throw new EmptyTreeException("Tree is empty");
		} else {
			// finds left most node
			while (!(currentNode.isLeaf())) {
				currentNode = currentNode.getLeft();
			}

			currentNode = currentNode.getParent();
			return currentNode.getValue();

		}

	}

	// input: root of tree
	// output: largest value in the tree
	public Pixel largest(BinaryNode r) throws EmptyTreeException {

		BinaryNode currentNode = r;

		if (currentNode.isLeaf()) {
			throw new EmptyTreeException("Tree is empty");
		} else {
			// finds right most node
			while (!(currentNode.isLeaf())) {
				currentNode = currentNode.getRight();
			}

			currentNode = currentNode.getParent();
			return currentNode.getValue();

		}

	}

	// input: root of tree and key
	// output: the next highest value in the tree or null if key is equal or
	// higher than the highest value
	// note: inputted key does not have to be stored in tree
	// throws emptytreeexception since it uses smallest method which throws this
	// exception
	public Pixel successor(BinaryNode r, Location key) throws EmptyTreeException {

		// empty tree - no successor
		if (r.isLeaf()) {
			return null;
		}

		// sets node and parent node of node
		BinaryNode nodeToFindSucOf = getNode(r, key);
		BinaryNode parentNode = nodeToFindSucOf.getParent();

		// if node isn't leaf and its right child isnt a leaf
		// then the successor is the smallest node in the right subtree
		if (!(nodeToFindSucOf.isLeaf()) && !((nodeToFindSucOf.getRight().isLeaf()))) {
			return (this.smallest(nodeToFindSucOf.getRight()));
		}

		else {
			if (parentNode == null)
				return null;

			// if node or its right child are a leaf
			// then its successor is its most recent ancestor that is a left
			// child
			while (parentNode != null && parentNode.getLeft() != nodeToFindSucOf) {
				nodeToFindSucOf = parentNode;
				parentNode = parentNode.getParent();
			}

			if (parentNode == null)
				return null;
			return parentNode.getValue();

		}
	}

	// input: root of tree and key
	// output: the next lowest value in the tree or null if key is equal or
	// lower than the highest value
	// note: input key does not have to be stored in tree
	// throws emptytreeexception since it uses largest method which throws this
	// exception
	public Pixel predecessor(BinaryNode r, Location key) throws EmptyTreeException {

		// empty tree - no successor
		if (r.isLeaf()) {
			return null;
		}

		// sets node and parent node of node
		BinaryNode nodeToFindPredOf = getNode(r, key);
		BinaryNode parentNode = nodeToFindPredOf.getParent();

		// if node isn't leaf and its left child isnt a leaf
		// then the predecessor is the largest node in the left subtree
		if (!(nodeToFindPredOf.isLeaf()) && !((nodeToFindPredOf.getLeft().isLeaf()))) {
			return (this.largest(nodeToFindPredOf.getLeft()));
		}

		else {
			if (parentNode == null)
				return null;

			// if node or its left child are a leaf
			// then its predecessor is its most recent ancestor that is a right
			// child
			while (parentNode != null && parentNode.getRight() != nodeToFindPredOf) {
				nodeToFindPredOf = parentNode;
				parentNode = parentNode.getParent();
			}

			if (parentNode == null)
				return null;
			return parentNode.getValue();

		}
	}

	// get method for root of tree
	public BinaryNode getRoot() {
		return this.root;
	}

}
