import requests


import json
import datetime

s = requests.session()
#                                     {"account":"json","platform":"Android","pwd":"i6UytEO2NIRpMT+Xun+\/IT4pwhjW8saiW7+LF98NXBtWuU6UqnQG7pSPkI5TNCN3V7FPFi\/DjY\/P\nx9QlKzitu8ZaJukgNex0ZIQfF4CrDrUPOzRLmsZrGNPNpMLaeGFkJ6rfUPKdCfp5gbSaS\/Y0HfXC\n9fqbLdtXHJgsqZ0cXH8=\n","code":2070,"deviceId":"99000662461066","buildNo":"1.4.4"}
# url = 'http://app.emao.com/v1.4/?info={"account":"json","platform":"Android","pwd":"Y9FYIQbO0wcoMBdv6ZMSVw8nFSihp3bk8eMn3Xa1q8+SM4nE9U0FihSueyhLHk6hGO5s9TLya3zK\\ngULzEjU\/YvUkO7wAUHmQaxzys4KuYqKxJnEyunnynhgYCre72VHFiXzJxJHi6IhSrwxzZvulJg77\\n+nlOFGue7Lfhq3e9hpk=\\n","code":2070,"deviceId":"99000662461066","buildNo":"1.4.4"}'
# print(url)
# r = s.get(url)
# print(r.text)

url = 'http://app.emao.com/v1.4/?info={"offset":0,"token":"1043656217","len":20,"code":2024}'
r = s.get(url)
print(r.text)

# url = 'http://app.emao.com/v1.4/?info={"platform":"Android","deviceId":"99000662461066","buildNo":"1.4.4","code":2073}'
# r = s.get(url)
# print(r.text)