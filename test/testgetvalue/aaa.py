class aaaa():
    a = 1
    def geta(self):
        return self.a


def aaa():
    bbb = "123"
    return bbb


aa = aaaa()
print(aa.geta())
print(aa.a)
print(aaaa().a)
print(aaa())
