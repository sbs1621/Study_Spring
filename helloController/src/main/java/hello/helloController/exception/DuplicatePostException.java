package hello.helloController.exception;

public class DuplicatePostException extends RuntimeException {
    public DuplicatePostException(String message) {
        super(message);
    }
}
