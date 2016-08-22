from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()
# driver = webdriver.Chrome()
url = 'https://passport.opifices.com/?redirect=https://m.opifices.com/'
driver.get(url)
driver.maximize_window()
driver.implicitly_wait(20)
# wait_element = WebDriverWait(self.driver, 10, 0.5)

a = [["ID", "user_mobile", "send_keys", "13800138000", "", ""],
    ["ID", "user_captcha", "send_keys", "123456", "", ""],
    ["ID", "js-sendBtn", "click", "", "", ""],
    ["CLASS_NAME", "btn-fixed", "click", "", "", ""],
    ["XPATH", "//span[text()='我的']", "click", "", "", ""],
    ["CLASS_NAME", "user-summary", "click", "", "", ""],
    ["switch_to", "", "", "", "", ""],
    ["XPATH", "//span[text()='主页']", "click", "", "", ""]]

byType = 0
byValue = 1
elMethod = 2
testData = 3
ShotName = 4
mayNull = 5

# TODO set_window_size,swipe,switch_to,execute_script
# TODO ID,XPATH,LINK_TEXT,PARTIAL_LINK_TEXT,NAME,TAG_NAME,CLASS_NAME,CSS_SELECTOR

str_split = "###"		# 分割数据
elIndex = 0
is_continue = False

by = By()


def element_exist(eby, value, el_index):                 # TODO 等待
    print(value)
    try:
        el_ex = driver.find_elements(eby, value)[el_index]
        return el_ex
    except KeyError:
        return None


def shot(path):
    return path


for i in range(8):

    V_byType = a[i][byType]
    V_byValue = a[i][byValue]
    V_elMethod = a[i][elMethod]
    V_testData = a[i][testData]
    V_ShotName = a[i][ShotName]
    V_mayNull = a[i][mayNull]

    # 判断是否需要继续
    if is_continue and V_mayNull == "continue":
        continue
    # 自定义操作方法
    if V_byType == "swipe":				# 滑动屏幕
        typeInfo = V_byType.split(",")
        up = int(typeInfo[0])
        left = int(typeInfo[1])
        # tool.swipe(driver, up, left)                            # TODO
        continue
    elif V_byType == "execute_script":		# 系统按键                         # TODO
        driver.execute_script(V_byValue)
        continue
    elif V_byType == 'switch_to':
        print(driver.window_handles)
        driver.switch_to.window(driver.window_handles[-1])  # 跳转到新打开的页面
        continue
    elif V_byType == 'set_window_size':
        driver.set_window_size(1024, 768)

    # 拆分查找对象和index
    if str_split in V_byValue:
        el_type = V_byValue.split(str_split)
        V_byValue = el_type[0]
        elIndex = int(el_type[1])
    else:
        # 初始化index
        elIndex = 0

    # 系统方法查找类型

    mBy = getattr(by, V_byType)

    # 确认对象
    el = element_exist(mBy, V_byValue, elIndex)					# 判断当前状态存在
    # 无对象确认
    if el is None:
        if V_mayNull == "null":		# 判断是否为特别事件
            is_continue = False 					# 默认没有后续
            continue
        else:										# 确定识别失败报错
            shot("对象：" + V_byValue + "不存在，失败" + V_ShotName)	    # 记录日志
            break

    # TODO 判断不存在时的截图
    if V_elMethod == "":
        if not V_ShotName == "":
            shot(V_ShotName)

    print(V_elMethod)
    if V_testData == "":
        getattr(el, V_elMethod)()  # Element执行反射方法
    else:									# TODO 确定带参的方法
        getattr(el, V_elMethod)(V_testData)  # Element执行反射方法
    
    # 截图完成
    if not V_ShotName == '':
        shot(V_ShotName)

driver.close()
