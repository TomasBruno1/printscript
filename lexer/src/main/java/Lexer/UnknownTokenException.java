package Lexer;

public class UnknownTokenException extends Throwable {
    public UnknownTokenException(String currentString, int from, int fromCol, int col, int row) {
        super("Unknown token: " + currentString + " at " + from + ":" + fromCol + ":" + col + ":" + row);
    }
}
