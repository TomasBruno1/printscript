package Parser;

import AST.Expression.Expression;
import AST.Node.Assignment;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

// Assignment -> Identifier Commons.Operator Expr Commons.Separator
public class AssignmentParser extends TokenConsumer implements Parser<Assignment> {

    private final ExpressionParser expressionParser = new ExpressionParser(getStream());

    public AssignmentParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Assignment parse() {
        String variable = consume(DefaultTokenTypes.IDENTIFIER).getContent();
        consume(DefaultTokenTypes.ASSIGN);
        Expression expression = expressionParser.parse();
        return new Assignment(variable, expression);
    }
}
