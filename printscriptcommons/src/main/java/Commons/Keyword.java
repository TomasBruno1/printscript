package Commons;

import lombok.Getter;

public interface Keyword {
    enum V1_0 implements Keyword {
        LET("let"),
        PRINTLN("println"),
        NUMBER("number"),
        STRING("string");

        @Getter
        final String keyword;

        V1_0(String keyword) {
            this.keyword = keyword;
        }
    }

    enum V1_1 implements Keyword {
        CONST("const"),
        READINPUT("readInput"),
        BOOLEAN("boolean"),
        IF("if"),
        ELSE("else"),
        TRUE("true"),
        FALSE("false");

        @Getter
        final String keyword;

        V1_1(String keyword) {
            this.keyword = keyword;
        }
    }

    String getKeyword();
}
