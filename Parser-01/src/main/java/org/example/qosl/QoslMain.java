package org.example.qosl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.antlr.qosl.Qosl01Lexer;
import org.example.antlr.qosl.Qosl01Parser;
import org.example.util.FileUtil;
import org.example.util.TreeFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QoslMain {

  public static void main(String[] args) throws IOException {
    String path = "antlr/TestFile.qosl";
    if (args.length > 0) {
      path = args[0];
    }
    QoslMain qoslMain = new QoslMain();
    CompilationResult compResult = qoslMain.parseInputPath(path);
    System.out.println("Resource: " + path);
    ParseResult parseResult = compResult.parseResult();
    System.out.println(TreeFormatter.toStringTree(
        parseResult.tree(),
        parseResult.parser()));
    String line = "************************";
    List<ParseError> parseErrors = compResult.parseErrors();
    if (parseErrors.isEmpty()) {
      println(line + "\nCompilation Successful\n" + line);
    } else {
      println(line + "\nCompilation Failed\n" + line);
      parseErrors.forEach(
        err -> {
          if (ParseStage.syntax.equals(err.stage())) {
            println(err.msg());
          }
        }
      );
      println(String.format("Found %d errors", parseErrors.size()));
    }
  }

  public static void println(String s) {
    System.out.println(s);
  }

  public CompilationResult parseInputPath(String inputPath) throws IOException {
    InputStream is = new FileUtil().getFileFromResourceAsStream(inputPath);
    return parseInputStream(is);
  }

  public CompilationResult parseInputStream(InputStream is) throws IOException {
    // create a CharStream that reads from standard input
    CharStream input = CharStreams.fromStream(is);

    // create a lexer that feeds off of input CharStream
    Qosl01Lexer lexer = new Qosl01Lexer(input);

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    Qosl01Parser parser = new Qosl01Parser(tokens);
    DefaultErrorListener errorListener = new DefaultErrorListener();
    parser.addErrorListener(errorListener);

    ParseResult parseResult = new ParseResult(parser, parser.start_());
    List<ParseError> parseErrors = errorListener.getParseErrors();
    return new CompilationResult(parseResult, parseErrors);
  }
}
