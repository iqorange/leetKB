package KBScript.parser.ast;

// 条件语句
public class IfStmt extends Stmt {
    public IfStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.IF_STMT, "if");
    }
}
