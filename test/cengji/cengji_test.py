import global_list

def a1(**db):
    print("***", global_list.GLOBAL_A)
    # if staff == '':
    #     print(1)
    # elif staff == '创业者':
    #     print(staff)

def a2(**db):
    print(2)
    dict = {'c': 2, 'b': 2, 'd': 'd'}
    global_list.GLOBAL_A.update(dict)
    print(global_list.GLOBAL_A)
    print(global_list.GLOBAL_A.keys())
    a1(staff='')

def a3(**kargs):
    print(3)
    print(type(kargs))
    print(kargs)
    kargs['phone'] = '13301060017'
    print(kargs.keys())
    print(kargs)
    global_list.GLOBAL_A.update(kargs)
    a2()

a3(b=1, a='创业者')

