Car controller program information 


@Author Raúl Maza
@Data   17/06/19
---------------------------------------------------------------------------------------------------------------------------
>>Hey there! that is a very simple aplication for controlling a car with Bluetooth.
The program was made with Java using Eclipse . You have the files in a folder named Java, feel free to see / modify it!
I made the program using bluecover, it is important to know that when you open the program it is going to try to connect 
to the HC-05 bluetooth module of the car (The device is named "RAUL"). If you want to connect to other device you have to
change manually the MAC in the code. The actual URL of the HC-05 module is:

URL: "btspp://201503142740:1;authenticate=false;encrypt=false;master=false"
MAC HC-05: 201503142740

Also the car is controlled with a Genuino 101 board. You will find the project in the Arduino folder.



Manual operation:
---------------------------------------------------------------------------------------------------------------------------

-Connect the power source in the car. (A green LED connected to the protoboard will blink 5 times, also 
the red LED on the HC-05 module will blink).

-Open the program and wait until you see "Connected!!" on the info box (the red LED on the HC-05 will stop blink).

-Now you can start typing on the textbox (down BT Contro Panel).

-w --> go fordward
-s --> go backward
-a --> go left
-d --> go right
