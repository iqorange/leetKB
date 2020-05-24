package KBScript.parser.util;

import KBScript.lexer.Token;

// 错误处理器
public class ParseException extends Exception {
    private String message;

    public ParseException(String message){
        this.message = message;
    }

    public ParseException(Token token){
        message = String.format("Syntax Error, unexpected token %s", token.getValue());
    }

    @Override
    public String getMessage(){
        return message;
    }
}
