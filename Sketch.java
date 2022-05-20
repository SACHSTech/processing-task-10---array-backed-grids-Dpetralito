import processing.core.PApplet;

public class Sketch extends PApplet {
  
  int [][] intGrid;
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;
  int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
  int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;
  int column;
  int rows;
  int intSelectedBlock = 0;
  int intSelectedRows = 0;
  int intRowTotal = 0;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    
    // put your size call here
    size (SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    
    background(0);
    intGrid = new int [ROW_COUNT][COLUMN_COUNT];
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
	  fill (255);
    
    for (int column = 0; column < COLUMN_COUNT; column++){
      for (int rows = 0; rows < ROW_COUNT; rows++)
        
    if (intGrid[rows][column] == 1) {
          fill (0, 255, 0);
          rect (MARGIN * (column + 1) + (column * CELL_WIDTH), MARGIN * (rows + 1) + (rows * CELL_HEIGHT), CELL_HEIGHT, CELL_WIDTH);
        } else {
          fill (255, 255, 255);
          rect (MARGIN * (column + 1) + (column * CELL_WIDTH), MARGIN * (rows + 1) + (rows * CELL_HEIGHT), CELL_HEIGHT, CELL_WIDTH);
        }
        
      }
    }

  public void mousePressed(){
    
  for (int rows = 0; rows < ROW_COUNT; rows++ ) {
      for (int column = 0; column < COLUMN_COUNT; column++) {
        
        if (dist(MARGIN * (column + 1) + (column * CELL_WIDTH) + 10, MARGIN * (rows + 1) + (rows * CELL_HEIGHT) + 10, mouseX, mouseY) < 10 ) {
          System.out.println("mouse coordinates: " + mouseX + ", " + mouseY + " grid coordinates: row: " + (rows + 1) + " column: " + (column + 1));
          
            intGrid[rows][column] ^= 1;
            if (intGrid[rows][column] == 1) {
              intSelectedBlock++;
            } else {
              intSelectedBlock--;
            }
          
            if(rows < 9) {
              intGrid[rows + 1][column] ^= 1;
              if (intGrid[rows + 1][column] == 1) {
                intSelectedBlock++;
              } else {
                intSelectedBlock--;
              }
            }
          
            if(column < 9) {
              intGrid[rows][column + 1] ^= 1;
              if (intGrid[rows][column + 1] == 1) {
                intSelectedBlock++;
              } else {
                intSelectedBlock--;
              }
            }

            if(rows > 0) {
              intGrid[rows - 1][column] ^= 1;
              if (intGrid[rows - 1][column] == 1) {
                intSelectedBlock++;
              } else {
                intSelectedBlock--;
              }
            }
            
            if(column > 0) {
              intGrid[rows][column - 1] = 1;
              if (intGrid[rows][column - 1] == 1) {
                intSelectedBlock++;
              } else {
                intSelectedBlock--;
              }
            }
        }
      }
    }
    
System.out.println("Total of " + intSelectedBlock + " selected");
   
    for (int rows = 0; rows < ROW_COUNT; rows++) {
      for (int column = 0; column < COLUMN_COUNT; column++) {
        if (intGrid[rows][column] == 1) {
          intSelectedRows++;
        }
        
        if (column < COLUMN_COUNT - 1 && column > 0) {
          if (intGrid[rows][column] == 1 && intGrid[rows][column - 1] == 1) {
            intRowTotal++;
          } else if (column < COLUMN_COUNT-1 && column > 0) {
            if (intGrid[rows][column] == 1 && intGrid[rows][column + 1] == 1) {
              intRowTotal++;
            }
          }
        }
      }
    }
  }
}