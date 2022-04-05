package Parser;

import AST.Expression.Expression;
import AST.Node.Declaration;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class DeclarationParser extends TokenConsumer implements Parser<Declaration> {

    private final ExpressionParser expressionParser = new ExpressionParser(getStream());

    public DeclarationParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    // Declaration -> Commons.Keyword Identifier Commons.Separator Commons.Keyword Commons.Separator | Commons.Keyword
    // Identifier Commons.Separator
    // Commons.Keyword Commons.Operator Expr Commons.Separator
    @Override
    public Declaration parse() {
        consume(DefaultTokenTypes.KEYWORD, "let");
        String variable = consume(DefaultTokenTypes.IDENTIFIER).getContent();
        consume(DefaultTokenTypes.SEPARATOR, ":");
        String type = consume(DefaultTokenTypes.KEYWORD).getContent();

        if (peek(DefaultTokenTypes.SEPARATOR, ";") != null) {
            consume(DefaultTokenTypes.SEPARATOR);
            return new Declaration(variable, type);
        }

        consume(DefaultTokenTypes.ASSIGN, "=");
        Expression expr = expressionParser.parse();

        return new Declaration(variable, type, expr);
    }
}
