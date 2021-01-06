public class Main {

    public static void main (String[] args){
        //Complex test = Complex.complexSquare(new Complex(1,2));
        //System.out.println("(" + test.getReal() + "," + test.getImaginary() + ")");
        WindowHandler wh = new WindowHandler(1366,768, 500.0, new Complex(-0.74,0.21));
        wh.drawWindow("Mandelbrot");
    }

}
