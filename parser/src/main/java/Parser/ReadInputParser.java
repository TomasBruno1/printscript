package Parser;

import AST.Expression.Function;
import AST.Expression.ReadInput;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;

public class ReadInputParser extends TokenConsumer implements Parser<ReadInput> {

    AbstractFunctionParser expressionParser;

    public ReadInputParser(TokenIterator stream, AbstractFunctionParser expressionParser) {
        super(stream);
        this.expressionParser = expressionParser;
    }

    @Override
    public ReadInput parse() throws UnexpectedKeywordException, UnexpectedTokenException {
        consume(DefaultTokenTypes.KEYWORD, "readInput");
        if (peek(DefaultTokenTypes.SEPARATOR, "(") == null)
            throw new UnexpectedTokenException(
                    "(",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, "(");
        Function message = expressionParser.parse();
        if (peek(DefaultTokenTypes.SEPARATOR, ")") == null)
            throw new UnexpectedTokenException(
                    ")",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, ")");

        return new ReadInput(message);
    }
}
