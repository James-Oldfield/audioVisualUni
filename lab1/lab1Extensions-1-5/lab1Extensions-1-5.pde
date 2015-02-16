/**
 * 3D mesh landscape with texture mapping. Includes peasy cam && control p5 for manipulation of the variables and a better 3D view.
 *
 * @param {int} tileCount - The number of tiles on the grid.
 * @param {int} tileSize  - The size of the tiles on the grid.
 * @param {int} meshDist  - Amount to distort squares by.
 * @param {float} z       - Z grid location for 3D.
 * @remote - https://github.com/James-Oldfield/audioVisualUni/blob/master/lab1/lab1Extension/lab1Extension.pde
 *
 */

// peasy cam && control p5
import peasy.*;
import controlP5.*;

PeasyCam  cam;
ControlP5 cp5;
Slider    slider;

// Global variables 
int tileCount = 10;
int tileSize  = 50;
int meshDist  = 10;
float z       = 20;

// PVector to store the tiles in
PVector[][] nodes = new PVector[tileCount+1][tileCount+1];
PImage myTexture;

void setup() {
  size(750, 750, P3D);
	smooth();
	// instantiate library objects
	cam = new PeasyCam(this, 1000);
	cp5 = new ControlP5(this);

	cp5.addSlider("meshDist")
		.setPosition(100,50)
		.setRange(0,50);
	cp5.setAutoDraw(false);

	myTexture = loadImage("texture.jpg");
	textureMode(NORMAL);

	loadNodes();
}

void draw() {
	background(255);
	lights();
	pushMatrix();
		rotateX(0.75);
		// Iterate through number of tiles
		for (int y = 0; y < tileCount; y ++) {
			beginShape(TRIANGLE_STRIP);
			texture(myTexture);
			// iterate through the number of grid points
			for (int x = 0; x <= tileCount; x++) {
				vertex(nodes[x][y].x, nodes[x][y].y, nodes[x][y].z, 0, 0);
				vertex(nodes[x][y+1].x, nodes[x][y+1].y, nodes[x][y+1].z, 0, 1);
			} 
			endShape();
		}
	popMatrix();

	drawGUI();
}

/**
 * Distorts each square by the meshDist
 */
void loadNodes(){

	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {
			nodes[x][y] = new PVector(
					x*tileSize + random(-meshDist, meshDist), 
					y*tileSize + random(-meshDist, meshDist), 
					random(-meshDist, meshDist)
				);
		}
	}
}

/**
 * Draws the GUI separate outside of matrix stack as to remain static.
 */
void drawGUI() {
	hint(DISABLE_DEPTH_TEST);
	cam.beginHUD();
	cp5.draw();
	cam.endHUD();
	hint(ENABLE_DEPTH_TEST);
}

/**
 * Recalls the loadNodes function when slider is dragged
 */
void mouseDragged() {
  if (mouseX >= 100 && 
  	  mouseX <= 200 &&
  	  mouseY >= 50 &&
  	  mouseY <= 75) {
    loadNodes();
  }
}