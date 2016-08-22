from a import A

att = A()

print(dir(att))

getattr(att,'a')()
wwww = None
getattr(att,'a')()

# getattr(att,'zzz')()

# print(globals())