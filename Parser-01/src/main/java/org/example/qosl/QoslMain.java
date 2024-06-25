package org.example.qosl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.antlr.qosl.Qosl01Lexer;
import org.example.antlr.qosl.Qosl01Parser;
import org.example.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

public class QoslMain {

  public static void main(String[] args) throws IOException {
    String path = "antlr/TestFile.qosl";
    if(args.length > 0){
      path = args[0];
    }
    QoslMain qoslMain = new QoslMain();
    ParseResult result = qoslMain.parseInputPath(path);
    System.out.println("Resource: "+path);
    System.out.println(result.tree().toStringTree(result.parser()));
  }

  public ParseResult parseInputPath(String inputPath) throws IOException {
    InputStream is = new FileUtil().getFileFromResourceAsStream(inputPath);
    return parseInputStream(is);
  }

  public ParseResult parseInputStream(InputStream is) throws IOException {
    // create a CharStream that reads from standard input
    CharStream input = CharStreams.fromStream(is);

    // create a lexer that feeds off of input CharStream
    Qosl01Lexer lexer = new Qosl01Lexer(input);

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    Qosl01Parser parser = new Qosl01Parser(tokens);

    return new ParseResult(parser, parser.compUnit()); // begin parsing at init rule
  }
}
