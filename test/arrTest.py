# coding=utf-8
'''
Created on 2016-5-20

@author: Administrator
'''
'''
创建并打印数组
'''
arr = ["aex", "bfe", "mpilgrim", "zddd", "example"];
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example']
print(arr[2]);#mpilgrim
'''
数组的负索引
li[-n] == li[len(li) - n]
'''
print(arr[-1]);#example
'''
数组的分片
arr[1:3]表示从第一个元素开始，直到但不包含第三个元素
'''
print(arr[1:3]);#['bfe', 'mpilgrim']
'''
向数组中添加元素
'''
arr.append("new");
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example', 'new']
arr.insert(2, "new");
print(arr);#['aex', 'bfe', 'new', 'mpilgrim', 'zddd', 'example', 'new']
arr.extend(['gete','sdwz','wettt']);
print(arr);#['aex', 'bfe', 'new', 'mpilgrim', 'zddd', 'example', 'new', 'gete', 'sdwz', 'wettt']
'''
在数组中搜索元素
'''
print(arr.index("example"));#5

#print(arr.index("f"));#ValueError: 'f' is not in list
print("example" in arr);#True
'''
删除数组中的元素
remove是删除元素的首次出现，pop是删除最后一个元素，并且返回最后一个元素
'''
arr.remove("new");
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example', 'new', 'gete', 'sdwz', 'wettt']
print(arr.pop());#wettt
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example', 'new', 'gete', 'sdwz']
'''
在数组中使用运算符
arr = [1, 2] * 3 等同于 arr = [1, 2] + [1, 2] + [1, 2]
'''
arr=arr+['fegrc','getrvs'];
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example', 'new', 'gete', 'sdwz', 'fegrc', 'getrvs']
arr+=['two'];
print(arr);#['aex', 'bfe', 'mpilgrim', 'zddd', 'example', 'new', 'gete', 'sdwz', 'fegrc', 'getrvs', 'two']
arr1=['dfd','hrh'];
print(arr1*3);#['dfd', 'hrh', 'dfd', 'hrh', 'dfd', 'hrh']
arr2=[3,4];
print(arr2*3);#[3, 4, 3, 4, 3, 4]
