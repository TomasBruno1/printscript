package Interpreter;

public class UndeclaredVariableException extends RuntimeException {
    public UndeclaredVariableException(String message) {
        super(message);
    }
}
