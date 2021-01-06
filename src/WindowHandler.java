import java.awt.*;
import javax.swing.JFrame;

public class WindowHandler extends Canvas{

    private int width;
    private int height;
    private double depth;
    private Complex focus;

    public WindowHandler(int width, int height, double depth, Complex focus){
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.focus = focus;
    }

    public void drawWindow(String title){
        JFrame frame = new JFrame(title);
        Canvas canvas = new WindowHandler(width, height, depth,focus);
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    private double transformX(int x, double depth){
        double newX = (x/depth) - (width/(2*depth)) + focus.getReal();
        return newX;
    }

    private double transformY(int y, double depth){
        double newY = -(y/depth) + (height/(2*depth)) + focus.getImaginary();
        return newY;
    }

    private Color calcMandelbrotColor(double x, double y){
        //if(x == 0.0 || y == 0.0) return Color.red;
        Complex oldCoordinates = new Complex(x,y);
        Complex newCoordinates = new Complex(0,0);
        int counter = 0;
        for(int i = 1; i <= 255; i++){
            newCoordinates = Complex.complexSquare(newCoordinates);
            newCoordinates.add(oldCoordinates);
            if(newCoordinates.getReal() > 2 && newCoordinates.getImaginary() > 2){
                counter = (int)((float)i*1);
                //System.out.println(i);
                break;
            }
        }
        double deltaX = oldCoordinates.getReal() - newCoordinates.getReal();
        double deltaY = oldCoordinates.getImaginary() - newCoordinates.getImaginary();
        double growth = deltaY / deltaX;
        //System.out.println("old: (" + oldCoordinates.getReal() + "," + oldCoordinates.getImaginary() +") new: (" + newCoordinates.getReal() + "," + newCoordinates.getImaginary() + ")");
        //System.out.println(growth);
        if(counter > 255) {
            //System.out.println("Counter: " + counter);
            return new Color(255,255,255);
        } else return new Color(0,0,counter);
        //return new Color((float)0.0,(float)0.0,counter);


        //return Color.red;
    }

    private Color getPixelColor(int x, int y){
        //if(x == width/2 || y == height/2){
        //   return Color.red;
        //} else return Color.gray;
        double newX = transformX(x, depth);
        double newY = transformY(y, depth);
        Color c = calcMandelbrotColor(newX,newY);
        return c;
    }

    private void paintPixels(Graphics g){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = getPixelColor(x,y);
                g.setColor(c);
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        //long t0 = System.currentTimeMillis();


        //System.out.println("Time: " + (System.currentTimeMillis() - t0));
        try {
            while(true){
                paintPixels(g);
                Thread.sleep(1000);
                depth = depth*1.5;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("(600,350) -> (" + newX + "," + newY + ")");
    }

}
