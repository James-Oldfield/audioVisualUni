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

// two integers, s and i, are initalised to 900 and 0 respectively.
int s=900, i=0;
public void setup() {
	// 900 is being used as the width of the sketch and P3D is passed as the final method in the size function to enable 3D
  size(1200, s, P3D);
}
public void draw() {
	// The sketch is translated 600 pixels across and 450 pixels down 
  translate(600, 450);
  // x is being rotated by a fraction of the incrementing i value.
  rotateX(i*.0021f);
  // Each frame, a gray value increments up to 256 then back to black, with an alpha value of 30. This creates a sudden glowing effect.
  fill(i++%256, 30);
  // 
  sphere(sin(i*.0014f)*s);
}// #p5
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab2extension" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
