import RPi.GPIO as GPIO
import time

pin = [11, 13, 15, 16, 18, 37]

if len(sys.argv) == 2:
    if (0 <= sys.argv[1] and sys.argv[1] < 6):
        motor = sys.argv[1]
    else
        print("choose motor: (0-5)")
        sys.exit()
else
    print("no argument, choose motor: (0-5)")
    sys.exit()

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

GPIO.setup(pin[motor], GPIO.OUT)

m = GPIO.PWM(pin[motor], GPIO.OUT)
#start position
m.start(2.5)
time.sleep(0.5)
#turn to 90 degrees
m.ChangeDutyCycle(7.5)
time.sleep(0.5)
#turn to 0 degrees
m.ChangeDutyCycle(2.5)
time.sleep(0.5)

#exit
m.stop()
GPIO.cleanup()

