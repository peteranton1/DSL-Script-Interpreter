package org.example.qosl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.qosl.Qosl01Parser;

public record ParseResult(Qosl01Parser parser, ParseTree tree) {
}
