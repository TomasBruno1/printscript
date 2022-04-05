package Commons;

import org.austral.ingsis.printscript.common.TokenType;
import org.jetbrains.annotations.NotNull;

public enum DefaultTokenTypes implements TokenType {
    KEYWORD("KEYWORD"),
    IDENTIFIER("IDENTIFIER"),
    OPERATOR("OPERATOR"),
    LITERAL("LITERAL"),
    SEPARATOR("SEPARATOR"),
    ASSIGN("ASSIGN");

    private String type;

    DefaultTokenTypes(String type) {
        this.type = type;
    }

    @NotNull
    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(@NotNull TokenType tokenType) {
        return type.equals(tokenType.getType());
    }
}
