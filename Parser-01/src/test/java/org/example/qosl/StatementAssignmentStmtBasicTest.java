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
    final String templatePre = "(compUnit (stmts (stmt (assignStmt let " +
      "(typeId variableA) (assignOp :) (exprOrBlock (expr (logicalExpr " +
      "(sumExpr (productExpr (typeValue %s))))))))))" ;
    final String templateLit = String.format(templatePre, "(typeLit %s)");
    final String templateId = String.format(templatePre, "(typeId %s)");
    return Stream.of(
      Arguments.of("let variableA : \"banana\"",
        String.format(templateLit,"\"banana\"")),
      Arguments.of("let variableA : \"\"\"\nbanana\"\"\"",
        String.format(templateLit,"\"\"\"\\nbanana\"\"\"")),
      Arguments.of("let variableA : 555",
        String.format(templateLit,"555")),
      Arguments.of("let variableA : 5.55",
        String.format(templateLit,"5.55")),
      Arguments.of("let variableA : 0xCAFEbabe",
        String.format(templateLit,"0xCAFEbabe")),
      Arguments.of("let variableA : true",
        String.format(templateLit,"true")),
      Arguments.of("let variableA : variableB",
        String.format(templateId,"variableB"))
    );
  }
}