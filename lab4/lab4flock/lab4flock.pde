// Global variable to dictate size of boid
float bs = 5.0;
// Dictate the number of boids
int nb   = 1000;

// used to determine whether a neighbor or not
float percDis     = 20;
float percAngle   = PI;
float percMinDist = 100;

// weight to attract/repel with
float alignWeight = .5;
float cohWeight   = .025;
float sepWeight   = .01;

ArrayList<Boid> boids = new ArrayList<Boid>();

void setup() {
	size(750, 750);

	for(int i = 0; i < nb; i++) {
		Boid b = new Boid();
		boids.add(b);
	}
}

void draw() {
	background(255);
	for (Boid b : boids) {
		// pass the returned value of getNeighs method into update method for all boids
		b.update(b.getNeighbors(boids));
	  b.display();
	}
}

class Boid {
	PVector loc, vel, accel;
	float velVal = 1;

	Boid() {
		loc = new PVector(random(width), random(height));
		vel = new PVector(random(-velVal, velVal), random(-velVal, velVal));
	}

	void display() {
		pushMatrix();
			translate(loc.x, loc.y);
			// rotate to translate velocity - cast as float
			rotate(-(float)atan2(-vel.y, vel.x));
			// Draw an individual flock
			triangle(-bs/2, bs/2, bs/2, 0, -bs/2, -bs/2);
		popMatrix();
	}

	// Takes an arraylist of boids and returns an arraylist of neighbors
	ArrayList getNeighbors(ArrayList boids) {
		ArrayList<Boid> neighbors = new ArrayList<Boid>();

		// Used to store difference in location
		PVector locDif = new PVector();

	// KEEP ON SCREEN
		if(loc.x < 0) {
			loc.x = width;
		} if (loc.x > width) {
			loc.x = 0;
		} if (loc.y < 0) {
			loc.y = height;
		} if (loc.y > height) {
			loc.y = 0;
		}

		for (int i=0; i<boids.size(); i++) {
			Boid b = (Boid)boids.get(i);
			if(b == this) {
				// Determines whether the boid being compared is the same as the comparison boid - this refers to the current object in context, ie. the one being compared.
				continue;
			} 
			// set location difference to other boids' location
		  locDif.set(b.loc);
		  locDif.sub(loc);

			// if the boid is not a neighbour, ignore this
		  if(locDif.mag() > percDis) {
		  	continue;
		  }

			// If it's outside the perception angle to flock with, this is not a neighbor
		  if(PVector.angleBetween(vel, locDif) > percAngle) {
				continue;
		  }

		  // by now, the rest of the neighbors are ones that we want, so return the rest as an ArrayList
		  neighbors.add(b);
		}

		  return neighbors;
		}

		void update(ArrayList neighbors) {
			// receives an arraylist of neighbors

			// local PVectors to compute
			PVector locDif   = new PVector(),
							velDif   = new PVector(),
							align    = new PVector(),
							cohesion = new PVector(),
							accel    = new PVector(),
							sep      = new PVector();

			// all boids in neighbors
			for (int i=0; i<neighbors.size(); i++) {
				Boid b = (Boid)neighbors.get(i);

				locDif.set(b.loc);
				locDif.sub(loc);

				velDif.set(b.vel);
				velDif.sub(vel);

				// Add these
				align.add(velDif);
				cohesion.add(locDif);
				if(locDif.mag() < percMinDist) {
					// if it's too small
					sep.add(locDif);
				}

				// if not zero, normalise these vectors
				if(align.mag() > 0) {
					align.normalize();
				}
				if(cohesion.mag() > 0) {
					cohesion.normalize();
				}
				if(sep.mag() > 0) {
					sep.normalize();
				}

				align.mult(alignWeight);
				cohesion.mult(cohWeight);
				sep.mult(sepWeight);

				accel.set(align);
				accel.add(cohesion);

				// Separation will go in different direction, therefore subtract
				accel.sub(sep);

				vel.add(accel);
				loc.add(vel);
			}
		}
	}