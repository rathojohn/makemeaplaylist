import requests
import json
import pprint

class Beats:
	def __init__(self):
		self.API_KEY = ''
		self.ROOT_URL = 'https://partner.api.beatsmusic.com/v1/api/'
	
	
	def getData(self, url):
		data = requests.get(url).text
		data = json.loads(data)
		return data
	
	
	def getArtistID(self, artistName):
		url = '%ssearch?q=%s&type=artist&client_id=%s' % (self.ROOT_URL, artistName, self.API_KEY)
		data = self.getData(url)
		artistID = data['data'][0]['id']
		return artistID
		
	
if __name__ == '__main__':
	tester = Beats()
	tester.getArtistID('Lorde')