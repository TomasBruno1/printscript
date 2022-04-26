package Commons;

import lombok.Getter;

public interface Keyword {
    enum V1_0 implements Keyword {
        LET("let"),
        PRINTLN("println");

        @Getter
        final String keyword;

        V1_0(String keyword) {
            this.keyword = keyword;
        }
    }

    enum V1_1 implements Keyword {
        CONST("const"),
        READINPUT("readInput"),
        IF("if"),
        ELSE("else");

        @Getter
        final String keyword;

        V1_1(String keyword) {
            this.keyword = keyword;
        }
    }

    String getKeyword();
}
