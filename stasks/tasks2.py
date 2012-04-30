
'''
Created on Mar 8, 2012

@author: rhayes
'''


import urllib2
import base64
import json
import pprint

from google.appengine.api import urlfetch

username = '4fqqu46ie8m'
password = 'x'
base64string = base64.encodestring('%s:%s' % (username, password))[:-1]

base_url = 'https://api.myintervals.com/'
task_url = base_url + 'task/'
headers = {
           'Accept': 'application/json',
           "Authorization":"Basic %s" % base64string
           }

def tasks():
    req = urllib2.Request(url=task_url)
    req.add_header('Accept', 'application/json')
    req.add_header('Authorization', "Basic %s" % base64string)
    f = urllib2.urlopen(req)
    
    return json.load(f)

print 'Content-Type: text/plain'
print ''
pprint.pprint(tasks())
