public class Main {

    public static void main (String[] args){
        //Complex test = Complex.complexSquare(new Complex(1,2));
        //System.out.println("(" + test.getReal() + "," + test.getImaginary() + ")");
        // - 0.74, 0.21
        // 0.26, 0.0018
        // -0.4601222,-0.5702860
        // -0.4621603,-0.5823998
        // -4.597541e-1,-5.702093e-1
        // -4.595053e-1, -5.810975e-1
        // -0.45961869,-0.57017670
        WindowHandler wh = new WindowHandler(1920,1080, 5.1539607552E12, new Complex(  -0.45961869544195,
                                                                                                             -0.570176693000503), 7832);
        wh.drawWindow("Mandelbrot");
    }

}
