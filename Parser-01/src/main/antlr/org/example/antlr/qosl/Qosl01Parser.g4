// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar Qosl01Parser;

@header{
package org.example.antlr.qosl;
}

options {
    tokenVocab = Qosl01Lexer;
}

//=============

start_
    : compilationUnit EOF
    ;

compilationUnit
    : LET typeIdentifier COLON literal ;

typeIdentifier
    : Identifier
    ;

literal
    : IntegerLiteral
    | FloatingPointLiteral
    | BooleanLiteral
    | StringLiteral
    | TextBlock
    ;


