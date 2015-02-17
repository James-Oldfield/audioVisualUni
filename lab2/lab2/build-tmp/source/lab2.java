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

public class lab2 extends PApplet {

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
	s = new PVector(2,3);
	myImage.filter(BLUR, 1);

	pos.add(vel);
	s.add(speed);

	scale(s.x, s.y);

	translate(pos.x, pos.y);
	image(myImage, 0,0, 100, 200);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
