package Parser;

import AST.Node.Node;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.Content;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class StatementParser extends TokenConsumer implements Parser<Node> {

    private final DeclarationParserV1_1 declarationParserV1_1 = new DeclarationParserV1_1(getStream());
    private final PrintParser printParser = new PrintParser(getStream());
    private final AssignmentParser assignmentParser = new AssignmentParser(
            getStream(),
            new FunctionParserV1_1(getStream())
    );
    private final IfBlockParser ifParser = new IfBlockParser(getStream(), this);

    public StatementParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Node parse() throws UnexpectedKeywordException, UnexpectedTokenException, UnclosedCodeBlockException {
        Node result;

        Content<String> next = peek(DefaultTokenTypes.KEYWORD);
        if (next != null) {
            if (next.getContent().equals("let") || next.getContent().equals("const")) {
                result = declarationParserV1_1.parse();
            } else if (next.getContent().equals("println")) {
                result = printParser.parse();
            } else if (next.getContent().equals("if")) {
                result = ifParser.parse();
                if (peek(DefaultTokenTypes.SEPARATOR, ";") != null)
                    consumeEOL();
                return result;
            } else
                throw new UnexpectedKeywordException(
                        next.getContent(),
                        next.getToken().getRange().getStartCol(),
                        next.getToken().getRange().getStartLine()
                );
        } else {
            result = assignmentParser.parse();
        }
        consumeEOL();
        return result;
    }

    private void consumeEOL() throws UnexpectedTokenException {
        if (peek(DefaultTokenTypes.SEPARATOR, ";") == null)
            throw new UnexpectedTokenException(
                    ";",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, ";");
    }
}
