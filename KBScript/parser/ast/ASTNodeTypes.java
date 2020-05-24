package KBScript.parser.ast;

public enum ASTNodeTypes {
    // 代码块
    BLOCK,
    // 二项表达式
    BINARY_EXPR,
    // 一元表达式
    UNARY_EXPR,
    // 变量
    VARIABLE,
    // 值类型
    SCALAR,
    // if表达式
    IF_STMT,
    // while表达式
    WHILE_STME,
    // for表达式
    FOR_STMT,
    // 赋值语句
    ASSIGN_STMT,
    // 函数定义
    FUNCTION_DECLARE_STMT,
    // 声明
    DECLARE_STMT
}
