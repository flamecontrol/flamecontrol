from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()
driver.get("https://passport.opifices.com/?redirect=https://m.opifices.com/")
driver.set_window_size(800, 1000)
by = By()


def ivregister():
    # driver.find_element_by_id("user_mobile").send_keys("13800138000")
    elindex = 0
    mBy = getattr(by, 'ID')
    el = driver.find_elements(mBy, "user_mobile")[elindex]

    # el.send_keys("13800138000")

    getattr(el, 'send_keys')("13800138000")

    driver.find_element(by.ID, "user_captcha").send_keys("123456")
    el = driver.find_element(by.ID, "js-sendBtn")
    getattr(el, 'click')
    # driver.find_element(by.CLASS_NAME, "btn-fixed").click()

    mBy = getattr(by, 'CLASS_NAME')
    el = driver.find_elements(mBy, "btn-fixed")[elindex]
    getattr(el, 'click')()

    driver.implicitly_wait(10)
    driver.find_element(by.XPATH, "//span[text()='我的']").click()
    driver.find_element(by.CLASS_NAME, "user-summary").click()

ivregister()

