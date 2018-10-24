package model.geometry;

import java.util.List;

public class Grid {

    /* todo need to double check these coords, not certain my logic is fully correct here

    An example Grid where the Square elements' respective start x and y Points are shown
    Note that the first Square starts at Point(x=0, y=1) as its end point would be Point(x=1, y=0)

    The bounds of this grid would be Point(x=0, y=0) to Point(x=7, y=5)

    [0,5] [1,5] [2,5] [3,5] [4,5] [5,5] [6,5]
    [0,4] [1,4] [2,4] [3,4] [4,4] [5,4] [6,4]
    [0,3] [1,3] [2,3] [3,3] [4,3] [5,3] [6,3]
    [0,2] [1,2] [2,2] [3,2] [4,2] [5,2] [6,2]
    [0,1] [1,1] [2,1] [3,1] [4,1] [5,1] [6,1]

     */
    private Square[][] squares;
    private Point startBound;
    private Point endBound;

    public Grid(Point startBound, Point endBound){ //todo problems here
        this.startBound = startBound;
        this.endBound = endBound;
        for(int xCol = startBound.getX(); xCol < endBound.getX(); xCol++){
            for(int yRow = startBound.getY(); yRow < endBound.getY(); yRow++){
                squares[yRow][xCol] = new Square(xCol, yRow);
                System.out.println("Created new Square: "+ squares[yRow][xCol].toString());
            }
        }
    }

    public Point getStartBound() {
        return startBound;
    }

    public Point getEndBound() {
        return endBound;
    }

    public Square[][] getSquares() {
        return squares;
    }

    /*
    public static void main(String[] args){
        new Grid(new Point(0, 0), new Point(50, 25));
    }
    */

}
