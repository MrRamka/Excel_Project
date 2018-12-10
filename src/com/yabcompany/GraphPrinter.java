package com.yabcompany;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphPrinter {
    private static int width;
    private static int height;
    private static String bgColor;
    private static String textColor;
    private static String[] colors;
    private static int[] BG_COLOR;
    private static int[] TEXT_COLOR;
    private static int fontSize = 80;
    private static int margin_left;
    private static int margin_top;
    private static int margin_right;
    private static int margin_bottom;


    protected static void initProperties() {
        PropertiesScanner.setProperties();
        width = PropertiesScanner.getWidth();
        height = PropertiesScanner.getHeight();
        bgColor = PropertiesScanner.getBgColor();
        textColor = PropertiesScanner.getTextColor();
        colors = PropertiesScanner.getColors();
        BG_COLOR = getColor(bgColor);
        TEXT_COLOR = getColor(textColor);
        margin_left = (int) (width * 0.15);
        margin_top = (int) (height * 0.2);
        margin_right = (int) (width * 0.8);
        margin_bottom = (int) (height * 0.8);
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
        g2d.drawString(graphName, margin_left, margin_top); // Graph

        //Lines
        int mx = maxValue(values);
        drawLines(g2d, mx);

        //Names
        printNames(g2d, names);

        //
        System.out.println(mx);
        int[] newColor = getColor(colors[1]);
        double step = 1.0 * (margin_right - margin_left) / names.length;
        g2d.setColor(new Color(newColor[0], newColor[1], newColor[2]));
        g2d.setStroke(new BasicStroke(8));

        for (int j = 0; j < params.length; j++) {
            newColor = getColor(colors[j]);
            g2d.setColor(new Color(newColor[0], newColor[1], newColor[2]));
            for (int i = 0; i < names.length - 1; i++) {
                g2d.drawLine((int) (margin_left + i * step + step / 2 + step/8), margin_bottom - (int) (values[i][j] * 0.5 * height / mx),
                        (int) (margin_left + (i + 1) * step + step / 2 + step/8), margin_bottom - (int) (values[i + 1][j] * 0.5 * height / mx));


            }
            g2d.fillRect((int)(margin_right + margin_right * 0.03), (int)(height * 0.3 + j * height * 0.05), 50,25);
            g2d.setColor(new Color(TEXT_COLOR[0], TEXT_COLOR[1], TEXT_COLOR[2]));
            g2d.drawString(params[j],(int)(margin_right + margin_right * 0.07), (int)(height * 0.3 + j * height * 0.05) + 20);

        }


        g2d.dispose();


        saveToPng(bufferedImage);
    }

    private static int[] getColor(String color) {
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
        int mx = values[0][0];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (mx < values[i][j]) {
                    mx = values[i][j];
                }
            }
        }
        return mx;
    }

    private static void drawLines(Graphics2D g2d, int mx) {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(margin_left, margin_bottom, margin_right, margin_bottom); // X
        //g2d.drawLine((int) (width * 0.15), (int) (height * 0.3), (int) (width * 0.15), (int) (height * 0.8)); // Y

        //Vertical lines
        g2d.setStroke(new BasicStroke(1));
        double step = height * 0.5 / 4;
        String s = "";
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize / 3);
        g2d.setFont(font);
        for (int i = 0; i < 4; i++) {
            g2d.drawLine(margin_left, (int) (height * 0.3 + i * step), margin_right, (int) (height * 0.3 + i * step));
            s += (int) (mx * 0.25 * (4 - i));
            g2d.drawString(s, (int)(margin_left * 0.9), (int) (height * 0.3 + i * step));
            s = "";
        }

    }

    private static void printNames(Graphics2D g2d, String[] names) {
        double step = 1.0 * (margin_right - margin_left) / names.length;
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize / 4);
        g2d.setFont(font);
        for (int i = 0; i < names.length; i++) {
            g2d.drawString(names[i], (int) (margin_left + i * step + step / 2), (int)(margin_bottom + margin_bottom * 0.07));

        }
    }

    private static void saveToPng(BufferedImage bufferedImage) {
        File file = new File(System.getProperty("user.home"), "Desktop\\image.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
