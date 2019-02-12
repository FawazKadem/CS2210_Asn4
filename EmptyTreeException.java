// represents exception that occurs when a method that requires
// a tree with at least one non leaf node
// is perfomed on a tree with only a leaf node
public class EmptyTreeException extends Exception {
	public EmptyTreeException(String string) {
		super(string);
	}
}
