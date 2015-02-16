int numVerts = 360;
PImage photo;

void setup() {
  size(600, 600, P3D); 
  photo = new PImage();
  photo = loadImage("cat.jpg");
}

void draw() {
  background(255);
  float f = 25 * sin(0.005 * millis() + 5);
  translate(width/2, height/2 + f);

  noStroke();

  beginShape();
  textureMode(NORMAL);
  texture(photo);
  for (float i=0; i<=numVerts; i++) {
    float _c = cos(radians(i));
    float _s = sin(radians(i));

    float c = map(_c, -1, 1, 0, 1);
    float s = map(_s, -1, 1, 0, 1);

    vertex(250*cos(radians(i)), 250*sin(radians(i)), c, s);
  }
  endShape();
}

