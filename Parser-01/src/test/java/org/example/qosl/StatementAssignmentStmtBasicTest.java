package org.example.qosl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

class StatementAssignmentStmtBasicTest extends QoslMainBaseCase {

  @BeforeEach
  void setUp() {
    init();
  }

  @ParameterizedTest
  @MethodSource("sourceOfTests")
  void assignmentStmtWithSingleLiteral
    (String input, String expected) throws IOException {

    InputStream inputStream = toInputStream(input);
    ParseResult result = underTest.parseInputStream(inputStream);

    assertTree(result, expected);
  }

  private static Stream<Arguments> sourceOfTests() {
    final String template = "(compilationUnit " +
      "(statements (statement (assignmentStmt let " +
      "(typeIdentifier variableA) : %s" ;
    return Stream.of(
      Arguments.of("let variableA : \"banana\"",
        String.format(template,
          "(expression (typeLiteral (literal \"banana\")))))))")),
      Arguments.of("let variableA : \"\"\"\nbanana\"\"\"",
        String.format(template,
          "(expression (typeLiteral (literal \"\"\"\\nbanana\"\"\")))))))")),
      Arguments.of("let variableA : 555",
        String.format(template,
          "(expression (typeLiteral (literal 555)))))))")),
      Arguments.of("let variableA : 5.55",
        String.format(template,
          "(expression (typeLiteral (literal 5.55)))))))")),
      Arguments.of("let variableA : 0xCAFEbabe",
        String.format(template,
          "(expression (typeLiteral (literal 0xCAFEbabe)))))))")),
      Arguments.of("let variableA : 111   ;",
        String.format(template,
          "(expression (typeLiteral (literal 111))))) ;))")),
      Arguments.of("let variableA : variableB",
        String.format(template,
          "(expression (typeIdentifier variableB))))))"))
    );
  }
}