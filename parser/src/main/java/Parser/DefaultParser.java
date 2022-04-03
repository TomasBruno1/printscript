package Parser;

import AST.Node.CodeBlock;
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
        CodeBlock program = new CodeBlock();

        Content<String> next;

        while (peek(CoreTokenTypes.EOF) == null) {
            next = peek(DefaultTokenTypes.KEYWORD);
            if (next != null) {
                if (next.getContent().equals("let")) {
                    program.addChild(declarationParser.parse());
                } else if (next.getContent().equals("println")) {
                    program.addChild(printParser.parse());
                } else
                    throw new IllegalStateException("Unexpected keyword: " + next.getContent());
            } else {
                program.addChild(assignmentParser.parse());
            }
            consume(DefaultTokenTypes.SEPARATOR, ";");
        }

        return program;
    }
}
