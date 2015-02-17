import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class lab2Extension3 extends PApplet {

PImage myImage;
PVector pos,
        vel,
        s,
        speed;


public void setup() {
  size(displayWidth,displayHeight);
  rectMode(CENTER);
  myImage = loadImage("cat.png");

  pos   = new PVector(0,0);
  vel   = new PVector(1,1);
  speed = new PVector(1,1);
}

public void draw() {
  background(255);
  s = new PVector(2,3);


  if (pos.y+300 >= height || pos.y < 0) {
    speed = new PVector(-speed.x, -speed.y);
    vel = new PVector(vel.x, -vel.y);
    speed.mult(0);
  } else if (pos.x+300 >= width || pos.x < 0) {
    speed = new PVector(-speed.x, -speed.y);
    vel = new PVector(-vel.x, vel.y);
    speed.mult(0);
  }
    vel.add(speed);
    pos.add(vel);

  translate(pos.x, pos.y);
  image(myImage, 0,0, 300, 300);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab2Extension3" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
