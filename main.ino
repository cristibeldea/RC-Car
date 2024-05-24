#include <Servo.h>

const int pinW=4;
const int pinA=5;
const int pinS=2;
const int pinD=7;

const int neutral = 90; //unghiul pentru mers in fata
const int left = 45; //roti orientate la stanga
const int right = 135; //roti orientate la dreapta

const int M1front = 12;
const int M1rear = 9;
const int M2front = 8;
const int M2rear = 13;

const int frontMotorPin = 3;
const int rearMotorPin = 6;

const int maxSpeed=255;
int frontSpeed=0;
int rearSpeed=0;
int accelerationStep=15;
int decelerationStep=30;
int angle=0;

Servo direction;

void setup(){
//Pinii care primesc input de la Raspberry
  pinMode(4, INPUT);
  pinMode(5, INPUT);
  pinMode(2, INPUT);
  pinMode(7, INPUT);

  pinMode(M1front, OUTPUT);
  pinMode(M2front, OUTPUT);
  pinMode(M1rear, OUTPUT);
  pinMode(M2rear, OUTPUT);

  digitalWrite(M1front, LOW);
  digitalWrite(M2front, LOW );
  digitalWrite(M1rear, LOW);
  digitalWrite(M2rear, LOW);

  direction.attach(11);
  direction.write(neutral);

}

void loop(){
  if(digitalRead(pinW)==HIGH){
    if(frontSpeed< maxSpeed){
      digitalWrite(M1front, HIGH);
      digitalWrite(M2front, HIGH);
      digitalWrite(M1rear, LOW);
      digitalWrite(M2rear, LOW);

      frontSpeed += accelerationStep;
      analogWrite(frontMotorPin, frontSpeed);
      analogWrite(rearMotorPin, frontSpeed);
      delay(100);
    }
  }

  if(digitalRead(pinS)==HIGH){
    if(rearSpeed< maxSpeed){
      digitalWrite(M1front, LOW);
      digitalWrite(M2front, LOW);
      digitalWrite(M1rear, HIGH);
      digitalWrite(M2rear, HIGH);

      rearSpeed += accelerationStep;
      analogWrite(frontMotorPin, rearSpeed);
      analogWrite(rearMotorPin, rearSpeed);
      delay(100);
    }
  }

  if(digitalRead(pinA)==HIGH){
    if(angle!=left){
      angle=left;
      direction.write(angle);
    }
  }

  if(digitalRead(pinD)==HIGH){
    if(angle!=right){
      angle=right;
      direction.write(angle);
    }
  }

  if(digitalRead(pinA)==LOW && digitalRead(pinD)==LOW){
    if(angle!=neutral){
      angle=neutral;
      direction.write(neutral);
    }
  }

  if(digitalRead(pinW) == LOW && digitalRead(pinS) == LOW){
    digitalWrite(M1front, LOW);
    digitalWrite(M2front, LOW);
    digitalWrite(M1rear, LOW);
    digitalWrite(M2rear, LOW);

    if(rearSpeed>0){
      if(rearSpeed - decelerationStep >=0)
        rearSpeed -= decelerationStep;
      else
        rearSpeed -= 15;
    }

    if(frontSpeed>0){
      if(frontSpeed - decelerationStep >= 0)
        frontSpeed -= decelerationStep;
      else
        frontSpeed -= 15;
    }
    delay(50);
  }
delay(50);
}