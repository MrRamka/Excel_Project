package com.yabcompany;

import com.yabcompany.GraphPrinter.GraphPrinter;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String configPath = "C:\\Users\\Rasim\\Desktop\\HELLO\\settings.properties";
        String destinationPath = "C:\\Users\\Rasim\\Desktop\\HELLO";
        String graphName = "Points scored";
        String[] names = {"Team A", "Team B", "Team C", "Team D","Team E"};
        String[] param = {"Period 1", "Period 2","Period 3"};
        int[][] values = {{20, 10, 40}, {25, 50, 60}, {75, 30,90}, {40, 60, 20  }, {75,40,90}};
        BufferedImage bufferedImage;
        GraphPrinter gp = new GraphPrinter(configPath, destinationPath);
        bufferedImage = gp.horizColumnGraph(graphName, names, param, values);
        gp.saveToImage(bufferedImage, "Horizontal Column Graph", "png");
        bufferedImage = gp.pointGraph(graphName, names, param, values);
        gp.saveToImage(bufferedImage, "Linear Graph", "jpg");
        bufferedImage = gp.vertColumnGraph(graphName, names, param, values);
        gp.saveToImage(bufferedImage, "Vertical Column Graph", "bmp");


    }
}
