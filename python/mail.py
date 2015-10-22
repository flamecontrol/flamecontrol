#!/usr/bin/env python3
#coding: utf-8
import configparser
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage

config = configparser.ConfigParser()
config.read("example.ini")


sender = config["mail"]["user"]     #mail from
receiver = config["mail"]["user"]   #mail to
subject = 'python email test'       #mail title
smtpserver = 'smtp.163.com'         #smtpserver
username = config["mail"]["user"]   #mail user
password = config["mail"]["pwd"]    #mail pwd


msgRoot = MIMEMultipart('related')
msgRoot['Subject'] = subject

#构造附件
att = MIMEText(open('test.txt', 'rb').read(), 'base64', 'utf-8')
att["Content-Type"] = 'application/octet-stream'
att["Content-Disposition"] = 'attachment; filename="test.txt"'
msgRoot.attach(att)                 #mail info

smtp = smtplib.SMTP()
smtp.connect(smtpserver)
smtp.login(username, password)
smtp.sendmail(sender, receiver, msgRoot.as_string())
smtp.quit()