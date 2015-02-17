int amp   = 100;
float s   = 0.00;

void setup() {
	size(500, 500);
}

void draw() {
	background(255);
	stroke(0);
	amp += map(mouseX, 0, height, -10, 10);
	for (int i = 0; i < width; i++) {
		float s = map(sin(radians(i)), -1, 1, -amp, amp);
		point(i, height/2 + s);
	}
}