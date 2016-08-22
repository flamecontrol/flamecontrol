from schema import Schema, And, Use, Optional
import json

true = True
false = False
null = None

schema = Schema([{'name': And(str, len),
                  'age':  And(Use(int), lambda n: 18 <= n <= 99),
                  Optional('sex'): And(str, Use(str.lower), lambda s: s in ('male', 'female')),
                  Optional('no'): bool}])

data = [{'name': 'Sue', 'age': '28', 'sex': 'FEMALE', 'no': true},
        {'name': 'Sam', 'age': '42'},
        {'name': 'Sacha', 'age': '20', 'sex': 'Male'}]

# data = '{"a": [{"name": "Sue", "age": "28", "sex": "FEMALE", "no": true},' \
#        '{"name": "Sam", "age": "42", "a": [{"b": "bb"}, {"b": "bb"}]},' \
#        '{"name": "Sacha", "age": "20", "sex": "Male"}],' \
#        '"c": "cc"}'
#
# data = json.loads(data)

validated = schema.validate(data)


