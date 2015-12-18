import requests


a = requests.get('https://github.com', verify=True)
print(a.text)