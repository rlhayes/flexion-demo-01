
'''
Created on Mar 8, 2012

@author: rhayes
'''


import urllib2
import base64
import xml.dom.minidom

import pprint

from google.appengine.api import urlfetch

username = '4fqqu46ie8m'
password = 'x'
base64string = base64.encodestring('%s:%s' % (username, password))[:-1]

base_url = 'https://api.myintervals.com/'
task_url = base_url + 'task/'

def tasks():
    req = urllib2.Request(url=task_url)
    req.add_header('Accept', 'application/xml')
    req.add_header('Authorization', "Basic %s" % base64string)
    f = urllib2.urlopen(req)
    
    dom = xml.dom.minidom.parse(f)
    return dom

print 'Content-Type: text/plain'
print ''
print tasks().toprettyxml()
