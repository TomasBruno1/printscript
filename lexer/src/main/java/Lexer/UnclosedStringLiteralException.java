package Lexer;

public class UnclosedStringLiteralException extends Throwable {
    public UnclosedStringLiteralException(int fromCol, int row) {
        super("Unclosed string literal at position: " + fromCol + ", line: " + row + 1);

    }
}
