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

public class lab2extension extends PApplet {

// Convolution matrices for image filter effects

PImage img;
float[][] matrix = {
	{-1, -1, -1},
	{-1, 9, -1},
	{-1, -1, -1},
};

public void setup() {
	img = loadImage("cat.png");
	size(img.width, img.height);
}

public void draw() {
	image(img, 0, 0);

for (int x = 0; x < width; x++ ) {
    for (int y = 0; y < height; y++ ) {
      // Each pixel location (x,y) gets passed into a function called convolution()
      // The convolution() function returns a new color to be displayed.
      int c = convolution(x,y,matrix,matrixsize,img); 
      int loc = x + y*img.width;
      pixels[loc] = c;
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab2extension" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
