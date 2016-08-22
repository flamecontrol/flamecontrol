import re

# def regex()
#
replan = r'\$\[\w+\]'
# p = re.compile(replan)
regex = '$[base_url]/v1/bp_relations.json?from=market&project_id=$[projects].json'
# regex1 = '/v1/bp_relations.json?from=market&project_id=$[projects].json'
# m = p.search(regex)
# m1 = p.search(regex1)
#
#
# print(m)
# print(m1)
# if m1 == None:
#     print(1)
# xx = m.group()
#
# print(xx)
# print(xx[2:len(xx) - 1])


mm = re.search(replan, regex)
print(mm)
print(mm.group())

mmm = re.findall(replan, regex)
print(mmm)
