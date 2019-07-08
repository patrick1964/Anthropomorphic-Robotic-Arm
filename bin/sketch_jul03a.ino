void setup() {
  Serial.begin(9600);
  pinMode(13,OUTPUT);
  /*while (!Serial) {
    ; // wait for serial port to connect.
  }*/
 
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
