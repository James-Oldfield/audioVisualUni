PImage myImage;
PVector pos,
				vel,
				s,
				speed;


void setup() {
	size(displayWidth,displayHeight);
	rectMode(CENTER);
	myImage = loadImage("cat.png");

	pos   = new PVector(0,0);
	vel   = new PVector(1,1);
	speed = new PVector(1,1);
}

void draw() {
	s = new PVector(2,3);
	myImage.filter(BLUR, 1);

	pos.add(vel);
	s.add(speed);

	scale(s.x, s.y);

	translate(pos.x, pos.y);
	image(myImage, 0,0, 100, 200);
}