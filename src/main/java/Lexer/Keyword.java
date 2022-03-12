package Lexer;

import lombok.Getter;

public enum Keyword {
    LET("let"),
    PRINTLN("println");

    @Getter
    final String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }
}
