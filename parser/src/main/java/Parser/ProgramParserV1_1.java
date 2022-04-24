package Parser;

import AST.Node.CodeBlock;
import AST.Node.Node;
import org.austral.ingsis.printscript.common.CoreTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class ProgramParserV1_1 extends TokenConsumer implements Parser<Node> {

    private final StatementParser statementParser = new StatementParser(getStream());

    public ProgramParserV1_1(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Node parse() throws UnexpectedKeywordException, UnexpectedTokenException, UnclosedCodeBlockException {
        CodeBlock program = new CodeBlock();

        while (peek(CoreTokenTypes.EOF) == null) {
            program.addChild(statementParser.parse());
        }

        return program;
    }
}
