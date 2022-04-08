package Lexer;

public class UnknownTokenException extends Throwable {
    public UnknownTokenException(String currentString, int fromCol, int row) {
        super("Unknown token: " + currentString + " at " + fromCol + ":" + row);
    }
}
