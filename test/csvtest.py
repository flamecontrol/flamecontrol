import csv
import sys


reader = csv.reader(open('Login_checkbox.csv', 'r', encoding='GBK'))
a = 1
for line in reader:
    # print(a,"","")
    sys.stdout.write(str(a) + "\t")
    a = a + 1
    print(line)

