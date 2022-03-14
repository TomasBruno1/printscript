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
                tokens.add(tokenize(currentString.toString(), from, fromCol, col, row));
            }
        }
        return tokens;
    }

    private Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        if(isOperator(currentString)) {
            return new Token(DefaultTokenTypes.OPERATOR, from, from + 1, new LexicalRange(fromCol, row, col, row));
        } else if(isSeparator(currentString)) {
            return new Token(DefaultTokenTypes.SEPARATOR, from, from + 1, new LexicalRange(fromCol, row, col, row));
        } else if (isKeyword(currentString)) {
            return new Token(DefaultTokenTypes.KEYWORD, from, from + currentString.length(), new LexicalRange(fromCol, row, col, row));
        } else if (isLiteral(currentString)){
            return new Token(DefaultTokenTypes.LITERAL, from, from + currentString.length(), new LexicalRange(fromCol, row, col, row));
        } else if (isIdentifier(currentString)){
            return new Token(DefaultTokenTypes.IDENTIFIER, from, from + currentString.length(), new LexicalRange(fromCol, row, col, row));
        }
        throw new IllegalArgumentException("Unknown token: " + currentString);
    }

    private boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.values()).map(keyword -> keyword.getKeyword()).toList().contains(currentString);
    }

    private boolean isOperator(String currentString) {
        return (currentString.length() == 1) && Arrays.stream(Operator.values()).map(operator -> operator.getOperator()).toList().contains(currentString.charAt(0));
    }

    private boolean isSeparator(String currentString) {
        return (currentString.length() == 1) && Arrays.stream(Separator.values()).map(separator -> separator.getSeparator()).toList().contains(currentString.charAt(0));
    }

    private boolean isLiteral (String currentString){
        if (currentString.charAt(0) == '"') return currentString.charAt(currentString.length()-1) == '"';
        else if (currentString.charAt(0) == '\'') return currentString.charAt(currentString.length()-1) == '\'';
        else return (currentString.matches("[0-9]{1,9}(\\.[0-9]*)?"));
    }

    private boolean isIdentifier (String currentString){
        return (currentString.matches("[a-zA-Z_][a-zA-Z0-9_]*"));
    }
}
