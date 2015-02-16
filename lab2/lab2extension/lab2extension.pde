// Convolution matrices for image filter effects

PImage img;
float[][] matrix = {
	{-1, -1, -1},
	{-1, 9, -1},
	{-1, -1, -1},
};

void setup() {
	img = loadImage("cat.png");
	size(img.width, img.height);
}

void draw() {
	image(img, 0, 0);

for (int x = 0; x < width; x++ ) {
    for (int y = 0; y < height; y++ ) {
      // Each pixel location (x,y) gets passed into a function called convolution()
      // The convolution() function returns a new color to be displayed.
      color c = convolution(x,y,matrix,matrixsize,img); 
      int loc = x + y*img.width;
      pixels[loc] = c;
    }
  }
}