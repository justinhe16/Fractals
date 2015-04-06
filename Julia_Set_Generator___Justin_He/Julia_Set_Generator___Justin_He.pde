float zoomx=2;
float zoomy=1.5;
float rangex=2;
float rangexneg=-2;
float rangey=-1.5;
float rangeyneg=1.5;
boolean JuliaGenerated=false;
float JuliaSetMouseX, JuliaSetMouseY;
float JuliaSetCoordinateSavedX, JuliaSetCoordinateSavedY;
ComplexNumber JuliaSetCoordinateSaved;


void setup() {
  colorMode(HSB);
  size(1200, 600);
  background(0, 0, 0);
  for (float x=0; x<800; x++) {
    for (float y=0; y<600; y++) {
      float x1 = map(x, 0, 800, -2, 2);
      float y1 = map(y, 0, 600, 1.5, -1.5);
      ComplexNumber point = new ComplexNumber(x1, y1); //
      ComplexNumber z = new ComplexNumber(0, 0);
      for (int iteration=0; iteration<255; iteration++) {
        z = (z.square()).add(point);
        if (z.magnitude() > 2) {
          if (iteration < 30) {
            stroke(iteration*10, 255, 255);
          } else {
            stroke(iteration, 255, 255);
          }
          point(x, y);
          break;
        }
        //println("hello" + x);
      }
      //println(map(mouseX, 0, 600, -2, 2)); 
      //println(map(mouseY, 0, 600, -1.5, 1.5));
    }
  }
}

void draw() {
  fill(0, 0, 255);
  rect(800, -1, 400, 601);
  ComplexNumber mouseCoordinates = new ComplexNumber((map(mouseX, 0, 800, rangexneg, rangex)), (map(mouseY, 0, 600, rangeyneg, rangey)));
  fill(0);
  textSize(15);
  text("Your mouse Coordinates", 810, 510);
  text("(represented as a ComplexNumber):", 810, 540);
  if (mouseX <= 800) {
    text(mouseCoordinates.toString(), 810, 570);
  } else {
    text("Your mouse is off the Complex Plane!", 810, 570);
  }
  {
    if (JuliaGenerated==false) {
      fill(0);
      text("Welcome to my Julia Set Generator!", 810, 30);
      text("This is a Mandelbrot Set.", 810, 60);
      text("To begin, press any point to produce", 810, 90);
      text("a Julia Set that is unique to the", 810, 120);
      text("Complex Number point you click on.", 810, 150);
    } else if (JuliaGenerated==true) {
      text("This is a Julia Set you generated", 810, 30);
      text("with the ComplexNumber", 810, 60);
      text(JuliaSetCoordinateSaved.toString(), 810, 90);
      text("LEFT CLICK to zoom in", 810, 120);
      text("RIGHT CLICK to zoom out", 810, 150);
      fill(0, 0, 0);
      stroke(0, 0, 0);
      rect(840, 200, 315, 100, 10);
      fill(0, 0, 255);
      textSize(21);
      text("RETURN TO THE MANDELBROT", 842, 255);
    }
  }
  if (JuliaGenerated == true && mouseX > 840 && mouseX < 1155 && mouseY > 200 && mouseY < 300) {
    fill(0, 0, 255);
    stroke(0, 0, 0);
    rect(840, 200, 315, 100, 10);
    fill(0, 0, 0);
    textSize(21);
    text("RETURN TO THE MANDELBROT", 842, 255);
  }
}

