import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 *   The QR-GEN program will allow users to encode information onto a QR code
 *   The top of the application will be a 500 x 500 pixel display of your QR code. ~the pixels in the QR code will be made up of Rectangle2D's
 *   This display will be a visual representation of a 2D array using the java Graphics2D libraries.
 *   //
 *   //
 *   The bottom of the application
 *   User will have a text field to input their data
 *   A button to submit the data in the field
 *   ~Optional the user will be able to specify the amount of error correction applied to the final QR code.
 *   https://www.thonky.com/qr-code-tutorial/introduction
 *   I plan to use this website to help with the implementation of the qr code generator
 *
 *   Recently stumbled upon another website that might help me out with the arduous task of adding the error correction to the generator.
 *   https://www.nayuki.io/page/creating-a-qr-code-step-by-step
 *
 *
**/
//P4P3P4P3434343434343434343434343434343434344494949494949494949494949494949494949494443434343P4P3P4P3P4P3P4P3P4P343434343434343434343444949494949494949494949P4P9P4P9P4P9P4P9P4P94949494443434343P43P43P43P43P4343434343434343434343434343P4P3434343434344494949494949P4P94949494949494949494949494949494949494949494443434343434343434343434343434343434343434343P4P3434343434344494949494949P4P94949494949494949494949494949494949494949494443434343434343434343434343434343434343434343P4P3434343434344494949494949P4P9494949494949
public class QR {
//this is my second experiment with ui elements and stuff so take this program with a grain of salt
    //this program will be my first time combining my experience with Graphics libraries and my minimal UI element experience together
//     https://www.thonky.com/qr-code-tutorial/introduction
    static int codeXY = 522;
    static int plength = 29;
    static int CONS = codeXY / plength;
    public static Rectangle2D.Double lazy(){
     return new Rectangle2D.Double();
    }
    public static void main(String args[]) {
        // https://youtu.be/dQw4w9WgXcQ
        // https://www.youtube.com/watch?v=dQw4w9WgXcQ
        String input = "Hello World";
        //440 green, and 127 blue
        //version 3, error correction L, byte mode
        //53 character maximum
        int[][] defaultQR = {
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 1, 0, 0},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 1, 2, 2, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 2, 2, 2, 2},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 1, 2, 2, 2, 2},
                {1, 0, 1, 1, 1, 0, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2},
                {1, 0, 1, 1, 1, 0, 1, 0, 0, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 0, 1, 1, 1, 0, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}

        };

        JFrame frame = new JFrame("QR-GEN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(codeXY+14, codeXY + 300);
        frame.setResizable(false);
        frame.setVisible(true);

//01000001000001110111011101110111011100101110011110010110111101110101011101000111010101100010011001010010111001100011011011110110110100101111
        frame.setContentPane(new QRpane());
        frame.repaint(0,0,codeXY+29, codeXY + 300);
        //why did i do this to myself
        //answer: it was kinda fun, also this information might help me figure out how to create a scalable algorithm
        //mainly, I was just too lazy to make that algorithm
        //kinda funny that i did something harder just to avoid making an algorithm... I guess I just like challenges

       String IT = "5434343434343434343434343434343434343434449494949494949494949494949494949494949444343434P3P4P3P4P3P4P3P4P3P434343434343434343434344494949494949494949494P9P4P9P4P9P4P9P4P9P49494949444343434P34P34P34P34P3434343434343434343434343434P3P4343434343434449494949494P9P49494949494949494949494949494949494949494949444343434343434343434343434343434343434343434P3P4343434343434449494949494P9P49494949494949494949494949494949494949494949444343434343434343434343434343434343434343434P3P4343434343434449494949494P9P49494949494949";
System.out.print("int[] actions = {");
      for(int i = 0; i < IT.length()-1; i++){
          System.out.printf("%c,",IT.charAt(i));
      }
//gihub pls
        System.out.printf("%c};",IT.charAt(IT.length() - 1));




}


}









