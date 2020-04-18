package KBScript.lexer;

// 处理词法分析器的异常
public class LexicalException extends Exception {
    private String msg;

    public LexicalException(char c){
         this.msg = String.format("Unexpercted character %c", c);
    }

    public LexicalException(String msg){
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }
}
