package KBScript.parser.ast;

// 代码块
public class Block extends Stmt {

    public Block(ASTNode parent) {
        super(parent, ASTNodeTypes.BLOCK, "block");
    }
}
