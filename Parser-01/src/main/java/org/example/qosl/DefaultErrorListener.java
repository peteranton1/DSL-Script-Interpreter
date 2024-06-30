package org.example.qosl;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class DefaultErrorListener implements ANTLRErrorListener {

  private final List<ParseError> parseErrors = new ArrayList<>();

  public List<ParseError> getParseErrors() {
    return parseErrors;
  }

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line,
                          int charPositionInLine,
                          String msg,
                          RecognitionException e) {
    parseErrors
      .add(new ParseError(ParseStage.syntax,
        recognizer, offendingSymbol,
        line, charPositionInLine, msg, e));
  }

  @Override
  public void reportAmbiguity(Parser recognizer,
                              DFA dfa,
                              int startIndex,
                              int stopIndex,
                              boolean exact,
                              BitSet ambigAlts,
                              ATNConfigSet configs) {
    parseErrors
      .add(new ParseError(ParseStage.reportAmbiguity,
        recognizer, dfa.toString(),
        startIndex, stopIndex, "reportAmbiguity", null));
  }

  @Override
  public void reportAttemptingFullContext(Parser recognizer,
                                          DFA dfa,
                                          int startIndex,
                                          int stopIndex,
                                          BitSet conflictingAlts,
                                          ATNConfigSet configs) {
    parseErrors
      .add(new ParseError(ParseStage.reportAttemptingFullContext,
        recognizer, dfa.toString(),
        startIndex, stopIndex, "reportAttemptingFullContext", null));
  }

  @Override
  public void reportContextSensitivity(Parser recognizer,
                                       DFA dfa,
                                       int startIndex,
                                       int stopIndex,
                                       int prediction,
                                       ATNConfigSet configs) {
    parseErrors
      .add(new ParseError(ParseStage.reportContextSensitivity,
        recognizer, dfa.toString(),
        startIndex, stopIndex, "reportContextSensitivity", null));
  }
}
