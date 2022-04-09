package Parser;

import AST.Expression.Function;
import AST.Node.Print;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class PrintParser extends TokenConsumer implements Parser<Print> {
    private final FunctionParser expressionParser = new FunctionParser(getStream());

    public PrintParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Print parse() throws UnexpectedTokenException {
        consume(DefaultTokenTypes.KEYWORD, "println");
        if(peek(DefaultTokenTypes.SEPARATOR, "(") == null) throw new UnexpectedTokenException("(", current().getRange().getStartCol(), current().getRange().getStartLine());
        consume(DefaultTokenTypes.SEPARATOR, "(");
        Function content = expressionParser.parse();
        if(peek(DefaultTokenTypes.SEPARATOR, ")") == null) throw new UnexpectedTokenException(")", current().getRange().getStartCol(), current().getRange().getStartLine());
        consume(DefaultTokenTypes.SEPARATOR, ")");
        return new Print(content);
    }
}
