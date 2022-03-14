package AST.Statement;

import lombok.Getter;

import java.util.List;

// Compound statement -> { statement, statement, ... } ;
@Getter
public class Compound implements Statement {
    List<Statement> statements;

    public Compound(List<Statement> statements) {
        this.statements = statements;
    }
}
