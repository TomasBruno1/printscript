package AST.Node;

import AST.Expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Declaration implements Node {
    String varName;
    String type;
    
    Expression value;
    
    public Declaration(String varName, String type) {
        this.varName = varName;
        this.type = type;
    }
    
    @Override
    public void accept(NodeVisitor visitor) {
        
    }
}
