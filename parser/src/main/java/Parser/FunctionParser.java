package Parser;

import AST.Expression.Function;
import AST.Expression.Operand;
import AST.Expression.Variable;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class FunctionParser extends TokenConsumer implements Parser<Function> {
    public FunctionParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Function parse() {
        String value = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
        if (peek(DefaultTokenTypes.OPERATOR) == null)
            return new Variable(value);
        Function result = new Variable(value);
        while (peek(DefaultTokenTypes.OPERATOR) != null) {
            Operand operand = Operand.getOperand(consume(DefaultTokenTypes.OPERATOR).getContent());
            String next = consumeAny(DefaultTokenTypes.IDENTIFIER, DefaultTokenTypes.LITERAL).getContent();
            result = result.addVariable(operand, new Variable(next));
        }
        return result;
    }
}
