/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.

  Most Arduinos have an on-board LED you can control. On the Uno and
  Leonardo, it is attached to digital pin 13. If you're unsure what
  pin the on-board LED is connected to on your Arduino model, check
  the documentation at http://arduino.cc

  This example code is in the public domain.

  modified 8 May 2014
  by Scott Fitzgerald
 */
 

char message; // variable to receive data from the serial port

int l_Pin = 13; // LED connected to pin 48 (on-board LED)
int l_State = 0;
int r_Pin = 11;
int r_State = 0;
int f_Pin = 9;
int f_State = 0;
int b_Pin = 7;
int b_State = 0;

void nullStates()
{
  r_State = LOW;
  l_State = LOW;
  f_State = LOW;
  b_State = LOW;
}

void setup() {
  message = 'X';
  pinMode(l_Pin, OUTPUT); 
  pinMode(r_Pin, OUTPUT); 
  pinMode(f_Pin, OUTPUT); 
  pinMode(b_Pin, OUTPUT); 
  
  Serial.begin(9600);       // start serial communication at 9600bps
}
void loop() {
  if( Serial.available() > 0 )       // if data is available to read
  {
    message = Serial.read();         // read it and store it in 'val'
    
    switch (message) {
      case 'F': 
         Serial.println(message);
         f_State = HIGH;
         break;
      case 'B': 
        Serial.println(message);
        b_State = HIGH;
        break;
      case 'L': 
        Serial.println(message);
        l_State = HIGH;
        break; 
      case 'R': 
        Serial.println(message);
        r_State = HIGH;
        break;
      case 'X': 
        Serial.println(message);
        nullStates();
        break;
    }
    delay(1);//Delay between reads for stability
        
  }

  digitalWrite(l_Pin, l_State);
  digitalWrite(r_Pin, r_State);
  digitalWrite(f_Pin, f_State);
  digitalWrite(b_Pin, b_State);

}




