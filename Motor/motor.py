import RPi.GPIO as GPIO
import time
import sys
import os

pin = [11, 12, 13, 15, 16, 18]

if len(sys.argv) == 2:
    if (0 <= int(sys.argv[1]) and int(sys.argv[1]) < 6):
        motor = int(sys.argv[1])
    else:
        print(sys.argv[1])
        print("choose motor: (0-5)")
        sys.exit()
else:
    print("no argument, choose motor: (0-5)")
    sys.exit()

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

GPIO.setup(pin[motor], GPIO.OUT)

m = GPIO.PWM(pin[motor], 50)
#start position
m.start(3.5)
time.sleep(0.35)
#turn to 90 degrees
#m.ChangeDutyCycle(3.5)
time.sleep(2)
#os.system("python stack.py")
#turn to 0 degrees
m.ChangeDutyCycle(7)
time.sleep(0.35)

#exit
m.stop()
GPIO.cleanup()

