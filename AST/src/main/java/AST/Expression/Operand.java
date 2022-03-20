package AST.Expression;

public enum Operand {
    SUM("+"), SUB("-"), MUL("*"), DIV("/");

    private final String string;

    Operand(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
