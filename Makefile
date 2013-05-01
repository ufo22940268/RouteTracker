all:
	ant debug install && adb shell "am start -a hongbosb.main"

clean:
	ant clean
