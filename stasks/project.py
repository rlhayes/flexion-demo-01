
'''
Created on Mar 8, 2012

@author: rhayes
'''


import urllib2
import base64
import xml.dom.minidom
import cgi
import os
import re

username = '4fqqu46ie8m'
password = 'x'
base64string = base64.encodestring('%s:%s' % (username, password))[:-1]

base_url = 'https://api.myintervals.com/'
project_url = base_url + 'project/'

def projects():
    form = cgi.FieldStorage()
    u = project_url
    m = re.match(r"/project/(.*)", os.getenv('PATH_INFO'))
    if "id" in form:
        u = u + form['id'].value
    elif m:
        u = u + m.group(1)
    req = urllib2.Request(url=u)
    req.add_header('Accept', 'application/xml')
    req.add_header('Authorization', "Basic %s" % base64string)
    f = urllib2.urlopen(req)
    
    dom = xml.dom.minidom.parse(f)
    return dom

print 'Content-Type: text/plain'
print ''
# print cgi.print_directory()
# print cgi.print_environ_usage()
# this has full url, like /project/XYZ
print "PATH_INFO %s" % os.getenv('PATH_INFO')
print projects().toprettyxml()
