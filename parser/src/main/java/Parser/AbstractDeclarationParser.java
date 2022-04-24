package Parser;

import AST.Expression.Function;
import AST.Node.Declaration;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;

public abstract class AbstractDeclarationParser extends TokenConsumer implements Parser<Declaration> {
    private final FunctionParser functionParser = new FunctionParser(getStream());

    public AbstractDeclarationParser(TokenIterator stream) {
        super(stream);
    }

    public Declaration parse() throws UnexpectedTokenException {
        boolean isConst = consumeDeclarationKeyword();
        String variable = consumeIdentifier();
        consumeTypeSeparator();
        String type = consumeTypeKeyword();

        if (peek(DefaultTokenTypes.SEPARATOR, ";") != null) {
            return new Declaration(variable, type);
        }

        consumeAssignmentOperator();

        Function function = functionParser.parse();

        return new Declaration(variable, type, isConst, function);
    }

    protected boolean consumeDeclarationKeyword() throws UnexpectedTokenException {
        consume(DefaultTokenTypes.KEYWORD, "let");
        if (peek(DefaultTokenTypes.IDENTIFIER) == null)
            throw new UnexpectedTokenException(
                    "identifier",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        return false;
    };

    protected void consumeAssignmentOperator() throws UnexpectedTokenException {
        if (peek(DefaultTokenTypes.ASSIGN, "=") == null)
            throw new UnexpectedTokenException(
                    "=",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.ASSIGN, "=");
    }

    protected String consumeTypeKeyword() throws UnexpectedTokenException {
        if (peek(DefaultTokenTypes.TYPE) == null)
            throw new UnexpectedTokenException(
                    "type",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        return consume(DefaultTokenTypes.TYPE).getContent();
    }

    protected void consumeTypeSeparator() throws UnexpectedTokenException {
        if (peek(DefaultTokenTypes.SEPARATOR) == null)
            throw new UnexpectedTokenException(
                    ":",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, ":");
    }

    protected String consumeIdentifier() {
        return consume(DefaultTokenTypes.IDENTIFIER).getContent();
    };

}
