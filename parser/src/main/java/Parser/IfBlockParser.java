package Parser;

import AST.Expression.Function;
import AST.Node.CodeBlock;
import AST.Node.IfBlock;
import Commons.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.CoreTokenTypes;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class IfBlockParser extends TokenConsumer implements Parser<IfBlock> {
    private final FunctionParser expressionParser = new FunctionParser(getStream());
    private final StatementParser statementParser = new StatementParser(getStream());

    public IfBlockParser(@NotNull TokenIterator stream) {
        super(stream);
    }

    @Override
    public IfBlock parse() throws UnexpectedKeywordException, UnexpectedTokenException, UnclosedCodeBlockException {
        consume(DefaultTokenTypes.KEYWORD, "if");
        if (peek(DefaultTokenTypes.SEPARATOR, "(") == null)
            throw new UnexpectedTokenException(
                    "(",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, "(");
        Function condition = expressionParser.parse();
        if (peek(DefaultTokenTypes.SEPARATOR, ")") == null)
            throw new UnexpectedTokenException(
                    ")",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, ")");

        CodeBlock ifBlock = getCodeBlock();
        CodeBlock elseBlock = new CodeBlock();
        if (peek(DefaultTokenTypes.KEYWORD, "else") != null) {
            consume(DefaultTokenTypes.KEYWORD, "else");
            elseBlock = getCodeBlock();
        }

        return new IfBlock(condition, ifBlock, elseBlock);
    }

    @NotNull
    private CodeBlock getCodeBlock()
            throws UnexpectedTokenException,
                UnclosedCodeBlockException,
                UnexpectedKeywordException {
        if (peek(DefaultTokenTypes.SEPARATOR, "{") == null)
            throw new UnexpectedTokenException(
                    "{",
                    current().getRange().getStartCol(),
                    current().getRange().getStartLine()
            );
        consume(DefaultTokenTypes.SEPARATOR, "{");

        CodeBlock result = new CodeBlock();
        while (peek(DefaultTokenTypes.SEPARATOR, "}") == null) {
            if (peek(CoreTokenTypes.EOF) != null) {
                throw new UnclosedCodeBlockException("Code block not closed with '}'");
            }
            result.addChild(statementParser.parse());
        }
        consume(DefaultTokenTypes.SEPARATOR, "}");
        return result;
    }

}
