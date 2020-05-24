package KBScript.parser.ast;

import KBScript.lexer.Token;

// 表达式
public class Expr extends ASTNode {
    public Expr(ASTNode parent){
        super(parent);
    }

    public void setLexeme(Token lexeme) {
        this.lexeme = lexeme;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setType(ASTNodeTypes type){
        this.type = type;
    }
}
