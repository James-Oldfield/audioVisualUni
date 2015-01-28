import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class lab1Extension extends PApplet {

// peasy cam && control p5



PeasyCam cam;
ControlP5 cp5;
Slider slider;

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

public void setup() {
	size(750, 750, P3D);
	// instantiate library objects
	cam = new PeasyCam(this, 1000);
	cp5 = new ControlP5(this);

	cp5.addSlider("meshDist").setPosition(100,50).setRange(0,255);
	cp5.setAutoDraw(false);

	myTexture = loadImage("texture.jpg");
	textureMode(NORMAL);

	loadNodes();
}

public void draw() {
	background(255);

	pushMatrix();
		rotateX(0.75f);
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
	popMatrix();

	// draw controlp5
	drawGUI();
}

// void mouseClicked() {
// 	// randomise the depth z property on click
// 	for (int x = 0; x < tileCount+1; x++) {
// 		for (int y = 0; y <= tileCount; y++) {
// 			nodes[x][y].z = random(-meshDist*2, meshDist*2);
// 		}
// 	}
// }

public void loadNodes(){
		// Distort each rectangle using meshDist variable
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

public void drawGUI(){
	hint(DISABLE_DEPTH_TEST);
	cam.beginHUD();
	cp5.draw();
	cam.endHUD();
	hint(ENABLE_DEPTH_TEST);
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP) {
	loadNodes();
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
