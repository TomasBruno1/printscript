package Lexer;

import lombok.Getter;

public enum Operator {
    PLUS('+'), MINUS('-'), MULTIPLY('*'), DIVIDE('/'),
    LEFT_PARENTHESIS('('), RIGHT_PARENTHESIS(')'),
    EQUAL('=');

    @Getter
    final char operator;

    Operator(char operator) {
        this.operator = operator;
    }
}
