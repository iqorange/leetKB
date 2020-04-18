package KBScript.lexer;

import KBScript.common.AlphabetHelper;
import KBScript.common.PeekIterator;

import java.util.ArrayList;
import java.util.stream.Stream;

// 词法分析器 JDK 10+
public class Lexer {
    // 作为流传进去
    public ArrayList<Token> analyse(Stream source) throws LexicalException{
        var tokens = new ArrayList<Token>();
        var iterator = new PeekIterator<Character>(source, (char)0);
        while (iterator.hasNext()){
            char c = iterator.next();
            // 流的结束
            if (c == 0){
                break;
            }
            char lookahead = iterator.peek();
            // 处理空格和换行情况
            if (c == ' ' || c == '\n'){
                continue;
            }
            // 删除注释
            if (c == '/'){
                if (lookahead == '/'){
                    while (iterator.hasNext() && (c = iterator.next()) != '\n');
                }else if (lookahead == '*'){
                    boolean valid = false;
                    while (iterator.hasNext()){
                        char p = iterator.next();
                        if (p == '*' && iterator.peek() == '/'){
                            iterator.hasNext();
                            valid = true;
                            break;
                        }
                    }
                    if (!valid){
                        throw new LexicalException("comments not match ");
                    }
                    continue;
                }
            }

            // 处理括号的情况
            if (c == '{' || c == '}' || c == '(' || c == ')'){
                tokens.add(new Token(TokenType.BRACKET, c+""));
                continue;
            }
            // 处理是引号的情况
            if (c == '"' || c == '\''){
                iterator.putBack();
                tokens.add(Token.makeString(iterator));
                continue;
            }
            // 处理其他字符的情况
            if (AlphabetHelper.isLetter(c)){
                iterator.putBack();
                tokens.add(Token.makeVarOrKeyword(iterator));
                continue;
            }
            // 处理如果是数字的情况
            if (AlphabetHelper.isNumber(c)){
                iterator.putBack();
                tokens.add(Token.makeNumber(iterator));
                continue;
            }
            // 处理正负号和小数点的情况
            if ((c == '+' || c == '-' || c == '.') && AlphabetHelper.isNumber(lookahead)){
                var lastToken = tokens.size() == 0 ? null : tokens.get(tokens.size()-1);
                if (lastToken == null || !lastToken.isNumber() || lastToken.isOperator()){
                    iterator.putBack();
                    tokens.add(Token.makeNumber(iterator));
                    continue;
                }
            }
            // 如果是操作符的情况
            if (AlphabetHelper.isOperator(c)){
                iterator.putBack();
                tokens.add(Token.makeOp(iterator));
                continue;
            }
            throw new LexicalException(c);
        }
        return tokens;
    }
}
