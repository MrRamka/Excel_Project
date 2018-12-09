package com.yabcompany;

import com.yabcompany.Graphs.PointGraph;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

public class Main extends JFrame {

        public static void main(String[] args) {
            /*FileInputStream fis;
            Properties property = new Properties();

            try {
                fis = new FileInputStream("C:\\Users\\ramil\\Excel\\src\\com\\yabcompany\\config.properties");
                property.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int width = Integer.parseInt(property.getProperty("screen.width"));
            int height = Integer.parseInt(property.getProperty("screen.height"));

            // Constructs a BufferedImage of one of the predefined image types.
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Create a graphics which can be used to draw into the buffered image
            Graphics2D g2d = bufferedImage.createGraphics();

            // fill all the image with white
            g2d.setColor(Color.white);
            g2d.fillRect(0, 0, width, height);

            // create a circle with black
            g2d.setColor(Color.black);
            g2d.fillOval(width/4, 0, height, height);

            // create a string with yellow
            g2d.setColor(Color.yellow);
            g2d.drawString(property.getProperty("screen.height"), width/2, height/2);

            // Disposes of this graphics context and releases any system resources that it is using.
            g2d.dispose();

            // Save as PNG
            File file = new File("C:\\Users\\ramil\\Desktop\\image.png");
            try {
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            String graphName = "Points scored";
            String[] names = {"Team 1", "Team 2","Team 3","Team 4",};
            String[] param = {"Period 1", "Period 2"};
            int[][] values = {{24, 89}, {36, 24}, {12, 37}, {38, 67}};

            GraphPrinter.pointGraph(graphName,names,param,values);


    }
}
