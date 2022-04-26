package Commons;

import lombok.Getter;

public interface Separator {
    enum V1_0 implements Separator {
        EOL(';'),
        LEFT_PARENTHESIS('('),
        RIGHT_PARENTHESIS(')'),
        TYPE(':');

        @Getter
        final char symbol;

        V1_0(char symbol) {
            this.symbol = symbol;
        }
    }

    enum V1_1 implements Separator {
        LEFT_BRACE('{'),
        RIGHT_BRACE('}');

        @Getter
        final char symbol;

        V1_1(char symbol) {
            this.symbol = symbol;
        }
    }

    char getSymbol();

}
