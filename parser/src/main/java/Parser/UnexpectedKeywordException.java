package Parser;

public class UnexpectedKeywordException extends Throwable {
    public UnexpectedKeywordException(String keyword, int fromCol, int row) {
        super("Unexpected keyword: " + keyword + " at position: " + fromCol + ", line:" + row+1);
    }
}
