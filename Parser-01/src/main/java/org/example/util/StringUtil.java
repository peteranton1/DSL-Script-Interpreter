package org.example.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringUtil {

  public InputStream toInputStream(String s) {
    return new ByteArrayInputStream(s.getBytes());
  }
}
