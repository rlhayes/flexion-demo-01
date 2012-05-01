import datetime
import logging
from google.appengine.ext import db
from google.appengine.api import users

class Datum(db.Model):
    well = db.StringProperty(required=True)
    when=db.DateTimeProperty()
    chara=db.StringProperty()
    unit=db.StringProperty()
    value=db.FloatProperty()
    def __str__(self):
        return 'Datum %s: %s %s of %s at %s' % (self.well, self.value, self.unit, self.chara, self.when)

def save(v):
    logging.info('got to datum save, d=%s' % v)
    v.put()

def listCharacteristics():
    q = db.GqlQuery('select chara from Datum')
    q = db.Query(Datum, projection=['chara'])
    allC = set()
    for d in q:
        allC.add(d.chara)
    logging.info('found %d kinds' % len(allC))
    return allC
