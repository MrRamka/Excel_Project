package com.yabcompany;
//Памагити! Рамиль опять какую-то непонятную хуйню написал :(

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GraphPrinter {
    protected static int width;
    protected static int height;
    protected static String bgColor;
    protected static String textColor;
    protected static String[] colors;
    protected static int[] BG_COLOR;
    protected static int[] TEXT_COLOR;
    protected static int fontSize = 80;

    public static String getBgColor() {
        return bgColor;
    }

    protected static void initProperties() {
        PropertiesScanner.setProperties();
        width = PropertiesScanner.getWidth();
        height = PropertiesScanner.getHeight();
        bgColor = PropertiesScanner.getBgColor();
        textColor = PropertiesScanner.getTextColor();
        colors = PropertiesScanner.getColors();
        BG_COLOR = getColor(bgColor);
        TEXT_COLOR = getColor(textColor);
    }

    public static void linearGraph() {

    }

    public static void columnGraph() {

    }

    public static void pointGraph(String graphName, String[] names, String[] params, int[][] values) {
        initProperties();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize);
        g2d.setFont(font);
        g2d.setColor(new Color(BG_COLOR[0], BG_COLOR[1], BG_COLOR[2]));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(TEXT_COLOR[0], TEXT_COLOR[1], TEXT_COLOR[2]));
        g2d.drawString(graphName, (int) (width * 0.15), (int) (height * 0.2)); // Graph Name
        g2d.drawLine((int) (width * 0.15), (int) (height * 0.8), (int) (width * 0.85), (int) (height * 0.8)); // X
        g2d.drawLine((int) (width * 0.15), (int) (height * 0.3), (int) (width * 0.15), (int) (height * 0.8)); // Y


        g2d.dispose();


        File file = new File(System.getProperty("user.home"), "Desktop\\image.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static int[] getColor(String color) {
        int[] currentColor = new int[3];
        try {
            currentColor[0] = Integer.valueOf(color.substring(1, 3), 16);
            currentColor[1] = Integer.valueOf(color.substring(3, 5), 16);
            currentColor[2] = Integer.valueOf(color.substring(5, 7), 16);
        } catch (NumberFormatException e) {
            System.out.println("Enter correct color");
        }
        return currentColor;
    }

    private static int maxValue(int[][] values) {
        int[][] arr = Arrays.copyOf(values, values.length);
        Arrays.sort(arr);
        int mx = arr[arr.length - 1][arr.length - 1];
        return mx;
    }

    private static void roundMaxValue(){



    }


}
