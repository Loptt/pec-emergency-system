import serial
import sys

bt = serial.Serial('/dev/rfcomm0',9600)
data = ""

while True:
	data += bt.read()
	#string = data.decode('UTF-16')
	#sys.stdout.write(data)

	data_end = data.find('\n')
	if data_end != -1:
		rec = data[:data_end]
		print(data)
	data = data[data_end+1:]
	
	print(data)
