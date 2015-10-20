import requests
import datetime,time

arrhead = ["via","X-Cache","X-Swift-CacheTime","X-Swift-SaveTime"]
arrurl = []
arrurl = [
"http://www.emao.com/city/hankou/?atm_yimao=1&lyma0",
"http://www.emao.com/city/beijing/?atm_yimao=1&lyma0",
"http://www.emao.com/city/shanghai/?atm_yimao=1&lyma0",
"http://www.emao.com/city/ningbo/?atm_yimao=1&lyma0"]
if(len(arrurl) == 0):
	content = input("")
	while(content != ""):
		arrurl.append(content)
		content = input("")
for url in arrurl:
	print("--------" + url + "--------")
	t1 = time.time()
	r = requests.get(url)
	t2 = time.time()
	t3 = (int((t2 - t1)*1000))/1000
	print(str(t3) + "s")
	print(float(r.elapsed.microseconds)/1000)
	# print(r.elapsed.microseconds)

	for hkey in arrhead:
		try:
			hval = r.headers[hkey]
			print(hkey + " : " + hval)
			if('0' == hval or "MISS" in hval):
			    print("    ERROR :::: " + hval)
		except:
			print (hkey + " :::: " + "NOT FOUND!!")
