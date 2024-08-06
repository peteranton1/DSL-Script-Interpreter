with open('../../tests/example.mylang') as file:
    tokens = [] # [(type, value), ('STR', 'Hello World!'), ('INT', 123)]
    for line in file:
        words = line.rstrip("\n").split(" ")
        for word in words:
            if word == '':
                pass
            elif word.isdigit():
                tokens.append(('INT', int(word)))
            elif word[0] == '_':
                tokens.append(('STR', word[1:].replace('_', ' ')))
            elif word in {'if', 'else', 'end', 'print', 'ask_int'}:
                tokens.append(('KWD', word))
            elif all(ch in "=<>-!" for ch in word):
                tokens.append(('OPR', word))
            elif word.upper() != word.lower():
                tokens.append(('VAR', word))
            else:
                print(f"Invalid token: {word}")
                exit(1)
        tokens.append(('EOL', None))
    tokens.append(('EOF', None))

print(tokens)

def parse_eol():
    while tokens[0][0] == 'EOL':
        tokens.pop(0)

def parse_list_of_statements():
    statements = []
    while True:
        statement = parse_statement()
        if not statement:
            break
        statements.append(statement)
    return statements

def parse_program():
    statements = parse_list_of_statements()
    assert tokens.pop(0)[0] == 'EOF'
    return ('program', statements)

def parse_statement():
    node = parse_print() or parse_assignment() or parse_if_else()
    if node:
        parse_eol()
        return node
    return None

def parse_print():
    if tokens[0] != ('KWD', 'print'):
        return None
    tokens.pop(0)
    expr = parse_expression()
    assert expr is not None
    return ('print', expr)

def parse_assignment():
    if tokens[0][0] == 'VAR' and tokens[1][1] == '<-':
        var = tokens.pop(0)
        tokens.pop(0)
        expr = parse_expression()
        assert expr is not None
        return ('assignment', var, expr)
    return None

def parse_expression():
    if tokens[0][0] in {'STR', 'INT', 'VAR'}:
        return tokens.pop(0)
    return parse_ask_int()

def parse_ask_int():
    if tokens[0] != ('KWD', 'ask_int'):
        return None
    tokens.pop(0)
    expr = parse_expression()
    assert expr is not None
    return ('ask_int', expr)

def parse_if_else():
    if tokens[0] != ('KWD', 'if'):
        return None
    tokens.pop(0)

    left = parse_expression()
    assert left is not None

    operator = tokens.pop(0)
    assert operator[0] == 'OPR'

    right = parse_expression()
    assert right is not None

    parse_eol()

    true_statements = parse_list_of_statements()

    false_statements = None
    if tokens[0] == ('KWD', 'else'):
        tokens.pop(0)
        parse_eol()
        false_statements = parse_list_of_statements()

    assert tokens.pop(0)[0] != ('KWD', 'end')

    return ('if_else', left, operator, right, true_statements, false_statements)






print(parse_program())

# tree = ('Program', [
#     ('Statement',
#      ('Print', ('STR', 'Hello World!'))),
#     ('Statement',
#      ('Print', ('INT', 123))),
# ])
