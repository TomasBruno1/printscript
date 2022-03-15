package Lexer;

import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultLexer implements Lexer {

    private final Tokenizer tokenizer = new DefaultTokenizer();

    @Override
    public List<Token> getTokens(String input) {
        List<Token> tokens = new ArrayList<>();

        int length = input.length();
        for (int i = 0, col = 0, row = 0; i < length; i++) {

            char currentChar = input.charAt(i);
            int from = i;
            int fromCol = col;
            col++;

            StringBuilder currentString = new StringBuilder();
            currentString.append(currentChar);
            while (true){
                if (currentChar == '"' || currentChar == '\'') {
                    int nextQuoteMark = currentChar == '"' ? (input.substring(i+1)).indexOf('"') : (input.substring(i+1)).indexOf('\'');
                    if(nextQuoteMark == -1) throw new IllegalArgumentException("Unclosed string literal");
                    currentString.append(input, i+1, i+nextQuoteMark+2);
                    i = i + nextQuoteMark + 1;
                    col = col + nextQuoteMark + 1;
                    break;
                }
                if (!Character.isLetterOrDigit(currentChar) && currentChar != '.' && currentChar != '_' ) break;

                char nextChar;
                if (i < length -1) {
                    nextChar = input.charAt(i + 1);
                    if (Character.isLetterOrDigit(nextChar) || nextChar == '.' || nextChar == '_') {
                        currentString.append(nextChar);
                        currentChar = nextChar;
                        col++;
                        i++;
                        continue;
                    }
                }
                break;
            }

            if (currentChar == '\n') {
                col = 0;
                row++;
            }

            if(!currentString.isEmpty() && currentString.charAt(0) > 32) {
                tokens.add(tokenizer.tokenize(currentString.toString(), from, fromCol, col, row));
            }
        }
        return tokens;
    }

}
