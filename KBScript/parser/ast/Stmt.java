package KBScript.parser.ast;

// 语句
public abstract class Stmt extends ASTNode {
    public Stmt(ASTNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }
}
