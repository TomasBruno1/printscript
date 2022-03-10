package Lexer;

import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultLexer implements Lexer {
    @Override
    public List<Token> getTokens(String input) {
        List<Token> tokens = new ArrayList<>();

        for (int i = 0, col = 0, row = 0; i < input.length(); i++) {

            char currentChar = input.charAt(i);

            int from = i;
            int fromCol = col;
            StringBuilder currentString = new StringBuilder();
            while (currentChar != ' ') {

                if (currentChar == '\n') {
                    col = 0;
                    row++;
                }
                else {
                    col++;
                    currentString.append(input.charAt(i));
                }

                i++;
                if (i >= input.length()) {
                    col++;
                    break;
                }

                currentChar = input.charAt(i);
            }

            if(!currentString.isEmpty()) {
                Token token = tokenize(currentString.toString(), from, i, fromCol, col, row);
                tokens.add(token);
            }
            col++;
        }
        return tokens;
    }

    private Token tokenize(String currentString, int from, int i, int fromCol, int col, int row) {
        if(currentString.length() == 1 && isOperator(currentString.charAt(0))) {
            return new Token(DefaultTokenTypes.OPERATOR, from, i, new LexicalRange(fromCol, row, col, row));
        }
        return null;
    }

    private boolean isOperator(char currentChar) {
        return Arrays.stream(Operator.values()).map(operator -> operator.getOperator()).toList().contains(currentChar);
    }
}
