package org.example.qosl;

import java.util.List;

public record CompilationResult(ParseResult parseResult,
                                List<ParseError> parseErrors) {
}
