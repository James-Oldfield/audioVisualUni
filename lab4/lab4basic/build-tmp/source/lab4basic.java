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

public class lab4basic extends PApplet {

// GLOBAL VARIABLES
// Gravitational constant
float g = 1.00f;

Sun s;
Planet p;

public void setup() {
	size(500, 500);

	s = new Sun(10);

	p = new Planet(5);
}

public void draw() {
	background(255);

	// Calculate attraction and apply to the planet
	PVector gravity = s.attract(p);
	p.applyForce(gravity);

	p.update();

	s.display();
	p.display();
}

// SUN CLASS
class Sun {

	int w, mass;
	PVector loc;

	Sun(int _w) {
		w = _w;

		// Base mass on the size (width)
		mass = w;
		loc  = new PVector(width/2, height/2);
	}

	public PVector attract(Planet p) {
		// Initial vector distance of the two points
		PVector force = PVector.sub(loc, p.loc);

		// The magnitude is the distance between the two objects
		float distance = force.mag();

		// Constrain incase the value is below 1 to prevent extreme outcomes.
		distance = constrain(distance, 5, 25);

		// Normalise to find only the direction
		force.normalize();

		// Compute the strength of gravity using Isaac Newton's formula theorem
		float strength = (g * mass * p.mass) / (distance * distance);
		force.mult(strength);

		return force;
	}

	public void display() {
		ellipseMode(CENTER);
		ellipse(loc.x, loc.y, w, w);
	}
}

class Planet {

	int w, mass;
	PVector loc, vel, accel;

	Planet(int _w) {
		w     = _w;
		mass  = 1;

		// randomise location and set rest to 0.
		loc   = new PVector(width/2+10, height/2+10);
		vel   = new PVector(0,0);
		accel = new PVector(0,0);
	}

	public void applyForce(PVector f) {
		PVector force = PVector.div(f, mass);
		accel.add(force);
	}

	public void update() {
		vel.add(accel);
		loc.add(vel);

		// Clear the acceleration each time
		accel.mult(0);
	}

	public void display() {
		ellipse(loc.x, loc.y, w, w);
	}

}

// class Moon extends Planet {

// 	Moon() {

// 	}

// 	void display() {
		
// 	}

// }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab4basic" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
