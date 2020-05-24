package KBScript.parser.ast;

import KBScript.lexer.Token;

import java.util.ArrayList;
import java.util.List;

// 抽象语法树节点
public abstract class ASTNode {
    // 树
    protected ArrayList<ASTNode> children = new ArrayList<>();
    protected ASTNode parent;
    protected ASTNodeTypes type;

    // 关键信息
    // 词法单元
    protected Token lexeme;
    // 备注
    protected String label;

    public ASTNode(ASTNode parent){
        this.parent = parent;
    }

    public ASTNode(ASTNode parent, ASTNodeTypes type, String label) {
        this.parent = parent;
        this.type = type;
        this.label = label;
    }

    public ASTNode getChild(int index){
        return this.children.get(index);
    }

    public void addChild(ASTNode node){
        children.add(node);
    }

    public Token getLexeme(){
        return lexeme;
    }

    private List<ASTNode> getChildren(){
        return children;
    }
}
