# -*- coding:utf-8 -*-
from selenium import webdriver

def browser():
    # host = '172.16.193.139:4444'
    host = '172.16.193.79:4444'
    # dc = {'browserName': 'Chrome'}
    dc = {'browserName': 'firefox'}
    driver = webdriver.Remote(command_executor='http://'+host+'/wd/hub', desired_capabilities=dc)
    return driver

if __name__ == '__main__':
    dr = browser()
    dr.get("http://www.baidu.com")
    dr.quit()