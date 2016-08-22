

v_expected = '"adasda":'
try:
    rule_schema = eval(v_expected)
    # response_schema = eval(response_text)
    # Schema(rule_schema, extra=False)(response_schema)
except Exception as e:
    print(e)
except MultipleInvalid as e:
    print(0)
    # self.set_log(ERROR, "{}数据效验不正确".format(e.errors))

print(1)

