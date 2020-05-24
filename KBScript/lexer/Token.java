package KBScript.lexer;

import KBScript.common.AlphabetHelper;
import KBScript.common.PeekIterator;

public class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public TokenType getType(){
        return type;
    }

    @Override
    public String toString() {
        return String.format("type: %s, value: %s", type, value);
    }

    public boolean isVariable(){
        return type == TokenType.VARIABLE;
    }

    public boolean isScalar(){
        return type == TokenType.INTEGER || type == TokenType.FLOAT || type == TokenType.STRING || type == TokenType.BOOLEAN;
    }

    // 提取变量关键字
    public static Token makeVarOrKeyword(PeekIterator<Character> iterator){
        String s = "";
        while (iterator.hasNext()){
            var lookahead = iterator.peek();
            if (AlphabetHelper.isLiteral(lookahead)){
                s += lookahead;
            }else{
                break;
            }
            iterator.next();
            // 循环不变式
        }

        // 判断关键词还是变量
        if(KeyWords.isKeyWord(s)){
            return new Token(TokenType.KEYWORD, s);
        }

        if (s.equals("true") || s.equals("false")){
            return new Token(TokenType.BOOLEAN, s);
        }

        return new Token(TokenType.VARIABLE, s);
    }

    public static Token makeString(PeekIterator<Character> iterator) throws LexicalException{
        StringBuilder s = new StringBuilder();
        int state = 0;
        while (iterator.hasNext()){
            char c = iterator.next();
            switch (state){
                case 0:
                    if (c=='\"'){
                        state = 1;
                    }else{
                        state = 2;
                    }
                    s.append(c);
                    break;
                case 1:
                    if (c=='"'){
                        return new Token(TokenType.STRING, s.toString() +c);
                    }else{
                        s.append(c);
                    }
                    break;
                case 2:
                    if (c=='\''){
                        return new Token(TokenType.STRING, s.toString()+c);
                    }else{
                        s.append(c);
                    }
                    break;
            }
        }
        throw new LexicalException("Unexpected error");
    }

    public static Token makeOp(PeekIterator<Character> iterator) throws LexicalException{
        int state = 0;
        while (iterator.hasNext()) {
            char lookaHead = iterator.next();
            switch (state) {
                case 0:
                    switch (lookaHead){
                        case '+':
                            state = 1;
                            break;
                        case '-':
                            state = 2;
                            break;
                        case '*':
                            state = 3;
                            break;
                        case '/':
                            state = 4;
                            break;
                        case '>':
                            state = 5;
                            break;
                        case 'C':
                            state = 6;
                            break;
                        case '=':
                            state = 7;
                            break;
                        case '!':
                            state = 8;
                            break;
                        case '&':
                            state = 9;
                            break;
                        case '|':
                            state = 10;
                            break;
                        case '^':
                            state = 11;
                            break;
                        case '%':
                            state = 12;
                            break;
                        case ',':
                            return new Token(TokenType.OPERATOR, ",");
                        case ';':
                            return new Token(TokenType.OPERATOR, ";");
                    }
                    break;
                case 1:
                    if (lookaHead == '+'){
                        return new Token(TokenType.OPERATOR, "++");
                    }else if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "+=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "+");
                    }
                case 2:
                    if (lookaHead == '-'){
                        return new Token(TokenType.OPERATOR, "--");
                    }else if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "-=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "-");
                    }
                case 3:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "*+");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "*");
                    }
                case 4:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "/+");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "/");
                    }
                case 5:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, ">+");
                    }else if (lookaHead == '>'){
                        return new Token(TokenType.OPERATOR, ">>");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, ">");
                    }
                case 6:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "<+");
                    }else if (lookaHead == '<'){
                        return new Token(TokenType.OPERATOR, "<<");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "<");
                    }
                case 7:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "==");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "=");
                    }
                case 8:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "!=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "!");
                    }
                case 9:
                    if (lookaHead == '&'){
                        return new Token(TokenType.OPERATOR, "&&");
                    }else if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "&=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "&");
                    }
                case 10:
                    if (lookaHead == '|'){
                        return new Token(TokenType.OPERATOR, "||");
                    }else if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "|=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "|");
                    }
                case 11:
                    if (lookaHead == '^'){
                        return new Token(TokenType.OPERATOR, "^^");
                    }else if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "^=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "^");
                    }
                case 12:
                    if (lookaHead == '='){
                        return new Token(TokenType.OPERATOR, "%=");
                    }else{
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "%");
                    }
            }
        }
        throw new LexicalException("Unexpected error");
    }

    public static Token makeNumber(PeekIterator<Character> iterator) throws LexicalException{
        StringBuilder s = new StringBuilder();
        int state = 0;
        while (iterator.hasNext()){
            char lookAHead = iterator.peek();
            switch (state) {
                case 0: {
                    if (lookAHead == '0') {
                        state = 1;
                    } else if (AlphabetHelper.isLetter(lookAHead)) {
                        state = 2;
                    } else if (lookAHead == '+' || lookAHead == '-') {
                        state = 3;
                    } else if (lookAHead == '.') {
                        state = 5;
                    }
                    break;
                }
                case 1: {
                    if (lookAHead == '0') {
                        state = 1;
                    } else if (AlphabetHelper.isLetter(lookAHead)) {
                        state = 2;
                    } else if (lookAHead == '.') {
                        state = 4;
                    } else {
                        return new Token(TokenType.INTEGER, s.toString());
                    }
                    break;
                }
                case 2: {
                    if (AlphabetHelper.isNumber(lookAHead)){
                        state = 2;
                    }else if (lookAHead == '.'){
                        state = 4;
                    }else{
                        return new Token(TokenType.INTEGER, s.toString());
                    }
                    break;
                }
                case 3: {
                    if (AlphabetHelper.isNumber(lookAHead)){
                        state = 2;
                    }else if (lookAHead == '.'){
                        state = 5;
                    }else{
                        throw new LexicalException(lookAHead);
                    }
                    break;
                }
                case 4: {
                    if (lookAHead == '.'){
                        throw new LexicalException(lookAHead);
                    }else if (AlphabetHelper.isNumber(lookAHead)){
                        state = 20;
                    }else{
                        return new Token(TokenType.FLOAT, s.toString());
                    }
                    break;
                }
                case 5: {
                    if (AlphabetHelper.isNumber(lookAHead)){
                        state = 20;
                    }else {
                        throw new LexicalException(lookAHead);
                    }
                    break;
                }
                case 20: {
                    if (AlphabetHelper.isNumber(lookAHead)){
                        state = 20;
                    }else if (lookAHead == '.'){
                        throw new LexicalException(lookAHead);
                    }else{
                        return new Token(TokenType.FLOAT, s.toString());
                    }
                }
            }
            iterator.next();
            s.append(lookAHead);
        }
        throw new LexicalException("Unexpected error");
    }

    public boolean isNumber() {
        return this.type == TokenType.INTEGER || this.type == TokenType.FLOAT;
    }

    public boolean isOperator() {
        return this.type == TokenType.OPERATOR;
    }

    public String getValue() {
        return value;
    }
}
