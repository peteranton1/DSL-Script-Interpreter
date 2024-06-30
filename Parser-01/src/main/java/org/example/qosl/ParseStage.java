package org.example.qosl;

public enum ParseStage {
  syntax,
  reportAmbiguity,
  reportAttemptingFullContext,
  reportContextSensitivity;
}
