package KBScript.parser.util;

import KBScript.common.PeekIterator;
import KBScript.lexer.Token;
import KBScript.lexer.TokenType;

import java.util.stream.Stream;

// 迭代器
public class PeekTokenIterator extends PeekIterator<Token> {
    public PeekTokenIterator(Stream<Token> stream){
        super(stream);
    }

    // 看下一个符号是否是value
    public Token nextMatch(String value) throws ParseException {
        var token = this.next();
        if (!token.getValue().equals(value)){
            throw new ParseException(token);
        }
        return token;
    }

    public Token nextMatch(TokenType type) throws ParseException {
        var token = this.next();
        if (!token.getValue().equals(type)){
            throw new ParseException(token);
        }
        return token;
    }
}
