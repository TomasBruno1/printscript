package Parser;

import AST.Node.CodeBlock;
import AST.Node.Node;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.CoreTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.Content;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class ProgramParserV1_1 extends TokenConsumer implements Parser<Node> {
    private final DeclarationParserV1_1 declarationParserV1_1 = new DeclarationParserV1_1(getStream());
    private final PrintParser printParser = new PrintParser(getStream());
    private final AssignmentParser assignmentParser = new AssignmentParser(getStream());
    private final ReadInputParser readInputParser = new ReadInputParser(getStream());

    public ProgramParserV1_1(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Node parse() throws UnexpectedKeywordException, UnexpectedTokenException {
        CodeBlock program = new CodeBlock();

        Content<String> next;

        while (peek(CoreTokenTypes.EOF) == null) {
            next = peek(DefaultTokenTypes.KEYWORD);
            if (next != null) {
                if (next.getContent().equals("let") || next.getContent().equals("const")) {
                    program.addChild(declarationParserV1_1.parse());
                } else if (next.getContent().equals("println")) {
                    program.addChild(printParser.parse());
                } else if (next.getContent().equals("readInput")) {
                    program.addChild(readInputParser.parse());
                } else
                    throw new UnexpectedKeywordException(
                            next.getContent(),
                            next.getToken().getRange().getStartCol(),
                            next.getToken().getRange().getStartLine()
                    );
            } else {
                program.addChild(assignmentParser.parse());
            }
            if (peek(DefaultTokenTypes.SEPARATOR, ";") == null)
                throw new UnexpectedTokenException(
                        ";",
                        current().getRange().getStartCol(),
                        current().getRange().getStartLine()
                );
            consume(DefaultTokenTypes.SEPARATOR, ";");
        }

        return program;
    }
}
