package org.example.qosl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class StatementAssignmentStmtExprTest extends QoslMainBaseCase {

  private static Stream<Arguments> sourceOfExprs() {
    final String templatePre = "(compUnit (stmts " +
      "(stmt (assignStmt (typeId varA) " +
      "(assignOp =) (exprOrBlock (expr " +
      "%s" +
      "))))))";
    final String operand_4 = "(typeLit 4)";
    final String operand_2 = "(typeLit 2)";
    final String operand_a = "(typeId a)";
    final String operand_b = "(typeId b)";
    final String templateLogical = String.format(templatePre,
      "(logicalExpr " +
        "(sumExpr (productExpr (typeValue %s))) " +
        "(logicalOp %s) " +
        "(sumExpr (productExpr (typeValue %s))))");
    final String templateSum = String.format(templatePre,
      "(logicalExpr (sumExpr " +
        "(productExpr (typeValue %s)) " +
        "(sumOp %s) " +
        "(productExpr (typeValue %s))" +
        "))");
    final String templateProduct = String.format(templatePre,
      "(logicalExpr (sumExpr (productExpr " +
        "(typeValue %s) " +
        "(productOp %s) " +
        "(typeValue %s)" +
        ")))");
    return Stream.of(
      Arguments.of("varA = 4 > 2",
        String.format(templateLogical, operand_4, ">", operand_2)),
      Arguments.of("varA = 4 < 2",
        String.format(templateLogical, operand_4, "<", operand_2)),
      Arguments.of("varA = 4 == 2",
        String.format(templateLogical, operand_4, "==", operand_2)),
      Arguments.of("varA = 4<=2",
        String.format(templateLogical, operand_4, "<=", operand_2)),
      Arguments.of("varA = 4>=2",
        String.format(templateLogical, operand_4, ">=", operand_2)),
      Arguments.of("varA = 4!=2",
        String.format(templateLogical, operand_4, "!=", operand_2)),
      Arguments.of("varA = 4 && 2",
        String.format(templateLogical, operand_4, "&&", operand_2)),
      Arguments.of("varA = 4 || 2",
        String.format(templateLogical, operand_4, "||", operand_2)),
      Arguments.of("varA = 4+2",
        String.format(templateSum, operand_4, "+", operand_2)),
      Arguments.of("varA = 4-2",
        String.format(templateSum, operand_4, "-", operand_2)),
      Arguments.of("varA = 4*2",
        String.format(templateProduct, operand_4, "*", operand_2)),
      Arguments.of("varA = 4/2",
        String.format(templateProduct, operand_4, "/", operand_2)),
      Arguments.of("varA = a > b",
        String.format(templateLogical, operand_a, ">", operand_b)),
      Arguments.of("varA = a < b",
        String.format(templateLogical, operand_a, "<", operand_b)),
      Arguments.of("varA = a == b",
        String.format(templateLogical, operand_a, "==", operand_b)),
      Arguments.of("varA = a <= b",
        String.format(templateLogical, operand_a, "<=", operand_b)),
      Arguments.of("varA = a >= b",
        String.format(templateLogical, operand_a, ">=", operand_b)),
      Arguments.of("varA = a != b",
        String.format(templateLogical, operand_a, "!=", operand_b)),
      Arguments.of("varA = a && b",
        String.format(templateLogical, operand_a, "&&", operand_b)),
      Arguments.of("varA = a || b",
        String.format(templateLogical, operand_a, "||", operand_b)),
      Arguments.of("varA = a + b",
        String.format(templateSum, operand_a, "+", operand_b)),
      Arguments.of("varA = a - b",
        String.format(templateSum, operand_a, "-", operand_b)),
      Arguments.of("varA = a * b",
        String.format(templateProduct, operand_a, "*", operand_b)),
      Arguments.of("varA = a / b",
        String.format(templateProduct, operand_a, "/", operand_b))
    );
  }

  @BeforeEach
  void setUp() {
    init();
  }

  @ParameterizedTest
  @MethodSource("sourceOfExprs")
  void assignmentStmtWithExprOps
    (String input, String expected) throws IOException {

    basicTest(input, expected);
  }

}