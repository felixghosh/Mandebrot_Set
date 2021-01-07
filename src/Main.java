public class Main {

    public static void main (String[] args){
        //Complex test = Complex.complexSquare(new Complex(1,2));
        //System.out.println("(" + test.getReal() + "," + test.getImaginary() + ")");
        // - 0.74, 0.21
        // 0.26, 0.0018
        // -0.4601222,-0.5702860
        // -0.4621603,-0.5823998
        WindowHandler wh = new WindowHandler(1920,1080, 500.0, new Complex(-4.597541e-1,-5.702093e-1));
        wh.drawWindow("Mandelbrot");
    }

}
