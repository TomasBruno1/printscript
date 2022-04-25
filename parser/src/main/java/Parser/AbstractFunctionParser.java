package Parser;

import AST.Expression.Function;
import AST.Expression.Operand;
import AST.Expression.Variable;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractFunctionParser extends TokenConsumer implements Parser<Function> {
    public AbstractFunctionParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Function parse() throws UnexpectedTokenException, UnexpectedKeywordException {
        if (peekAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL) == null)
            throw new UnexpectedTokenException(
                    "identifier/literal",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        String value = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
        if (peek(DefaultTokenTypes.OPERATOR) == null)
            return new Variable(value);
        Function result = new Variable(value);
        while (peek(DefaultTokenTypes.OPERATOR) != null) {
            Operand operand = Operand.getOperand(consume(DefaultTokenTypes.OPERATOR).getContent());
            if (peekAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL) == null)
                throw new UnexpectedTokenException(
                        "identifier/literal",
                        current().getRange().getStartCol(),
                        current().getRange().getStartLine()
                );
            String next = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
            result = result.addVariable(operand, new Variable(next));
        }
        return result;
    }
}
