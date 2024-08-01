// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar Qosl01Parser;

options {
    tokenVocab = Qosl01Lexer;
}

//=============

start_
    : compUnit EOF
    ;

compUnit
    : stmts ;

stmts: (stmt SEMI?)* ;

stmt
    : assignExpr? block
    ;

block
    : stmtsBlock
    | pageBlock
    | kwdStmt
    | ifBlock
    | whileBlock
    | expr
    ;

stmtsBlock
    : LBRACE stmts RBRACE
    ;

pageBlock
    : PAGE stmtsBlock
    ;

assignExpr
    : typeId rangeExpr? assignOp
    ;

kwdStmt
    : CHARACTER rangeExpr kwdSub* textLit
    | SINGLE    kwdSub* textLit
    | MULTIPLE  kwdSub* textLit
    | BOOLEAN   assignExpr expr
    | DATE      assignExpr expr
    | FLOAT     assignExpr expr
    | INTEGER   assignExpr expr
    | DECIMAL   rangeExpr? kwdSub* textLit
    | ON_ENTRY  typeId  // 'on_entry'|'ON_ENTRY';
    | ON_EXIT   typeId // 'on_exit'|'ON_EXIT';
    | PACKAGE   typeId  // 'package'|'PACKAGE';
    | PERFORM   typeIdList  // 'perform'|'PERFORM';
    | QTEXT     textLit  // 'qt'|'QT'|'qtext'|'QTEXT';
    ;

kwdSub
    : DEFAULT    // 'default'|'DEFAULT';
    | OPENEND    // 'open'|'openend';
    ;

typeIdList
    : typeIdExpr (COMMA typeId)*
    ;

typeIdExpr
    : typeId rangeExpr?
    ;

typeId
    : Identifier
    ;

rangeExpr
    : LPAREN typeValue rangeSubExpr* RPAREN
    ;

rangeSubExpr
    : COMMA typeValue
    | ELLIPSE typeValue
    ;

whileBlock
    : WHILE rangeExpr block
    ;

ifBlock
    : IF condition block
      elseifBlock*
      elseBlock?
    ;

elseifBlock
    : ELSEIF condition block
    ;

elseBlock
    : ELSE block
    ;

condition
    : expr
    ;

assignOp
    : COLON    // ':';
    | ASSIGN   // '=';
    ;

typeLit
    : IntegerLiteral
    | FloatingPointLiteral
    | booleanLiteral
    ;

textLit
    : StringLiteral
    | TextBlock
    | NullLiteral
    ;

expr
    : unaryExp
    | parenExpr
    | logicalExpr
    | typeValue
    | rangeExpr
    ;

typeValue
    : typeLit
    | textLit
    | typeId
    ;

parenExpr
    : LPAREN (expr|rangeExpr) RPAREN
    ;

unaryExp
    : unaryOp expr
    ;

unaryOp
    : BANG     // '!';
    | SUB      // '-';
    ;

booleanLiteral: TRUE | FALSE;

logicalExpr
    : sumExpr (logicalOp sumExpr)*
    ;

sumExpr
    : productExpr (sumOp productExpr)*
    ;

productExpr
    : typeValue (productOp typeValue)*
    ;

logicalOp
    : GT       // '>';
    | LT       // '<';
    | EQUAL    // '==';
    | LE       // '<=';
    | GE       // '>=';
    | NOTEQUAL // '!=';
    | AND      // '&&';
    | OR       // '||';
    ;

sumOp
    : ADD      // '+';
    | SUB      // '-';
    ;

productOp
    : MUL      // '*';
    | DIV      // '/';
    ;


