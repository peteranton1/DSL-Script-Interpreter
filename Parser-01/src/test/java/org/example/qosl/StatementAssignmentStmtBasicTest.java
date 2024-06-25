package org.example.qosl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class StatementAssignmentStmtBasicTest extends QoslMainBaseCase {

  private static Stream<Arguments> sourceOfTests() {
    final String templatePre = "(compUnit (stmts (stmt (assignStmt " +
      "(typeId variableA) (assignOp :) (exprOrBlock (expr (logicalExpr " +
      "(sumExpr (productExpr (typeValue %s))))))))))";
    final String templateLit = String.format(templatePre, "(typeLit %s)");
    final String templateId = String.format(templatePre, "(typeId %s)");
    return Stream.of(
      Arguments.of("variableA : \"banana\"",
        String.format(templateLit, "\"banana\"")),
      Arguments.of("variableA : \"\"\"\nbanana\"\"\"",
        String.format(templateLit, "\"\"\"\\nbanana\"\"\"")),
      Arguments.of("variableA : 555",
        String.format(templateLit, "555")),
      Arguments.of("variableA : 5.55",
        String.format(templateLit, "5.55")),
      Arguments.of("variableA : 0xCAFEbabe",
        String.format(templateLit, "0xCAFEbabe")),
      Arguments.of("variableA : 0b10010011",
        String.format(templateLit, "0b10010011")),
      Arguments.of("variableA : true",
        String.format(templateLit, "true")),
      Arguments.of("variableA : variableB",
        String.format(templateId, "variableB"))
    );
  }

  @BeforeEach
  void setUp() {
    init();
  }

  @ParameterizedTest
  @MethodSource("sourceOfTests")
  void assignmentStmtWithSingleLiteral
    (String input, String expected) throws IOException {

    basicTest(input, expected);
  }
}