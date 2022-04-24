package Parser;

import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class DeclarationParserV1_0 extends AbstractDeclarationParser {

    public DeclarationParserV1_0(@NotNull TokenIterator stream) {
        super(stream);
    }

    // Declaration -> Commons.Keyword Identifier Commons.Separator Commons.Keyword Commons.Separator | Commons.Keyword
    // Identifier Commons.Separator
    // Commons.Keyword Commons.Operator Expr Commons.Separator

}
