package exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String message) {
        super(message);
    }

//    public ElementNotFoundException() {
//        super("Element is not in the repository!");
//    }
}
