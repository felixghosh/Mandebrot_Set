import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class WindowHandler extends Canvas{

    private int width;
    private int height;
    private double depth;
    private Complex focus;
    private int counter = 0;
    private String path = "C:\\Users\\Felix Ghosh\\Desktop\\Mandebrot_Set\\image";
    private int iterations;

    public WindowHandler(int width, int height, double depth, Complex focus, int iterations){
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.focus = focus;
        this.iterations = iterations;
    }

    public void drawWindow(String title){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new WindowHandler(width, height, depth,focus, iterations);
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
        for(int i = 1; i <= iterations; i++){
            newCoordinates = Complex.complexSquare(newCoordinates);
            newCoordinates.add(oldCoordinates);
            counter = i;
            if(newCoordinates.getReal() > 2 && newCoordinates.getImaginary() > 2){
                break;
            }
        }
        int rValue;
        int gValue;
        int bValue;

        if(counter == iterations){
            rValue = 0;
            gValue = 0;
            bValue = 0;
        }else if(counter <= 255){
            rValue = 0;
            gValue = 0;
            bValue = counter % 255;
        } else if(counter <= 511){
            rValue = 0;
            gValue = counter % 255;
            bValue = (counter + 85) % 255;
        } else{
            rValue = counter % 255;
            gValue = (counter + 190) % 255;
            bValue = (counter + 85) % 255;
        }

        /*bValue = counter%255;

        if(counter > 255) rValue = (counter + 85)%255;
        else rValue = 0;

        if(counter > 511) gValue = (counter + 170)%255;
        else gValue = 0;*/

        //System.out.println((newCoordinates.getReal()));


        return new Color(rValue, gValue, bValue);

        //double deltaX = oldCoordinates.getReal() - newCoordinates.getReal();
        //double deltaY = oldCoordinates.getImaginary() - newCoordinates.getImaginary();
        //double distance = Math.sqrt(Math.pow(newCoordinates.getReal(), 2) + Math.pow(newCoordinates.getImaginary(), 2));
        //System.out.println("old: (" + oldCoordinates.getReal() + "," + oldCoordinates.getImaginary() +") new: (" + newCoordinates.getReal() + "," + newCoordinates.getImaginary() + ")");
        //System.out.println(distance);

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

       // paintPixels(g);

        try
        {
            while(true){
                System.out.println(iterations);
                paintPixels(g);
               /* BufferedImage image = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = image.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
                graphics2D.scale(2,2);
                paintPixels(graphics2D);
                String filepath = path + counter +".jpg";
                ImageIO.write(image,"jpeg", new File(filepath));
                counter++;*/
                depth = depth*8;
                iterations = (int)(iterations * 1.1);

            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        //System.out.println("Time: " + (System.currentTimeMillis() - t0));
        /*try {
            while(true){
                paintPixels(g);
                Thread.sleep(1);
                depth = depth*1.5;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