void mouseClicked() {
  if (mouseButton == LEFT && mouseX <= 800) { //julia set generator + zoom
    if (JuliaGenerated==false) { //on first click, generates julia set
      background(0, 0, 0);
      for (float x=0; x<800; x++) {
        for (float y=0; y<600; y++) {
          float x1 = map(x, 0, 800, -2, 2);
          float y1 = map(y, 0, 600, 1.5, -1.5);
          float mousex1 = map(mouseX, 0, 800, -2, 2);
          float mousey1 = map(mouseY, 0, 600, 1.5, -1.5);
          ComplexNumber point = new ComplexNumber(mousex1, mousey1); //
          ComplexNumber z = new ComplexNumber(x1, y1);
          JuliaSetMouseX = mousex1;
          JuliaSetMouseY = mousey1;
          for (int iteration=0; iteration<255; iteration++) {
            z = (z.square()).add(point);
            if (z.magnitude() > 2) {
              if (iteration < 30) {
                stroke(iteration*10, 255, 255);
              } else {
                stroke(iteration, 255, 255);
              }
              point(x, y);
              break;
            }
            //println("hello" + x);
          }
          JuliaSetCoordinateSavedX = mousex1;
          JuliaSetCoordinateSavedY = mousey1;
        }
      }
      JuliaSetCoordinateSaved = new ComplexNumber(JuliaSetCoordinateSavedX, JuliaSetCoordinateSavedY);
      JuliaGenerated = true;
    } else { // if julia set was already generated, zoom in
      background(0, 0, 0);
      zoomx = zoomx/2;
      zoomy = zoomy/2;
      float mousex1 = map(mouseX, 0, 800, rangexneg, rangex);
      float mousey1 = map(mouseY, 0, 600, rangeyneg, rangey);
      for (float x=0; x<800; x++) {
        for (float y=0; y<600; y++) {
          float x1 = map(x, 0, 800, (-1*zoomx)+mousex1, zoomx+mousex1);
          float y1 = map(y, 0, 600, zoomy+mousey1, (-1*zoomy)+mousey1);
          ComplexNumber point = new ComplexNumber(JuliaSetMouseX, JuliaSetMouseY); //
          ComplexNumber z = new ComplexNumber(x1, y1);
          for (int iteration=0; iteration<255; iteration++) {
            z = (z.square()).add(point);
            if (z.magnitude() > 2) {
              if (iteration < 30) {
                stroke(iteration*10, 255, 255);
              } else {
                stroke(iteration, 255, 255);
              }
              point(x, y);
              break;
            }
          }
        }
      }
      rangex = (zoomx)+mousex1;
      rangexneg = (-1*zoomx)+mousex1;
      rangey = (-1*zoomy)+mousey1;
      rangeyneg = zoomy+mousey1;
    }
    fill(0, 0, 255);
    rect(800, -1, 400, 601);
  }
  if (mouseButton == RIGHT && mouseX <= 800) { // zoom out
    background(0, 0, 0);
    zoomx = zoomx*2;
    zoomy = zoomy*2;
    float mousex1 = map(mouseX, 0, 800, rangexneg, rangex);
    float mousey1 = map(mouseY, 0, 600, rangeyneg, rangey);
    for (float x=0; x<800; x++) {
      for (float y=0; y<600; y++) {
        float x1 = map(x, 0, 800, (-1*zoomx)+mousex1, zoomx+mousex1);
        float y1 = map(y, 0, 600, zoomy+mousey1, (-1*zoomy)+mousey1);
        ComplexNumber point = new ComplexNumber(JuliaSetMouseX, JuliaSetMouseY); //
        ComplexNumber z = new ComplexNumber(x1, y1);
        for (int iteration=0; iteration<255; iteration++) {
          z = (z.square()).add(point);
          if (z.magnitude() > 2) {
            if (iteration < 30) {
              stroke(iteration*10, 255, 255);
            } else {
              stroke(iteration, 255, 255);
            }
            point(x, y);
            break;
          }
        }
      }
    }
    rangex = (zoomx)+mousex1;
    rangexneg = (-1*zoomx)+mousex1;
    rangey = (-1*zoomy)+mousey1;
    rangeyneg = zoomy+mousey1;
  }
  if (JuliaGenerated == true && mouseX > 840 && mouseX < 1155 && mouseY > 200 && mouseY < 300) {
    background(0, 0, 0);
    for (float x=0; x<800; x++) {
      for (float y=0; y<600; y++) {
        float x1 = map(x, 0, 800, -2, 2);
        float y1 = map(y, 0, 600, 1.5, -1.5);
        ComplexNumber point = new ComplexNumber(x1, y1); //
        ComplexNumber z = new ComplexNumber(0, 0);
        for (int iteration=0; iteration<255; iteration++) {
          z = (z.square()).add(point);
          if (z.magnitude() > 2) {
            if (iteration < 30) {
              stroke(iteration*10, 255, 255);
            } else {
              stroke(iteration, 255, 255);
            }
            point(x, y);
            break;
          }
          //println("hello" + x);
        }
        //println(map(mouseX, 0, 600, -2, 2)); 
        //println(map(mouseY, 0, 600, -1.5, 1.5));
      }
    }
    zoomx=2;
    zoomy=1.5;
    rangex=2;
    rangexneg=-2;
    rangey=-1.5;
    rangeyneg=1.5;
    JuliaGenerated = false;
  }
}

