package model.geometry;

import java.util.List;

public class Grid {

    /*

    An example Grid where the Square elements' respective start x and y Points are shown
    Note that the first Square starts at Point(x=0, y=1) as its end point would be Point(x=1, y=0)

    The bounds of this grid would be Point(x=0, y=0) to Point()

    [0] [1] [2] [3] [4] [5] [6]
    [0] [1] [2] [3] [4] [5] [6]
    [0] [1] [2] [3] [4] [5] [6]
    [0] [1] [2] [3] [4] [5] [6]
    [0,1] [1] [2] [3] [4] [5] [6]

     */
    private List<Square> squares;
    private Point startBound;
    private Point endBound;

    public Grid(Square){

    }

}
