
'''
Created on Mar 8, 2012

@author: rhayes
'''


import urllib
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
    result = urlfetch.fetch(url=task_url,
                        method=urlfetch.GET,
                        headers=headers)
    return json.loads(result.content)

print 'Content-Type: text/plain'
print ''
pprint.pprint(tasks())
