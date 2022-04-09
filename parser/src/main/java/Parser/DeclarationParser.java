package Parser;

import AST.Expression.Function;
import AST.Node.Declaration;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class DeclarationParser extends TokenConsumer implements Parser<Declaration> {

    private final FunctionParser functionParser = new FunctionParser(getStream());

    public DeclarationParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    // Declaration -> Commons.Keyword Identifier Commons.Separator Commons.Keyword Commons.Separator | Commons.Keyword
    // Identifier Commons.Separator
    // Commons.Keyword Commons.Operator Expr Commons.Separator
    @Override
    public Declaration parse() throws UnexpectedTokenException {
        consume(DefaultTokenTypes.KEYWORD, "let");
        if (peek(DefaultTokenTypes.IDENTIFIER) == null) throw new UnexpectedTokenException("identifier", current().getRange().getStartCol(), current().getRange().getStartLine());
        String variable = consume(DefaultTokenTypes.IDENTIFIER).getContent();
        if (peek(DefaultTokenTypes.SEPARATOR) == null) throw new UnexpectedTokenException(":", current().getRange().getStartCol(), current().getRange().getStartLine());
        consume(DefaultTokenTypes.SEPARATOR, ":");
        if (peek(DefaultTokenTypes.KEYWORD) == null) throw new UnexpectedTokenException("type", current().getRange().getStartCol(), current().getRange().getStartLine());
        String type = consume(DefaultTokenTypes.KEYWORD).getContent();

        if (peek(DefaultTokenTypes.SEPARATOR, ";") != null) {
            return new Declaration(variable, type);
        }

        if (peek(DefaultTokenTypes.ASSIGN, "=") == null) throw new UnexpectedTokenException("=", current().getRange().getStartCol(), current().getRange().getStartLine());
        consume(DefaultTokenTypes.ASSIGN, "=");
        Function function = functionParser.parse();

        return new Declaration(variable, type, function);
    }
}
