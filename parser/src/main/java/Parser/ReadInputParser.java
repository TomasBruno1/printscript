package Parser;

import AST.Node.ReadInput;
import org.austral.ingsis.printscript.common.TokenConsumer;
import org.austral.ingsis.printscript.parser.TokenIterator;

public class ReadInputParser extends TokenConsumer implements Parser<ReadInput> {
    public ReadInputParser(TokenIterator stream) {
        super(stream);
    }

    @Override
    public ReadInput parse() throws UnexpectedKeywordException, UnexpectedTokenException {
        return null;
    }
}
