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

public class lab4flock extends PApplet {

// Global variable to dictate size of boid
float bs = 5.0f;

// Dictate the number of boids
int nb = 100;

ArrayList<Boid> boids = new ArrayList<Boid>();

public void setup() {
	size(500, 500);

	for(int i = 0; i < nb; i++) {
		Boid b = new Boid();
		boids.add(b);
	}
}

public void draw() {
	background(255);
	for (Boid b : boids) {
	  b.display();
	}
}

class Boid {
	PVector loc, vel, accel;
	float velVal = 5;

	Boid() {
		loc = new PVector(random(width), random(height));
		vel = new PVector(random(-velVal, velVal), random(-velVal, velVal));
	}

	public void display() {
		pushMatrix();
			translate(loc.x, loc.y);
			// rotate to translate velocity - cast as float
			rotate(-(float)atan2(-vel.y, vel.x));
			// Draw an individual flock
			triangle(-bs/2, bs/2, bs/2, 0, -bs/2, -bs/2);
		popMatrix();
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab4flock" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
