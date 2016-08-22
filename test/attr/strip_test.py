print(9999)

for i in range(1):
    print(i)

a = "111"
b = str(a).split(":")[1]
print(b)

def set_ids(text, res_type):
    values = res_type.split(".")
    id_value = None
    decode_json = json.loads(text)
    for i in range(len(values) - 1):
        decode_json = decode_json[values[i]]
    self.set_log(DEV, decode_json)
    if type(decode_json) is dict:      # TODO TODO
        id_value = str(decode_json[values[1]])
    else:
        for i in range(len(decode_json)):
            get_value = str(decode_json[i][values[1]])
            if i == 0:
                id_value = get_value
                continue
            id_value = id_value + "," + get_value