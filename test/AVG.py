def analysistime(m):
    re = []
    re.append(max(m))
    re.append(min(m))
    re.append("{:.3f}".format(sum(m)/len(m)))
    return  re




arrlist = [2,3,4]
arrlist.append(5)
arrlist.append(1)
arrlist.append(2.3)
arrlist.append(2.3)
arrlist.append(2.3)
arrlist.append(2.3)
print(arrlist)

arrlist = analysistime(arrlist)
print(arrlist)