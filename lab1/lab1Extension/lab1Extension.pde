// peasy cam
import peasy.*;
PeasyCam cam;

// Global variables 
int tileCount = 10;
int tileSize = 50;
// Amount to distort squares by
int meshDist  = 10;
// Z grid location for 3D
float z = 20;

// PVector to store the tiles in
PVector[][] nodes = new PVector[tileCount+1][tileCount+1];
PImage myTexture;

void setup() {
	size(750, 750, P3D);
	cam = new PeasyCam(this, 0, 0, 0, 50);

	myTexture = loadImage("texture.jpg");
	textureMode(NORMAL);

	// Distort each rectangle using meshDist variable
	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {
			nodes[x][y] = new PVector(
					x*tileSize + noise(-meshDist, meshDist), 
					y*tileSize + noise(-meshDist, meshDist), 
					random(-meshDist*2, meshDist*2)
				);
		}
	}
}

void draw() {
	background(255);
	rotateX(0.75);
	scale(0.75);
	// Iterate through number of tiles
	for (int y = 0; y < tileCount; y ++) {
		beginShape(QUAD_STRIP);
		texture(myTexture);
		// iterate through the number of grid points
		for (int x = 0; x <= tileCount; x++) {
			vertex(nodes[x][y].x, nodes[x][y].y, nodes[x][y].z, 1, 0);
			vertex(nodes[x][y+1].x, nodes[x][y+1].y, nodes[x][y+1].z, 1, 1);
		} 
		endShape();
	}
}

void mouseClicked() {
	// randomise the depth z property on click
	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {
			nodes[x][y].z = random(-meshDist*2, meshDist*2);
		}
	}
}