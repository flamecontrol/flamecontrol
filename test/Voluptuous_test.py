from voluptuous import Schema
import json

# schema = Schema({'a': str, 'b': str, 'c': int})
r = '{"a": "aa", "b": str, "c": int}'
rj = json.loads(r)
schema = Schema({'a': 'aa', 'b': str, 'c': int})
a = '{"a":"aa","b":"bbbb","c":1}'
j = json.loads(a)

print(schema(j))


