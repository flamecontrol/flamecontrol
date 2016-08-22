import re

# print('Please input mail address:',inputmail = raw_input())
input = 'aaaa@aaaa.com'
regex = '[^\._-][\w\.-]+@(?:[A-Za-z0-9]+\.)+[A-Za-z]+$|^0\d{2,3}\d{7,8}$|^1[358]\d{9}$|^147\d{8}'
regex = 'aaa@aaaa.co'


"""
说明：
[^\._-][\w\.-]+@(?:[A-Za-z0-9]+\.)+[A-Za-z]+$匹配邮箱。
综合目前国内常用的邮箱，大概通用的规则包括：
1、[^\._]，不能以下划线和句点开头。
2、[\w\.]+，包括字母、数字。而对句点及下划线各提供商有差别，对此有效性不做更严格的判断。
3、@是必须的。
4、(?:[A-Za-z0-9]+\.)+[A-Za-z]+$，@后以xxx.xxx结尾，考虑到多级域名，会有这种情况xxx.xxx.xxx如xxx@yahoo.com.cn

^0\d{2,3}\d{7,8}$|^1[358]\d{9}$|^147\d{8}$匹配电话号码。
只考虑国内的情况,大概通用的规则包括：
1、^0\d{2,3}，固定电话区号3-4位数字，以0开头。
2、d{7,8}$，固定电话一般7-8位数字。
3、国内目前的手机号码都是11位数字，三大运营商的号码段基本都在上面列出来了，我们这里除了147的号码的段，其他的都只考虑前两位，
第三位就不考虑了，否则，工作量也很大。前两位包括13*、15*、18*。

"""


def check(input, regex):
    p = re.compile(regex)
    match = p.match(input)

    if match:
        return match.group()
    else:
        return False

print(check(input, regex))


"""
部分测试结果：

正确的邮箱地址格式：
Please input mail address: biao.wu@gmail.com
biao.wu@gmail.com

Please input mail address: wu_biao@163.com
wu_biao@163.com

Please input mail address: wubiao@yahoo.com.cn
wubiao@yahoo.com.cn

Please input mail address: wu-biao@qq.com
wu-biao@qq.com

Please input mail address: 8888@qq.com
8888@qq.com

错误的邮箱地址格式：
Please input mail address: .biao@163.com
mail address or phone number is error!

Please input mail address: _wubiao@qq.com
mail address or phone number is error!

Please input mail address: -qq@qq.com
mail address or phone number is error!

Please input mail address: biao@@.com
mail address or phone number is error!

Please input mail address: wubiao@qq.com.
mail address or phone number is error!

Please input mail address: wubiao@qq.com.22
mail address or phone number is error!

Please input mail address: wubiao#@163.com
mail address or phone number is error!

正确的电话号码格式：
Please input mail address: 13530315051
13530315051

Please input mail address: 075512345678
075512345678

Please input mail address: 18667676767
18667676767

错误的电话号码格式：
Please input mail address: 135303154
mail address or phone number is error!

Please input mail address: 135303112345
mail address or phone number is error!

Please input mail address: 1234567890
mail address or phone number is error!

"""