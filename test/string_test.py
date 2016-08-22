import re
a = "aaaaa@[bbb:ccc]aaaa"

# aa = r'@[*]'
aa = r'@[\w+]'

p = re.compile(aa)

m = p.search(a)

print(m)


for i in range(10):
    for j in range(10):
        z = i * 10 + j
        if divmod(z, 2)[1] == 1:
            print(z)
        else:
            continue
        print(1)

