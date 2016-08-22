#coding=utf-8
'''
Created on 2015年9月11日
@author: huangpei401
@fun：检查用户登录后的链接有效性，并将检测结果写入txt文件
@environment：通用环境
'''
import time,urllib2
import codecs,sys
sys.path.append("E:\\svn\\develop\\AutoTest_Emao\\public")
import wait_time

def check_link_login(ff_driver,success_file,error_file):
 
    links = ff_driver.find_elements_by_tag_name("a")
    wait_time.wait_time(ff_driver,1,60)

    count_url=0
    str2='http'
    str3='javascript'
    str4='#'

    #log_ok = codecs.open('E:/Python_emao_demo/test_report/emao_log_ok.txt','a','utf-8')
    #log_error=codecs.open('E:/Python_emao_demo/test_report/emao_log_error.txt','a','utf-8')
    log_ok = codecs.open(success_file,'a','utf-8')
    log_error=codecs.open(error_file,'a','utf-8')

    log_ok.truncate()#清空
    log_error.truncate()#清空

    for link in links:
        str1=link.get_attribute("href")
        #print str1
        if (str3 not in str1) and (str4 not in str1) and str1 !='':
            count_url+=1

            try:
                s=urllib2.urlopen(str1)
                print str1,s.getcode(),str(count_url)
                
                #print '**********************************************************'
                #print 'url:%s\nstatus:%s\ncount_url:%s\n'%(str1,s.getcode(),str(count_url))
                log_ok.write('**********************************************************\r\n')
                log_ok.write('url:%s\r\nstatus:%s\r\ncount_url:%s\r\n'%(str1,s.getcode(),str(count_url)))


            except urllib2.HTTPError,e:
                #print e.code
                #print e.read()
                #print e.geturl()
                print str1,e.getcode(),str(count_url)
                
                #print 'url:%s\nstatus:%s\ncount_url:%s\n'%(str1,e.getcode(),str(count_url))
                log_error.write('**********************************************************\r\n')
                log_error.write('url:%s\r\nstatus:%s\r\ncount_url:%s\r\n'%(str1,e.getcode(),str(count_url)))

            except urllib2.URLError,e:
                print str(e)
                print e.reason
      
    log_ok.close()
    log_error.close()
