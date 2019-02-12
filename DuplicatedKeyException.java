// represents exception that occurs when a key is placed into a tree that already stores that key
public class DuplicatedKeyException extends Exception {

	public DuplicatedKeyException(String string) {
		super(string);
	}

}
