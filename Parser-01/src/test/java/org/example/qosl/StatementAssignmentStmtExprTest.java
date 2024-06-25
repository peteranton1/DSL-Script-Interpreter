package org.example.qosl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

class StatementAssignmentStmtExprTest extends QoslMainBaseCase {

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
      "(typeId varA) (assignOp =) (exprOrBlock (expr (logicalExpr " +
      "%s)))))))" ;
    final String templateLitSum = String.format(templatePre,
      "(sumExpr (productExpr (typeValue (typeLit 4))) " +
        "(sumOp %s) (productExpr (typeValue (typeLit 2))))");
    final String templateLitProd = String.format(templatePre,
      "(sumExpr (productExpr (typeValue (typeLit 4)) " +
        "(productOp %s) (typeValue (typeLit 2))))");
    return Stream.of(
      Arguments.of("let varA = 4+2",
        String.format(templateLitSum,"+")),
      Arguments.of("let varA = 4-2",
        String.format(templateLitSum,"-")),
      Arguments.of("let varA = 4*2",
        String.format(templateLitProd,"*")),
      Arguments.of("let varA = 4/2",
        String.format(templateLitProd,"/"))
    );
  }
}