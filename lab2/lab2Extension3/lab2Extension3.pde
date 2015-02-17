PImage myImage;
PVector pos,
        vel,
        s,
        speed;


void setup() {
  size(displayWidth,displayHeight);
  rectMode(CENTER);
  myImage = loadImage("cat.png");

  pos   = new PVector(0,0);
  vel   = new PVector(1,1);
  speed = new PVector(1,1);
}

void draw() {
  background(255);
  s = new PVector(2,3);


  if (pos.y+300 >= height || pos.y < 0) {
    speed = new PVector(-speed.x, -speed.y);
    vel = new PVector(vel.x, -vel.y);
    speed.mult(0);
  } else if (pos.x+300 >= width || pos.x < 0) {
    speed = new PVector(-speed.x, -speed.y);
    vel = new PVector(-vel.x, vel.y);
    speed.mult(0);
  }
    vel.add(speed);
    pos.add(vel);

  translate(pos.x, pos.y);
  image(myImage, 0,0, 300, 300);
}