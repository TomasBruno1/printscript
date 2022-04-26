package Parser;

import AST.Expression.Function;
import AST.Expression.Operand;
import AST.Expression.Variable;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class FunctionParserV1_1 extends AbstractFunctionParser {

    ReadInputParser readInputParser = new ReadInputParser(getStream(), this);

    public FunctionParserV1_1(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Function parse() throws UnexpectedTokenException, UnexpectedKeywordException {
        Function result;
        if (peek(DefaultTokenTypes.KEYWORD, "readInput") != null) {
            result = readInputParser.parse();
        } else if (peekAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL) != null) {
            String value = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
            if (peek(DefaultTokenTypes.OPERATOR) == null)
                return new Variable(value);
            result = new Variable(value);
        } else
            throw new UnexpectedTokenException(
                    "identifier/literal",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );

        while (peek(DefaultTokenTypes.OPERATOR) != null) {
            Operand operand = Operand.getOperand(consume(DefaultTokenTypes.OPERATOR).getContent());
            if (peekAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL) != null) {
                String next = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
                result = result.addVariable(operand, new Variable(next));
            } else if (peek(DefaultTokenTypes.KEYWORD, "readInput") != null) {
                result = result.addVariable(operand, readInputParser.parse());
            } else
                throw new UnexpectedTokenException(
                        "identifier/literal",
                        current().getRange().getStartCol(),
                        current().getRange().getStartLine()
                );

        }
        return result;
    }
}
