# Anthropomorphic-Robotic-Arm
the program to run our fiber test modules

the java code is intended to handle all computations and user input through a GUI and submit a final byte value over serial port
to an arduino which would translate that to voltage intensity.

Sample was programmed in the Ardunio IDE and goes as follows:
void setup() {
  Serial.begin(9600);
  pinMode(13,OUTPUT);
}
void loop() {
  if (Serial.available() > 0) {    
    byte incomingByte = 0;
    incomingByte = Serial.read(); // read the incoming byte:
    if (incomingByte != -1&&incomingByte !=10) { // -1 means no data is available
      analogWrite(13,(int)incomingByte);
      Serial.println((int)incomingByte);
    }
  }
}

The Ardunio code file is also in the bin folder
