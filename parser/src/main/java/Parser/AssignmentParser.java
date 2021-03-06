package Parser;

import AST.Expression.Function;
import AST.Node.Assignment;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

// Assignment -> Identifier Commons.Operator Expr Commons.Separator
public class AssignmentParser extends TokenConsumer implements Parser<Assignment> {

    private final AbstractFunctionParser expressionParser;

    public AssignmentParser(@NotNull TokenIterator stream, AbstractFunctionParser expressionParser) {
        super(stream);
        this.expressionParser = expressionParser;
    }

    @Override
    public Assignment parse() throws UnexpectedTokenException, UnexpectedKeywordException {
        if (peek(DefaultTokenTypes.IDENTIFIER) == null)
            throw new UnexpectedTokenException(
                    "identifier",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        String variable = consume(DefaultTokenTypes.IDENTIFIER).getContent();
        if (peek(DefaultTokenTypes.ASSIGN) == null)
            throw new UnexpectedTokenException(
                    "=",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.ASSIGN);
        Function function = expressionParser.parse();
        return new Assignment(variable, function);
    }
}
