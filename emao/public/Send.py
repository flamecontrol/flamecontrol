# -*- coding: utf-8 -*-
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
import smtplib

class send:
 
        def people(self,content):
            
            #创建一个带附件的实例
            msg = MIMEMultipart()

            txt = MIMEText(content,'plain','gb2312')    
            msg.attach(txt)
            #构造附件1
            
            att1 = MIMEText(open('/var/lib/jenkins/workspace/emao_git/test_report/log_emao.log', 'rb').read(), 'base64', 'gb2312')
            att1["Content-Type"] = 'application/octet-stream'
            att1["Content-Disposition"] = 'attachment; filename="log_emao.log"'#这里的filename可以任意写，写什么名字，邮件中显示什么名字
            msg.attach(att1)
            """
            #构造附件2
            att2 = MIMEText(open('d:\\test.html', 'rb').read(), 'base64', 'gb2312')
            att2["Content-Type"] = 'application/octet-stream'
            att2["Content-Disposition"] = 'attachment; filename="test.html"'
            msg.attach(att2)
            """
            #加邮件头
            strTo = ['chenshiyang460@emao.com','gaojie419@emao.com']
            msg['to']=','.join(strTo)
            
            msg['from'] = 'chen_shiyangnihao@163.com'
            msg['subject'] = u'一猫现网问题'

 
            #发送邮件
            try:
                server = smtplib.SMTP()
                server.connect('smtp.163.com')
    
                server.starttls()
                server.login('chen_shiyangnihao@163.com','111qqq!')#XXX为用户名，XXXXX为密码
                server.sendmail(msg['from'], strTo,msg.as_string())
#                server.sendmail(msg['from'], msg['to'],self.error)
                server.quit()
                print '发送成功'
            except Exception, e: 
                    print str(e)