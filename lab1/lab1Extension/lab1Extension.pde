// Global variables 
int tileCount = 10;
int tileSize  = 50;

// Amount to distort squares by
int meshDist  = 5;

// Z grid location for 3D
float z = 20;

// PVector to store the tiles in
PVector[][] nodes = new PVector[tileCount+1][tileCount+1];

void setup() {
	size(750, 750, P3D);

	// Distort each rectangle using meshDist variable
	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {

			// random z value
			z = random(-20, 20);

			nodes[x][y] = new PVector(x*tileSize + random(-meshDist, meshDist), y*tileSize + random(-meshDist, meshDist), z);
		}
	}
}

void draw() {
	background(255);
	// Iterate through number of tiles
	for (int y = 0; y < tileCount; y ++) {
		beginShape(QUAD_STRIP);
		// iterate through the number of grid points
		for (int x = 0; x <= tileCount; x++) {
			vertex(nodes[x][y].x, nodes[x][y].y, nodes[x][y].z);
			vertex(nodes[x][y+1].x, nodes[x][y+1].y, nodes[x][y+1].z);
		} 
		endShape();
	}
}