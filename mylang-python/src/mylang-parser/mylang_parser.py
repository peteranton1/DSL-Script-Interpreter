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
