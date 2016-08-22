# -*- coding:utf8 -*-

class Obj:
    """ An object that use reflection """

    def __init__(self, name):
        """ 构造函数 """
        self.name = name

    def echo_methods(self):
        """ 输出类中所有的方法，以及doc 文档 """
        print
        "\n Method List: "
        for attrName in dir(self):
            attr = getattr(self, attrName)
            if callable(attr):
                print
                attrName, "():", attr.__doc__

    def echo_attributes(self):
        print
        "\n Attributes"

        for name in dit(self):
            attr = getattr(self, attr)
            if not callable(attr):
                print
                name, ":", attr


obj = Obj("testObject")
obj.echo_methods()