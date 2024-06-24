// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar Qosl01Parser;

//@header{
//package org.example.antlr.qosl;
//}

options {
    tokenVocab = Qosl01Lexer;
}

//=============

start_
    : compilationUnit EOF
    ;

compilationUnit
    : statements ;

statements: (statement SEMI?)* ;

statement
    : assignmentStmt
    ;

assignmentStmt
    : LET typeIdentifier COLON expression
    ;

typeIdentifier
    : Identifier
    ;

typeLiteral
    : literal
    ;

expression
    : typeLiteral
    | typeIdentifier
    ;

literal
    : IntegerLiteral
    | FloatingPointLiteral
    | BooleanLiteral
    | StringLiteral
    | TextBlock
    | NullLiteral
    ;


