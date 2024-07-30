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
    : LBRACE stmts RBRACE
    | kwdStmt
    | ifBlock
    | whileBlock
    | expr
    ;

assignExpr
    : typeId assignOp
    ;

kwdStmt
    : kwdMain (MUL rangeExpr)? kwdSub* typeValue?
    ;

rangeExpr
    : LPAREN rangeExpr RPAREN
    | typeValue ((COMMA typeValue)* | (ELLIPSE typeValue)?)
    ;

kwdMain
    : CHARACTER  // 'c'|'C'|'character'|'CHARACTER';
    | DATE       // 'date';
    | MULTIPLE   // 'm'|'M'|'multiple'|'MULTIPLE';
    | NUMBER     // 'number';
    | ON_ENTRY   // 'on_entry'|'ON_ENTRY';
    | ON_EXIT    // 'on_exit'|'ON_EXIT';
    | PACKAGE    // 'package'|'PACKAGE';
    | PERFORM    // 'perform'|'PERFORM';
    | QTEXT      // 'qt'|'QT'|'qtext'|'QTEXT';
    | SINGLE     // 's'|'S'|'single'|'SINGLE';
    ;

kwdSub
    : NUMBER    // 'number';
    | DEFAULT    // 'default'|'DEFAULT';
    | OPENEND    // 'open'|'openend';
    | DATE       // 'date';
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
    : Identifier parenExpr?
    ;

typeLit
    : IntegerLiteral
    | FloatingPointLiteral
    | BooleanLiteral
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


