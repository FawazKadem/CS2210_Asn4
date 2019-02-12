// represents exception that occurs when a key is removed
//from a tree which does not contain the key
public class InexistentKeyException extends Exception {

	public InexistentKeyException(String string) {
		super(string);
	}
}
