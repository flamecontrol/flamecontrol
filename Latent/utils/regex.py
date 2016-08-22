import re
import os


class Regex:
    @staticmethod
    def re(aim, value):

        if re.search(value, aim):
            return True
        else:
            return False



# print(os.getcwd())
print(Regex.re("13800138000", "/^1\d{10}$/"))
