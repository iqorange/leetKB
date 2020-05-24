package KBScript.parser.ast;

// 函数定义语句
public class FunctionDeclareStmt extends Stmt{
    public FunctionDeclareStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.FUNCTION_DECLARE_STMT, "func");
    }
}
