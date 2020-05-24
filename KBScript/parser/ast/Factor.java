package KBScript.parser.ast;

import KBScript.lexer.TokenType;
import KBScript.parser.util.PeekTokenIterator;

// 因子，用于操作符与运算（表达式里代表值的元素）
public abstract class Factor extends ASTNode{
    public Factor(ASTNode parent, PeekTokenIterator iterator) {
        super(parent);
        var token = iterator.next();
        var myType = token.getType();
        // 类型转换
        if (myType == TokenType.VARIABLE){
            this.type = ASTNodeTypes.VARIABLE;
        }else{
            this.type = ASTNodeTypes.SCALAR;
        }
        this.label = token.getValue();
        this.lexeme = token;
    }
}
