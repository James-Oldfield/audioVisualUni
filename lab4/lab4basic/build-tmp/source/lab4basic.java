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

// PLANETARY SYSTEM - Moon and Asteroid extend Planet

// GLOBAL VARIABLES
// Gravitational constant
float g = .05f;

Sun s;
Planet[]   ps = new Planet[10];
Moon[]     ms = new Moon[10];
Asteroid[] as = new Asteroid[10];

public void setup() {
	size(750, 750);

	s = new Sun(50, color(255, 255, 0));

	for (int i = 0; i < ps.length; i++) {
		// Randomise location
		PVector planLoc = new PVector(random(100, width-100), random(100, height-100));
		PVector moonLoc = new PVector(random(100, width-100)+5, random(100, height-100)+5);
		PVector asterLoc = new PVector(width+ps.length-i*100, 0);

		ps[i] = new Planet(i*5, color(255, 200, 255), planLoc);
		ms[i] = new Moon(i*2, color(0, 255, 255), moonLoc);
		as[i] = new Asteroid(i, color(255, 255, 255), asterLoc);
	}
}

public void draw() {
	background(255);

	s.display();

	for (int i = 0; i < ps.length; i++) {
		// Calculate attraction and apply to the planet
		PVector gravity = s.attract(ps[i]);
		ps[i].applyForce(gravity);
		ps[i].update();
		ps[i].display();

		// Apply gravity to the moons
	  gravity = ps[i].attract(ms[i]);
		ms[i].applyForce(gravity);
		ms[i].update();
		ms[i].display();

		as[i].display();
		as[i].update();
	}
}

// SUN CLASS
class Sun {

	int w, mass;
	int myCol;
	PVector loc;

	Sun(int _w, int _myCol) {
		w     = _w;
		myCol = _myCol;
		mass  = w;

		loc  = new PVector(width/2, height/2);
	}

	// Attract a planet
	public PVector attract(Planet p) {
		// Initial vector distance of the two points
		PVector force = PVector.sub(loc, p.loc);

		// The magnitude is the distance between the two objects
		float distance = force.mag();

		// Constrain incase the value is below 1 to prevent extreme outcomes.
		distance = constrain(distance, 1, 5);

		// Normalise to find only the direction
		force.normalize();

		// Compute the strength of gravity using Isaac Newton's formula theorem
		float strength = (g * mass * p.mass) / (distance * distance);
		force.mult(strength);

		return force;
	}

	public void display() {
		fill(myCol);
		ellipseMode(CENTER);
		ellipse(loc.x, loc.y, w, w);
	}
}

class Planet {

	int w, mass;
	int myCol;
	PVector loc, vel, accel;

	Planet(int _w, int _myCol, PVector _loc) {
		w     = _w;
		myCol = _myCol;
		mass  = w;

		loc   = _loc;
		vel   = new PVector(2,2);
		accel = new PVector(0,0);
	}

	public PVector attract(Moon m) {
		// Initial vector distance of the two points
		PVector force = PVector.sub(loc, m.loc);

		// The magnitude is the distance between the two objects
		float distance = force.mag();

		// Constrain incase the value is below 1 to prevent extreme outcomes.
		distance = constrain(distance, .05f, .075f);

		// Normalise to find only the direction
		force.normalize();

		// Compute the strength of gravity using Isaac Newton's formula theorem
		float strength = (g * mass * m.mass) / (distance * distance);
		force.mult(strength);

		// Alter moons' gravity to keep up with planets
		force.div(1000);

		return force;
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
		fill(myCol);
		ellipse(loc.x, loc.y, w, w);
	}

}

class Moon extends Planet {

	Moon(int _w, int _myCol, PVector _loc) {
		// Inherit superclass's constructor
		super(_w, _myCol, _loc);
			mass  = w/2;
	}

	//*
	// Moon class remains relatively similar to
	// Planet class, it inherits everything from 
	// Planet, only has a different gravitional pull
	// and also is attracted to planet class.
	//*

}

class Asteroid extends Planet {
	PShape asteroid = loadShape("meteor.svg");

	Asteroid(int _w, int _myCol, PVector _loc) {
		// Inherit superclass's constructor
		super(_w, _myCol, _loc);
	}

	public void display() {
		shape(asteroid, loc.x, loc.y, 200, 100);
	}
	public void update() {
		loc.x --;
		loc.y ++;
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab4basic" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
