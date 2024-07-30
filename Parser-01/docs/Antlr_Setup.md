Antlr Setup
===============

Antlr is a tool for generating compilers. 

The tool can be setup using the following script:

    cd $HOME/dev/antlr
    antlr_jar=antlr-4.8-complete.jar
    sudo curl -O https://www.antlr.org/download/$antlr_jar
    export CLASSPATH=".:$HOME/dev/antlr/$antlr_jar:$CLASSPATH"
    alias antlr4='java -jar ~/dev/antlr/$antlr_jar'
    alias grun='java org.antlr.v4.gui.TestRig'

