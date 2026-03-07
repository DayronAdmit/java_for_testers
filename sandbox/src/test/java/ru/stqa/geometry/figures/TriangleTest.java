package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriangleTest {

    @Test
    void canCalculatePerimeterForEquilateralTriangle() {
        var t = new Triangle(2, 2, 2);
        Assertions.assertEquals(6.0, t.perimeter());
    }

    @Test
    void canCalculatePerimeterWithRightAngle() {
        var t = new Triangle(3, 4, 5);
        Assertions.assertEquals(12.0, t.perimeter());
    }

    @Test
    void canCalculatePerimeterForIsoscelesTriangle() {
        var t = new Triangle(8.5, 5.1, 5.1);
        Assertions.assertEquals(18.7, t.perimeter());
    }

    @Test
    void canCalculateSquareForEquilateralTriangle() {
        var t = new Triangle(2, 2, 2);
        Assertions.assertEquals(1.73, roundToTwoDecimal(t.square()));
    }

    @Test
    void canCalculateSquareWithRightAngle() {
        var t = new Triangle(3, 4, 5);
        Assertions.assertEquals(6.0, roundToTwoDecimal(t.square()));
    }

    @Test
    void canCalculateSquareForIsoscelesTriangle() {
        var t = new Triangle(8.5, 5.1, 5.1);
        Assertions.assertEquals(11.98, roundToTwoDecimal(t.square()));
    }

    private double roundToTwoDecimal(double d) {
        return Math.round(d * 100) / 100.0;
    }
}