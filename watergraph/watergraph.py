import webapp2
import logging
import datum
import datetime

class MainPage(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.out.write('Hello!')

class WellHandler(webapp2.RequestHandler):
    def get(self,well=None):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.out.write('Gotta well for %s' % well)

    def put(self,well=None):
        if not well:
            self.abort(500)
        else:
            d = self.request.get('d')
            u = self.request.get('u')
            v = self.request.get('v')
            c = self.request.get('c')
            
            self.store(well,d,u,v,c)

    def store(self,well,date,unit,value,chara):
        logging.info('will try to store data for %s', well)
        logging.info('date=%s, unit=%s, value=%s, ctr=%s', date, unit, value, chara)
        date_parsed = datetime.datetime.strptime(date,'%Y-%m-%dT%H:%M:%S')
        value_parsed = float(value)
        d = datum.Datum(well=well, when=date_parsed, chara=chara, unit=unit, value=value_parsed)
        datum.save(d)

class CharacteristicHandler(webapp2.RequestHandler):
    def get(self):
        proj = datum.listCharacteristics();
        self.response.headers['Content-Type'] = 'text/plain'
        for c in proj:
            self.response.out.write(c)
            self.response.out.write('\n')
            
        

app = webapp2.WSGIApplication([
        webapp2.Route('/well', WellHandler, 'well'),
        webapp2.Route('/well/<well>', WellHandler, 'well'),
        webapp2.Route('/characteristics', CharacteristicHandler, 'chara'),
        ('/', MainPage),
        ])

