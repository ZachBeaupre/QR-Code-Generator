import jdk.jfr.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//test
public class QRpane extends JPanel {
    static int codeXY = 522;
    static int plength = 29;
    static int CONS = codeXY / plength;
    static boolean tog = true;
    static boolean pdown = false;
    static int X = 28; //temp
    static int Y = 28; //temp
    final String[] pBytes = {"11101100", "00010001"};
    int pByte = 0;
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
     int[][] newQR = defaultQR;

    public QRpane(){
        //temp
        Graphics g;
        setFocusable(true);
        ///ONLY DELETE WHEN DONE WITH EVERYTHING
        //code for generating bit mapping
        //bitmapping? is that even a thing i can call it? im just making a pattern that allows me to write information into the array
//        addKeyListener(new KeyAdapter() {
//                            //some real yandere dev shiz lolol
//                           public void keyPressed(KeyEvent e) {
//                               //System.out.println(e.getKeyCode() + " " + e.getKeyChar());
//
//                               if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
//                                   pdown = true;
//                                   System.out.print("P");
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//
//
//                               }
//                               if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
//                                   X--;
//
//                                   System.out.print("4");
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//                               }
//                               if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
//                                   Y--;
//                                   System.out.print("8");
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//                               }
//
//                               if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
//                                   X++;
//                                   Y--;
//                                   System.out.print("3");
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//                               }
//                               if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
//                                   X++;
//                                   Y++;
//                                   System.out.print("9");
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//                               }
//                               if (e.getKeyCode() == KeyEvent.VK_K) {
//                                   X = 28;
//                                   Y = 28;
//
//                                   System.out.printf("RESET!%n");
//                                   tog = true;
//                                   repaint(0, 0, codeXY + 29, codeXY + 300);
//
//
//                               }
//
//                       }});

qrdraw(newQR, 28,28, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");


//https://www.youtube.com/watch?v=dQw4w9WgXcQ
        repaint(0,0,codeXY+29, codeXY + 300);

    }

    public void paint(Graphics g) {
        drawQR(g);
///only delete when done with everything

//        if(tog) {
//            drawQR(g);
//            tog = false;
//        }
//        g.setColor(Color.RED);
//        g.drawRect(X * CONS, Y * CONS, CONS, CONS);
//        if(pdown){
//
//        g.fillRect(X * CONS, Y * CONS, CONS, CONS);
//        pdown = false;
//        }

    }



    public void qrdraw(int[][] arr, int x, int y, String DATA){

        String BYTE = "0100"; //0100 byte mode
    String D_LENGTH = Integer.toBinaryString(DATA.length()); //test 26

            while (D_LENGTH.length() < 8) {
                D_LENGTH = "0" + D_LENGTH;
            }

    BYTE = BYTE + D_LENGTH;

for(int i = 0; i < DATA.length(); i++){

    String temp = Integer.toBinaryString(DATA.charAt(i));
    while(temp.length() < 8){
        temp = "0" + temp;
    }
    BYTE = BYTE + temp;
}

//add the terminator bits
//Arnold Schwarzenegger lol
        while(BYTE.length() % 8 != 0){
            BYTE += "0";
        }

        //padding bits
        while(BYTE.length() < 440){
            BYTE = BYTE + pBytes[pByte];

            if(pByte == 1){
            pByte = 0;
            }else{
                pByte = 1;
            }
        }
        System.out.println(BYTE);
        System.out.println(BYTE.length()); //before adding EC



        //ERROR CORRECTION CODING - REED SOLOMON
        //3L has 55 codewords
        //15 EC codewords per block
        //for EC split into bytes, find values
        //0100000 first byte
        //So we must split the String of data into bytes, even if the charecters dont align with the spacing, as data is data.
        ArrayList<String> EC_Codewords = new ArrayList<>();
        for (int i = 0; i < BYTE.length(); i += 8) {
            int end = Math.min(i + 8, BYTE.length()); //prevent us from trying to access things out of bounds.
            String chunk = BYTE.substring(i, end);
            EC_Codewords.add(chunk);
        }

////        uncomment to print out the Error  Correction Code Words
//for(int i = 0; i < EC_Codewords.size(); i++){
//    System.out.println(EC_Codewords.get(i));
//}







        //I am setting it up the dumb way so i can figure out the smart way (other than the smart way being anything other than this rofl
        //literally using a turtle what is wrong with me
        //The point of this project was to both entertain me when i was bored and build something for my resume/portfolio thingy sooooo yea
        //the annoyance of making a moves list definately was entertaining
        //i made a little keyboard controlled square that i used to create these values, before that i did it by hand and it took FOREVER
zTurtle z = new zTurtle(x, y);
//z.ArrMan(arr, 5, Integer.parseInt(BYTE.substring(0,1)));
//boolean direction = true;

        //0 means ignore previous move  (dont draw previous move)
int[] actions = {5,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,4,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,4,4,3,4,3,4,3,4,3,0,4,0,3,0,4,0,3,0,4,0,3,0,4,0,3,0,4,0,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,4,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,0,4,0,9,0,4,0,9,0,4,0,9,0,4,0,9,0,4,0,9,4,9,4,9,4,9,4,4,4,3,4,3,4,3,4,3,0,4,3,0,4,3,0,4,3,0,4,3,0,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,0,4,0,3,4,3,4,3,4,3,4,3,4,3,4,4,4,9,4,9,4,9,4,9,4,9,4,9,0,4,0,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,4,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,0,4,0,3,4,3,4,3,4,3,4,3,4,3,4,4,4,9,4,9,4,9,4,9,4,9,4,9,0,4,0,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,9,4,4,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,0,4,0,3,4,3,4,3,4,3,4,3,4,3,4,4,4,9,4,9,4,9,4,9,4,9,4,9,0,4,0,9,4,9,4,9,4,9,4,9,4,9,4,9};

        int aint = 0; //haha

for(int i = 0; i < BYTE.length(); i++){

//use z.checker when making this into an algorithm, I think I understand the approach I will take enough to make it work properly...

if(aint + 1 < actions.length) {
    while (actions[aint + 1] == 0) {
        z.move(actions[aint]);
        aint = aint + 2;
    }
}


    z.ArrMan(arr, actions[aint], Integer.parseInt(BYTE.substring(i, i + 1)));
    aint++;



}

    }
    public void drawQR(Graphics g){

        for (int i = 0; i < plength; i++) {
            for (int j = 0; j < plength; j++) {
                g.setColor(Color.BLACK);
                switch (defaultQR[j][i]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
                    case 1:
                        g.setColor(Color.BLACK);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        break;
                }

                g.fillRect(i * CONS, j * CONS, CONS, CONS);
            }
        }
    }












}
