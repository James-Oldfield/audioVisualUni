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

public class lab1Extension extends PApplet {

// Global variables 
int tileCount = 10;
int tileSize = 50;
// Amount to distort squares by
int meshDist  = 5;
// Z grid location for 3D
float z = 20;

// PVector to store the tiles in
PVector[][] nodes = new PVector[tileCount+1][tileCount+1];
PImage myTexture;

public void setup() {
	size(750, 750, P3D);

	myTexture = loadImage("texture.jpg");
	textureMode(NORMAL);

	// Distort each rectangle using meshDist variable
	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {

			// random z value
			z = random(-20, 20);

			nodes[x][y] = new PVector(x*tileSize + random(-meshDist, meshDist), y*tileSize + random(-meshDist, meshDist), z);
		}
	}
}

public void draw() {
	background(255);
	translate(175, 175);
	rotateX(0.75f);
	scale(0.75f);
	// Iterate through number of tiles
	for (int y = 0; y < tileCount; y ++) {
		beginShape(QUAD_STRIP);
		texture(myTexture);
		// iterate through the number of grid points
		for (int x = 0; x <= tileCount; x++) {
			vertex(nodes[x][y].x, nodes[x][y].y, nodes[x][y].z, 0, 1);
			vertex(nodes[x][y+1].x, nodes[x][y+1].y, nodes[x][y+1].z, 1, 0);
		} 
		endShape();
	}
}

public void mouseClicked() 
{
	for (int x = 0; x < tileCount+1; x++) {
		for (int y = 0; y <= tileCount; y++) {
		nodes[x][y].z = random(-20,20);
	}
}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab1Extension" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
