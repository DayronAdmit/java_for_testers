package ru.stqa.geometry.figures;

public class Triangle {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Длина ребра не может быть отрицательным числом");
        }
        if (a + b < c || b + c < a || c + a < b) {
            throw new IllegalArgumentException("Сумма длин двух сторон теругольника не должна быть меньше длины третьей");
        }
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double square() {
        var p = this.perimeter() / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }
}
