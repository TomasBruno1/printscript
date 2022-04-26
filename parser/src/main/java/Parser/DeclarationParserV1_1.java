package Parser;

import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DeclarationParserV1_1 extends AbstractDeclarationParser {

    public DeclarationParserV1_1(@NotNull TokenIterator stream) {
        super(stream);
        functionParser = new FunctionParserV1_1(stream);
    }

    // Declaration -> Commons.Keyword Identifier Commons.Separator Commons.Keyword Commons.Separator | Commons.Keyword
    // Identifier Commons.Separator
    // Commons.Keyword Commons.Operator Expr Commons.Separator

    @Override
    protected boolean consumeDeclarationKeyword() {
        boolean isConst = false;
        if (Objects.requireNonNull(peek(DefaultTokenTypes.KEYWORD)).getContent().equals("const")) {
            consume(DefaultTokenTypes.KEYWORD, "const");
            isConst = true;
        } else {
            consume(DefaultTokenTypes.KEYWORD, "let");
        }
        return isConst;
    }
}
