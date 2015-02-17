// PLANETARY SYSTEM - Moon extends Planets

// GLOBAL VARIABLES
// Gravitational constant
float g = 2;

Sun s;
Planet[] ps = new Planet[10];
Moon[]   ms = new Moon[10];

void setup() {
	size(500, 500);

	s = new Sun(50, color(255, 255, 0));

	for (int i = 0; i < ps.length; i++) {
		// Randomise location
		PVector loc = new PVector(random(100, width-100), random(100, height-100));

		ps[i] = new Planet(i*5, color(255, 200, 255), loc);
		ms[i] = new Moon(i*2, color(0, 255, 255), loc);
	}
}

void draw() {
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
	}
}

// SUN CLASS
class Sun {

	int w, mass;
	color myCol;
	PVector loc;

	Sun(int _w, color _myCol) {
		w     = _w;
		myCol = _myCol;
		mass  = w;

		loc  = new PVector(width/2, height/2);
	}

	// Attract a planet
	PVector attract(Planet p) {
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

	void display() {
		fill(myCol);
		ellipseMode(CENTER);
		ellipse(loc.x, loc.y, w, w);
	}
}

class Planet {

	int w, mass;
	color myCol;
	PVector loc, vel, accel;

	Planet(int _w, color _myCol, PVector _loc) {
		w     = _w;
		myCol = _myCol;
		mass  = w;

		loc = _loc;
		vel   = new PVector(2,0);
		accel = new PVector(0,0);
	}

	PVector attract(Moon m) {
		// Initial vector distance of the two points
		PVector force = PVector.sub(loc, m.loc);

		// The magnitude is the distance between the two objects
		float distance = force.mag();

		// Constrain incase the value is below 1 to prevent extreme outcomes.
		distance = constrain(distance, 24, 25);

		// Normalise to find only the direction
		force.normalize();

		// Compute the strength of gravity using Isaac Newton's formula theorem
		float strength = (g * mass * m.mass) / (distance * distance);
		force.mult(strength);

		return force;
	}

	void applyForce(PVector f) {
		PVector force = PVector.div(f, mass);
		accel.add(force);
	}

	void update() {
		vel.add(accel);
		loc.add(vel);

		// Clear the acceleration each time
		accel.mult(0);
	}

	void display() {
		fill(myCol);
		ellipse(loc.x, loc.y, w, w);
	}

}

class Moon extends Planet {

	Moon(int _w, color _myCol, PVector _loc) {
		// Inherit superclass's constructor
		super(_w, _myCol, _loc);
	}

}