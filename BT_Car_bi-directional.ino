#include <SoftwareSerial.h>

SoftwareSerial BT(10, 11); // RX | TX

const int right_1 = 4;
const int right_2 = 5;
const int left_1 = 3;
const int left_2 = 2; 
const int led_start = 7; 

char state = -1;

void setup()
{
  inicialize();
}
 
void loop()
{
  // Feed any data from bluetooth to Terminal.
  if (BT.available() > 0){
     state = BT.read();
     Serial.print("ESTADO: ");
     Serial.println(state);
  }
  //Car motion
  car(state);
}

/////FUNCTIONS/////

/**
 * Control motores 
 * -Con un 1 funcionan los dos lados a la vez
 */
 void car(char state){
//Acelerate
 if (state == 'a') {
    digitalWrite(right_1, LOW);
    digitalWrite(right_2, LOW);
    digitalWrite(left_1,LOW);
    digitalWrite(left_2, LOW);
 }
 if (state == 'A') {
   digitalWrite(right_1, HIGH);
   digitalWrite(right_2,LOW);
   digitalWrite(left_1,HIGH);
   digitalWrite(left_2,LOW);
  }  
//Reverse
 if (state == 'b') {
    digitalWrite(right_1, LOW);
    digitalWrite(right_2, LOW);
    digitalWrite(left_1,LOW);
    digitalWrite(left_2, LOW);
 }
 if (state == 'B') {
   digitalWrite(right_1, LOW);
   digitalWrite(right_2,HIGH);
   digitalWrite(left_1,LOW);
   digitalWrite(left_2,HIGH);
  } 
//Go right
 if (state == 'R') {
   digitalWrite(right_1, HIGH);
   digitalWrite(right_2,LOW);
   digitalWrite(left_1,LOW);
   digitalWrite(left_2,HIGH);
  }
  if(state == 'r'){
    digitalWrite(right_1, LOW);
    digitalWrite(right_2, LOW);
    digitalWrite(left_1,LOW);
    digitalWrite(left_2, LOW);
  }
//Go left
 if (state == 'L') {
   digitalWrite(right_1, LOW);
   digitalWrite(right_2,HIGH);
   digitalWrite(left_1,HIGH);
   digitalWrite(left_2,LOW);
  }
  if(state == 'l'){
    digitalWrite(right_1, LOW);
    digitalWrite(right_2, LOW);
    digitalWrite(left_1,LOW);
    digitalWrite(left_2, LOW);
  }
 }

/**
 * Inicialize arduino and blink a green LED 5 times when it's ready 
 * (green led keeps HIGH when program is running)
 */
 void inicialize(){

  pinMode(right_1,OUTPUT);
  pinMode(right_2,OUTPUT);
  pinMode(left_1,OUTPUT);
  pinMode(left_2,OUTPUT);

  digitalWrite(right_1, LOW);
  digitalWrite(right_2, LOW);
  digitalWrite(left_1,LOW);
  digitalWrite(left_2, LOW);
  
  pinMode(led_start,OUTPUT);
  
  Serial.begin(9600);
  BT.begin(9600);  //Default Baud for comm, it may be different for your Module. 

  //Led blinks
  for (int i=0;i<5;i++){
  digitalWrite(led_start,HIGH);
  delay(50);
  digitalWrite(led_start,LOW);
  delay(50);
  }
  digitalWrite(led_start,HIGH);
}
