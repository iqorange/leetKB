package KBScript.parser.ast;

import KBScript.lexer.TokenType;
import KBScript.parser.util.PeekTokenIterator;

// 常数
public class Scala extends Factor {
    public Scala(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }
}
