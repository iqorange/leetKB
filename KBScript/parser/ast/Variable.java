package KBScript.parser.ast;

import KBScript.parser.util.PeekTokenIterator;

// 变量
public class Variable extends Factor {

    public Variable(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }
}
