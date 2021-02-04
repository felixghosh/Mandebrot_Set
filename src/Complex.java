import java.math.BigDecimal;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double re, double im){
        this.real = re;
        this.imaginary = im;
    }

    public double getReal(){
        return real;
    }

    public double getImaginary(){
        return imaginary;
    }

    public static Complex complexSquare(Complex c){
        double newReal = (c.real * c.real) - (c.imaginary * c.imaginary);
        double newImaginary = (c.real * c.imaginary)*2;
        return new Complex(newReal, newImaginary);
    }

    public void add(Complex c){
        real += c.getReal();
        imaginary += c.getImaginary();
    }

}
