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

public class lab3Extension1 extends PApplet {

int amp   = 100;
float s   = 0.00f;
float[] h;

public void setup() {
	size(500, 500);
}

public void draw() {
	background(255);
	stroke(0);
	amp += map(mouseX, 0, height, -10, 10);
	for (int i = 0; i < width; i++) {
		float s = map(sin(radians(i)), -1, 1, -amp, amp);
		point(i, height/2 + s);
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lab3Extension1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
