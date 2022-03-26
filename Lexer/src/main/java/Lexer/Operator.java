package Lexer;

import lombok.Getter;

public enum Operator {
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    EQUALS('=');

    @Getter final char operator;

    Operator(char operator) {
        this.operator = operator;
    }
}
