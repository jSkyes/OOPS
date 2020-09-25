import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;

abstract class NonNegativeInteger {
    public NonNegativeInteger();
    public boolean equals (Object that);
    public void set(int v) throws IllegalArgumentException;
    public int get();
}

public void setTest(){
    //•	if set has not been called, get should return 0
    //•	if set has been called, get should return the value of the last set
    //•	if set is called with a negative number, the IllegalArgumentException is raised

    NonNegativeInteger first = new NonNegativeInteger();
    //test set has not been called yet returns a 0
    Assertions.assertTrue(0 == first.get());

    // check that if set is called it'll return the last version set
    first.set(5);
    Assertions.assertTrue(5 == first.get());

    //check neg nums don't work, throw IllegalArg
    try {
        first.set(-5);
        first.get(); //should return IllegalArgumentException
    }catch (IllegalArgumentException e) {

    }
}}

/*Shape c = ShapeFactory.newCircle();	\\ creates a circle
	Shape r = ShapeFactory.newRectangle();	\\ creates a rectangle
	Shape t = ShapeFactory.newTranslate();	\\ creates a translate
Shape o = ShapeFactory.newComplexShape(); \\ creates a complexShape
*/

class ShapeFactory {
    //factorys work by checking what type is passed in and creating that object that is passed in
    //but rather than checking types, this one just picks methods
    //creates and returns the type of object it calls for

    ArrayList<Shape> shapes = new ArrayList<>();

    public Shape newRectangle(){
        return new rectangleObj();
    }

    public Shape newCircle(){
        return new circleObj();
    }

    public Shape newTranslate(){
        return new circleObj();
    }

    public Shape newComplexShape(){
        return new complexObj();
    }

    public void addShape(Shape s){
        //add a shape to the composite
        shapes.add(s);
    }

    public void draw(Color c){
        //for each shape in the composite
        for (Shape s: shapes) {
            //call the object's respective draw method
            s.draw(c);
        }
    }

    //Write a correct implementation of the draw method in Translate.
    // Hint : Move cursor by amount (_x,_y), then draw, then
    // restore the cursor to its original position.
    //class Translate implements Shape {
    abstract class Translate implements Shape {

        Translate(){

        };
        public void draw(Color c) {
            //t.setXY(2,3);
            this.setXY(_x, _y);
            //draw the shape of whatever shape the translate shape is?
            this.shape.draw(c);
            //reset the the cursor to the origin??
            this.setXY(0, 0);
        }
    }
}
