package Parser;

import AST.Expression.Expression;
import AST.Expression.ExpressionOptional;
import AST.Expression.Operand;
import Lexer.DefaultTokenTypes;
import java.util.ArrayList;
import java.util.List;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

// Expr -> Literal ExprOpt* | Identifier ExprOpt*
// ExprOpt -> Operator Literal | Operator Identifier

public class ExpressionParser extends TokenConsumer implements Parser<Expression> {
    public ExpressionParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Expression parse() {
        if (peek(DefaultTokenTypes.IDENTIFIER) != null) {
            String variable = consume(DefaultTokenTypes.IDENTIFIER).getContent();
            return new Expression(variable, exprOpts());
        } else {
            String literal = consume(DefaultTokenTypes.LITERAL).getContent();
            return new Expression(literal, exprOpts());
        }
    }

    private List<ExpressionOptional> exprOpts() {
        List<ExpressionOptional> result = new ArrayList<>();
        while (peek(DefaultTokenTypes.OPERATOR) != null) {
            String operator = consume(DefaultTokenTypes.OPERATOR).getContent();
            if (peek(DefaultTokenTypes.LITERAL) != null) {
                String literal = consume(DefaultTokenTypes.LITERAL).getContent();
                result.add(new ExpressionOptional(Operand.getOperand(operator), literal));
            } else {
                String identifier = consume(DefaultTokenTypes.IDENTIFIER).getContent();
                result.add(new ExpressionOptional(Operand.getOperand(operator), identifier));
            }
        }
        return result;
    }
}
