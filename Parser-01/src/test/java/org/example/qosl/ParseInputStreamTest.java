package org.example.qosl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class ParseInputStreamTest extends QoslMainBaseCase {

  @BeforeEach
  void setUp() {
    init();
  }

  @Test
  void parseInputStreamWhenStreamExists() throws IOException {

    String input = " variableA : \"banana\"";

    InputStream inputStream = toInputStream(input);
    ParseResult result = underTest.parseInputStream(inputStream).parseResult();

    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.parser());
    Assertions.assertNotNull(result.tree());

    String expectedTree = "(compUnit (stmts (stmt (assignStmt " +
      "(typeId variableA) (assignOp :) (exprOrBlock (expr " +
      "(logicalExpr (sumExpr (productExpr (typeValue " +
      "(typeLit \"banana\")))))))))))";
    String actualTree = result.tree().toStringTree(result.parser());

    Assertions.assertEquals(expectedTree, actualTree);
  }

}