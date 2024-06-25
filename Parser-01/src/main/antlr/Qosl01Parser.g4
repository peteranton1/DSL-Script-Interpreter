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
    : assignStmt
    | kwdStmt
    | ifBlock
    | whileBlock
    ;

assignStmt
    : LET typeId assignOp exprOrBlock
    ;

kwdStmt
    : kwdMain kwdSub* (typeId assignOp)? exprOrBlock
    ;

kwdMain
    : CHARACTER  // 'c'|'C'|'character'|'CHARACTER';
    | MULTIPLE   // 'm'|'M'|'multiple'|'MULTIPLE';
    | ON_ENTRY   // 'on_entry'|'ON_ENTRY';
    | ON_EXIT    // 'on_exit'|'ON_EXIT';
    | PACKAGE    // 'package'|'PACKAGE';
    | PERFORM    // 'perform'|'PERFORM';
    | QTEXT      // 'qt'|'QT'|'qtext'|'QTEXT';
    | SINGLE     // 's'|'S'|'single'|'SINGLE';
    ;

kwdSub
    : DECIMAL    // 'decimal'|'DECIMAL';
    | DEFAULT    // 'default'|'DEFAULT';
    | FLOAT64    // 'float'|'float64';
    | INT64      // 'int'|'int64';
    ;

whileBlock
    : WHILE condition block
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

typeId
    : Identifier
    ;

typeLit
    : IntegerLiteral
    | FloatingPointLiteral
    | BooleanLiteral
    | StringLiteral
    | TextBlock
    | NullLiteral
    ;

exprOrBlock
    : expr
    | block
    ;

block
    : LBRACE stmts RBRACE
    ;

expr
    : unaryExp
    | parenExpr
    | logicalExpr
    | typeValue
    ;

typeValue
    : typeLit
    | typeId
    ;

parenExpr
    : LPAREN expr RPAREN
    ;

unaryExp
    : unaryOp expr
    ;

unaryOp
    : BANG     // '!';
    | SUB      // '-';
    ;

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


