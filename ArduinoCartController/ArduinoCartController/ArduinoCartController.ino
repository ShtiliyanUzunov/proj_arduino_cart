
 /* Represents the control signals
  * needed to control the motor
  */
 struct motorSignals {
   int enable;
   int input1;
   int input2;  
 } left_mSignals, right_mSignals;

char message; // variable to receive data from the serial port

//Pin Mappings for left motor
int leftEnablePin = 13;
int leftInp1Pin = 11;
int leftInp2Pin = 9;

//Pin Mappings for right motor
int rightEnablePin = 7;
int rightInp1Pin = 5;
int rightInp2Pin = 3;


void turnOffMotors()
{
  left_mSignals.enable = LOW;
  right_mSignals.enable = LOW;
}

void setMoveForward()
{
  left_mSignals.enable = HIGH;
  left_mSignals.input1 = HIGH;
  left_mSignals.input2 = LOW;

  right_mSignals.enable = HIGH;
  right_mSignals.input1 = LOW;
  right_mSignals.input2 = HIGH;
}

void setMoveBackward()
{
  left_mSignals.enable = HIGH;
  left_mSignals.input1 = LOW;
  left_mSignals.input2 = HIGH;

  right_mSignals.enable = HIGH;
  right_mSignals.input1 = HIGH;
  right_mSignals.input2 = LOW;
}

void setTurnLeft()
{
  left_mSignals.enable = HIGH;
  left_mSignals.input1 = HIGH;
  left_mSignals.input2 = LOW;

  right_mSignals.enable = HIGH;
  right_mSignals.input1 = HIGH;
  right_mSignals.input2 = LOW;
}

void setTurnRight()
{
  left_mSignals.enable = HIGH;
  left_mSignals.input1 = LOW;
  left_mSignals.input2 = HIGH;

  right_mSignals.enable = HIGH;
  right_mSignals.input1 = LOW;
  right_mSignals.input2 = HIGH;
}

void setup() {
  message = 'X';
  pinMode(leftEnablePin, OUTPUT); 
  pinMode(leftInp1Pin, OUTPUT); 
  pinMode(leftInp2Pin, OUTPUT); 
  pinMode(rightEnablePin, OUTPUT); 
  pinMode(rightInp1Pin, OUTPUT); 
  pinMode(rightInp2Pin, OUTPUT); 
  
  Serial.begin(9600);       // start serial communication at 9600bps
}


/* Main Loop */

void loop() {
  if( Serial.available() > 0 )       // if data is available to read
  {
    message = Serial.read();         // read it and store it in 'val'
    
    switch (message) {
      case 'F': 
         //Serial.println(message);
         setMoveForward();
         break;
      case 'B': 
        //Serial.println(message);
        setMoveBackward();
        break;
      case 'L': 
        //Serial.println(message);
        setTurnLeft();
        break; 
      case 'R': 
        //Serial.println(message);
        setTurnRight();
        break;
      case 'X': 
        //Serial.println(message);
        turnOffMotors();
        break;
    }
    delay(1);//Delay between reads for stability
  }

  digitalWrite(leftEnablePin, left_mSignals.enable);
  digitalWrite(leftInp1Pin, left_mSignals.input1);
  digitalWrite(leftInp2Pin, left_mSignals.input2);
  digitalWrite(rightEnablePin, right_mSignals.enable);
  digitalWrite(rightInp1Pin, right_mSignals.input1);
  digitalWrite(rightInp2Pin, right_mSignals.input2);
}


