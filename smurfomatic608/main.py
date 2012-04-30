#!/usr/bin/env python
#
#
from google.appengine.dist import use_library
use_library('django', '1.2')

from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from google.appengine.ext import db
import logging
import os
from google.appengine.ext.webapp import template

class Visit(db.Model):
    ip = db.StringProperty()
    date = db.DateTimeProperty(auto_now_add=True)
    server = db.StringProperty()
    software = db.StringProperty()

class MainHandler(webapp.RequestHandler):
    def get(self):
        template_values = {
            'title': "Smurf-O-Matic 608",
        }

        path = os.path.join(os.path.dirname(__file__), 'index.html')
        self.response.out.write(template.render(path, template_values))

class VisitHandler(webapp.RequestHandler):
    def get(self):
        rqaddr = self.request.remote_addr
        # key = db.Key.from_path('Visits','local')
        ss = os.environ['SERVER_SOFTWARE']
        sn = os.environ['SERVER_NAME']

        # v = Visit(parent=key)
        v = Visit()
        v.ip = rqaddr
        v.server = sn
        v.software = ss
        v.put()

        vv = self.visits()

        logging.debug("Visit from %s" % rqaddr)
        path = os.path.join(os.path.dirname(__file__), 'visit.html')
        template_values = {
            'rqaddr': rqaddr,
            'visits': vv,
        }
        self.response.out.write(template.render(path, template_values))
        
    def visits(self):
        q = Visit.all()
        q.order("-date")

        return q

class MyVisitHandler(webapp.RequestHandler):
    def get(self):
        rqaddr = self.request.remote_addr

        vv = self.my_visits(rqaddr)

        logging.debug("My visit from %s" % rqaddr)
        path = os.path.join(os.path.dirname(__file__), 'visit.html')
        template_values = {
            'title': "My Visits",
            'rqaddr': rqaddr,
            'visits': vv,
        }
        self.response.out.write(template.render(path, template_values))
        
    def my_visits(self, my_ip):
        q = Visit.all()
        q.filter("ip =", my_ip)
        q.order("-date")

        return q


def main():
    application = webapp.WSGIApplication([('/', MainHandler),
                                          ('/visit', VisitHandler),
                                          ('/myvisits', MyVisitHandler),
                                          ],
                                         debug=True)
    util.run_wsgi_app(application)


if __name__ == '__main__':
    main()
