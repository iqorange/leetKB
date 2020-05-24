package KBScript.parser.ast;

// 声明语句
public class DeclareStmt extends Stmt {
    public DeclareStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.DECLARE_STMT, "declare");
    }
}
