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

    String input = "let variableA : \"banana\"";

    InputStream inputStream = toInputStream(input);
    ParseResult result = underTest.parseInputStream(inputStream);

    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.parser());
    Assertions.assertNotNull(result.tree());

    String expectedTree = "(compilationUnit " +
      "(statements (statement (assignmentStmt let " +
      "(typeIdentifier variableA) : " +
      "(expression (typeLiteral (literal \"banana\")))))))";
    String actualTree = result.tree().toStringTree(result.parser());

    Assertions.assertEquals(expectedTree, actualTree);
  }

}