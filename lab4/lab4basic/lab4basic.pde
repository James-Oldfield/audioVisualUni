Sun s;

void setup() {
	size(500, 500);
	background(255);

	s = new Sun(50);

	translate(width/2, height/2);
	s.display();
}

void draw() {

}

// SUN CLASS
class Sun {

	int w;

	Sun(int _w) {
		w = _w;
	}

	void display() {
		ellipseMode(CENTER);
		ellipse(0, 0, w, w);
	}
}

class Planet {

	Planet() {

	}

	void display() {
		
	}

}

class Moon extends Planet {

	Moon() {

	}

	void display() {
		
	}

}