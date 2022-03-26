package Parser;

import AST.Node.Node;
import Lexer.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.CoreTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.Content;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class DefaultParser extends TokenConsumer implements Parser<Node> {

    private final DeclarationParser declarationParser = new DeclarationParser(getStream());
    private final PrintParser printParser = new PrintParser(getStream());
    private final AssignmentParser assignmentParser = new AssignmentParser(getStream());

    public DefaultParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public Node parse() {
        Content<String> next = peek(DefaultTokenTypes.KEYWORD);

        if (next == null) return null;

        if (next.getContent().equals("let")) {
            declarationParser.parse();
        } else if (next.getContent().equals("println")) {
            printParser.parse();
        }

        while (peek(CoreTokenTypes.EOF) != null) {
            next = peek(DefaultTokenTypes.KEYWORD);
            if (next != null) {
                if (next.getContent().equals("let")) {
                    declarationParser.parse();
                } else if (next.getContent().equals("println")) {
                    printParser.parse();
                }
            } else {
                assignmentParser.parse();
            }
        }

        System.out.println(peek(DefaultTokenTypes.SEPARATOR, ";"));
        return null;
    }
}
