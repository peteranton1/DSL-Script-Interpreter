package org.example.qosl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ParseInputPathTest extends QoslMainBaseCase {

  @BeforeEach
  void setUp() {
    init();
  }

  @Test
  void parseInputPathWhenFileExists() throws IOException {

    String inputPath = "QoslMain/parseInputPathWhenFileExists.qosl";

    ParseResult result = underTest.parseInputPath(inputPath);

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