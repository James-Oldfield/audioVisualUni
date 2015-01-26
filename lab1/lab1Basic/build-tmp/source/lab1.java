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

public class lab1 extends PApplet {

PImage upRiver;
PVector _v1, _v2, _v3, _v4, posVec;

public void setup() {
	size(1250, 750, P3D);

	upRiver = loadImage("upriver.jpg");
	textureMode(NORMAL);

	_v1 = new PVector(100, 100, 100);
	_v2 = new PVector(100, 400, 200);
	_v3 = new PVector(400, 100, 300);
	_v4 = new PVector(400, 400, 400);
}

public void draw() {
	background(255);
	posVec = new PVector(mouseX, mouseY);
	// limit the magnitude of posVec
	posVec.limit(500);

	PVector v1 = PVector.add(posVec, _v1);
	PVector v2 = PVector.add(posVec, _v2);
	PVector v3 = PVector.add(posVec, _v3);
	PVector v4 = PVector.add(posVec, _v4);

	beginShape();
		texture(upRiver);
		vertex(v1.x, v1.x, v1.z, 0, 1);
		vertex(v2.x, v2.y, v2.z, 0, 0);
		vertex(v3.x, v3.y, v3.z, 1, 0);
		vertex(v4.x, v4.y, v4.z, 1, 1);
	endShape();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
