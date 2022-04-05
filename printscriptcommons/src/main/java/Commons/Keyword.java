package Commons;

import lombok.Getter;

public enum Keyword {
    LET("let"),
    PRINTLN("println"),
    NUMBER("number"),
    STRING("string");

    @Getter
    final String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }
}
