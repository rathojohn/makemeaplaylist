import json
import requests
import pprint
import random

class LastFM:
	def __init__(self):
		self.API_KEY = ''
		self.ROOT_URL = 'http://ws.audioscrobbler.com/2.0/'
	
	
	def getToken():
		url = ROOT_URL + '?method=auth.gettoken&api_key=' + API_KEY + '&format=json'
		token = getData(url)
		return token
	
	
	def generateURL(artist, method):
		self.url = self.ROOT_URL + method + artist + '&api_key=' + self.API_KEY + '&format=json'
		return url
	
	
	def getData(self, url):
		data = requests.get(url).text
		data = json.loads(data)
		return data
	
	
	def getSimilarArtists(data):
		similarArtists = [artist['name'] for artist in data['similarartists']['artist']]
		return similarArtists
	
	
	def getSongs(artist):
		url = generateURL(artist, '?method=artist.gettoptracks&artist=')
		songData = getData(url)
		try:
			if songData['error'] == 16:
				return None
		except KeyError:
			songs = [{artist: song['name']} for song in songData['toptracks']['track']]
			return songs[0:3]
		except TypeError:
			return None
	
	
	def createPlaylist(artist):
		url = generateURL(artist, '?method=artist.getsimilar&artist=')
		data = getData(url)
		artists = getSimilarArtists(data)
		song_bank = []
		random.shuffle(artists)
		for artist in artists[0:15]:
			songs = getSongs(artist)
			song_bank.append(songs)
		print song_bank
		api_key_param = '&api_key=' + API_KEY
		create_playlist_url = ROOT_URL + '?method=playlist.create' + api_key_param
		
		
	def checkArtist(artist):
		url = generateURL(artist, '?method=artist.getinfo&artist=')
		data = getData(url)
		try:
			if data['error'] == 6:
				print 'Artist not found. Please enter a valid artist. '
		except KeyError:
			print 'Generating playlist.'
			createPlaylist(artist)



if __name__ == '__main__':
	artist = raw_input('Enter an artist. ')
	checkArtist(artist)