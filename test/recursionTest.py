def c(a,i):
    if(i > 0):return a[i - 1] + c(a,i - 1)
    else:return 0

a = [1,2,3,4,5,6,7,8,9,10]
print(c(a,1))