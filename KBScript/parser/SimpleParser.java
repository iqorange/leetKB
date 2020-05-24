package KBScript.parser;

import KBScript.parser.ast.ASTNode;
import KBScript.parser.ast.ASTNodeTypes;
import KBScript.parser.ast.Expr;
import KBScript.parser.ast.Scala;
import KBScript.parser.util.ParseException;
import KBScript.parser.util.PeekTokenIterator;

public class SimpleParser {
    // Expri -> digit + Expr | digit
    public static ASTNode parse(PeekTokenIterator iterator) throws ParseException {
        var expr = new Expr(null);
        var scalar = new Scala(expr, iterator);
        // base condition
        if (!iterator.hasNext()){
            return scalar;
        }

        // 拿到加号
        expr.setLexeme(iterator.peek());
        // eat
        iterator.nextMatch("+");
        expr.setLabel("+");
        // 左节点
        expr.addChild(scalar);
        expr.setType(ASTNodeTypes.BINARY_EXPR);
        // 右节点
        var rightNode = parse(iterator);
        expr.addChild(rightNode);
        return expr;
    }
}
