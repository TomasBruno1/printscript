package Parser;

public class UnexpectedTokenException extends Throwable {
    public UnexpectedTokenException(String expected, int startCol, int row) {
        super("Expected: \"" + expected + "\" at position: " + startCol + ", line: " + (row + 1));
    }
}
