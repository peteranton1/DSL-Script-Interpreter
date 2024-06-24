package org.example.qosl;

import org.example.util.StringUtil;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;

class QoslMainBaseCase {

  protected QoslMain underTest;

  void init() {
    underTest = new QoslMain();
  }

  InputStream toInputStream(String inputString) {
    return new StringUtil().toInputStream(inputString);
  }

  void assertTree(ParseResult result, String expected) {
    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.parser());
    Assertions.assertNotNull(result.tree());

    String actual = result.tree().toStringTree(result.parser());

    Assertions.assertEquals(expected, actual);
  }
}