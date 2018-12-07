package com.yabcompany;


import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphPrinter {
    private static int width;
    private static int height;
    private static String bgColor;
    private static String textColor;
    private static String[] colors;

    public static String getBgColor() {
        return bgColor;
    }

    private static void initProperties(){
        PropertiesScanner.setProperties();
        width = PropertiesScanner.getWidth();
        height = PropertiesScanner.getHeight();
        bgColor = PropertiesScanner.getBgColor();
        textColor = PropertiesScanner.getTextColor();
        colors = PropertiesScanner.getColors();
    }

    public static void linearGraph() {

    }

    public static void columnGraph() {

    }

    public static void pointGraph(String graphName, String[] names, String[] params, int[][] values) {
        initProperties();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        //ToDo: Check color and colors int array
        g2d.fillRect(0, 0, width, height);


    }

    private static void getColor(String color){
        int r = 0, g = 0, b = 0;
        String cl = "";



    }


}
