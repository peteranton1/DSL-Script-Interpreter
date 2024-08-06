MyLang Grammar
================

Below is the grammar EBNF for mylang

    <Program> ::= <Statement>*
    <Statement> ::= <Print> | <Assignment> | <IfElse> EOL
    <Print> ::= 'print' <Expression>
    <Assignment> ::= VAR '<-' <Expression>
    <Expression> ::= (STR | INT | VAR | <AskInt>)
    <AskInt> ::= 'ask_int' <Expression>
    <IfElse> ::=    'if' <Epression> ('=' | '!=' | '<' | '>') <Expression> EOL
                        <Statement>* 
                    ('else' EOL 
                        <Statement>*)?
                    'end'



