package com.yabcompany.GraphPrinter;
//ToDO: JavaDoc
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Ramil Minyukov ramil.minyukov@yandex.ru
 * @author Rasim Khasanov rasim.2@bk.ru
 * @author Karim Sharafutdinov shkar2001@mail.ru
 * @version 15.12.2018.2
 * @since 8
 */

public class GraphPrinter {
    private PropertiesScanner propertiesScanner;
    private int width;
    private int height;
    private String bgColor;
    private String textColor;
    private String[] colors;
    private int[] BG_COLOR;
    private int[] TEXT_COLOR;
    private int fontSize = 80;
    private int margin_left;
    private int margin_top;
    private int margin_right;
    private int margin_bottom;
    private String configPath;
    private String destinationPath;

    public GraphPrinter(String configPath, String destinationPath) throws IOException {
        this.configPath = configPath;
        this.destinationPath = destinationPath;
        propertiesScanner = new PropertiesScanner(configPath);
        propertiesScanner.setProperties();
        width = propertiesScanner.getWidth();
        height = propertiesScanner.getHeight();
        bgColor = propertiesScanner.getBgColor();
        textColor = propertiesScanner.getTextColor();
        colors = propertiesScanner.getColors();
        BG_COLOR = getColor(bgColor);
        TEXT_COLOR = getColor(textColor);
        margin_left = (int) (width * 0.15);
        margin_top = (int) (height * 0.2);
        margin_right = (int) (width * 0.8);
        margin_bottom = (int) (height * 0.8);

    }

    /**
     * @param graphName name of graphic
     * @param names     names of columns
     * @param params    names of parameters
     * @param values    array of input values
     * @return BufferedImage
     * @throws IllegalArgumentException if array values[][] has a negative numbers
     */

    public BufferedImage horizColumnGraph(String graphName, String[] names, String[] params, int[][] values) {

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Moishrift", Font.PLAIN, fontSize);
        int mx = maxValue(values, names.length, params.length);

        printMainPart(g2d, font, graphName);

        drawLinesLinear(g2d, mx);

        printNamesLinear(g2d, names);

        int[] newColor;

        double step = 1.0 * (margin_bottom - 0.3 * height) / names.length;
        String[] newColors = createRandomColors(colors);
        g2d.setColor(new Color(TEXT_COLOR[0], TEXT_COLOR[1], TEXT_COLOR[2]));
        for (int i = 0; i < params.length; i++) {
            newColor = getColor(newColors[i]);
            g2d.setColor(new Color(newColor[0], newColor[1], newColor[2]));
            for (int j = 0; j < names.length; j++) {
                g2d.fillRect(margin_left + (int) (margin_left * 0.02),
                        (int) (height * 0.3 + step * 0.5 / (params.length + 1) + step * (j) + (i) * step / (params.length + 1)),
                        (values[j][i] * (margin_right - margin_left) / mx),
                        (int) (step / (params.length + 1))
                );
            }
            printParameters(g2d, params[i], i);
        }
        g2d.dispose();
        return bufferedImage;
    }

    /**
     * @param graphName name of graphic
     * @param names     names of columns
     * @param params    names of parameters
     * @param values    array of input values
     * @return BufferedImage
     * @throws IllegalArgumentException if values[][] has a negative numbers
     */

    public BufferedImage vertColumnGraph(String graphName, String[] names, String[] params, int[][] values) {
        checkNegativeNumbers(values, names.length, params.length);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize);

        printMainPart(g2d, font, graphName);

        int mx = maxValue(values, names.length, params.length);
        int[] newColor;
        //Lines
        drawLines(g2d, mx);

        //Names
        printNames(g2d, names);

        double step = 1.0 * (margin_right - margin_left) / names.length;
        String[] newColors = createRandomColors(colors);

        for (int i = 0; i < params.length; i++) {
            newColor = getColor(newColors[i]);
            g2d.setColor(new Color(newColor[0], newColor[1], newColor[2]));
            for (int j = 0; j < names.length; j++) {
                g2d.fillRect((int) (step * (j + 1) + step / (params.length + 1) + (i) * step / (params.length + 1)),
                        margin_bottom - (int) (margin_bottom * 0.01) - (int) (values[j][i] * 0.5 * height / mx),
                        (int) (step / (params.length + 1)),
                        (int) (values[j][i] * 0.5 * height / mx));
            }
            printParameters(g2d, params[i], i);
        }

        g2d.dispose();
        return bufferedImage;
    }

    /**
     * @param graphName name of graphic
     * @param names     names of columns
     * @param params    names of parameters
     * @param values    array of input values
     * @return BufferedImage
     * @throws IllegalArgumentException if values[][] has a negative numbers
     */

    public BufferedImage pointGraph(String graphName, String[] names, String[] params, int[][] values) {
        checkNegativeNumbers(values, names.length, params.length);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize);

        printMainPart(g2d, font, graphName);

        int mx = maxValue(values, names.length, params.length);
        int[] newColor;

        //Lines
        drawLines(g2d, mx);

        //Names
        printNames(g2d, names);


        double step = 1.0 * (margin_right - margin_left) / names.length;
        g2d.setStroke(new BasicStroke(8));
        String[] newColors = createRandomColors(colors);

        for (int j = 0; j < params.length; j++) {
            newColor = getColor(newColors[j]);
            g2d.setColor(new Color(newColor[0], newColor[1], newColor[2]));
            for (int i = 0; i < names.length - 1; i++) {
                g2d.drawLine((int) (margin_left + i * step + step / 3 + step / 8), margin_bottom - (int) (values[i][j] * 0.5 * height / mx),
                        (int) (margin_left + (i + 1) * step + step / 3 + step / 8), margin_bottom - (int) (values[i + 1][j] * 0.5 * height / mx));
            }
            printParameters(g2d, params[j], j);
        }

        g2d.dispose();
        return bufferedImage;
    }

    /**
     * @param bufferedImage is Image which is Buffered
     * @param fileName      is name of file
     * @param format        is format of file (JPG, PNG, BMP etc.)
     * @throws IOException if file is null
     */
    public void saveToImage(BufferedImage bufferedImage, String fileName, String format) throws IOException {
        File file = new File(this.destinationPath + "\\" + fileName + "." + format);
        ImageIO.write(bufferedImage, format, file);
        System.out.println("Saved to " + this.destinationPath + "\\" + fileName + "." + format);
    }

    private void printMainPart(Graphics2D g2d, Font font, String graphName) {
        g2d.setFont(font);
        g2d.setColor(new Color(BG_COLOR[0], BG_COLOR[1], BG_COLOR[2]));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(TEXT_COLOR[0], TEXT_COLOR[1], TEXT_COLOR[2]));
        g2d.drawString(graphName, margin_left, margin_top); // Graph

    }

    private void printParameters(Graphics2D g2d, String parameter, int j) {
        g2d.fillRect((int) (margin_right + margin_right * 0.03), (int) (height * 0.3 + j * height * 0.05), 50, 25);
        g2d.setColor(new Color(TEXT_COLOR[0], TEXT_COLOR[1], TEXT_COLOR[2]));
        g2d.drawString(parameter, (int) (margin_right + margin_right * 0.07), (int) (height * 0.3 + j * height * 0.05) + 20);

    }

    private int[] getColor(String color) {
        int[] currentColor = new int[3];
        try {
            currentColor[0] = Integer.valueOf(color.substring(1, 3), 16);
            currentColor[1] = Integer.valueOf(color.substring(3, 5), 16);
            currentColor[2] = Integer.valueOf(color.substring(5, 7), 16);
        } catch (NumberFormatException e) {
            //System.out.println("Enter correct color");
        }
        return currentColor;
    }

    private int maxValue(int[][] values, int namesLen, int paramLen) {
        int mx = values[0][0];
        for (int i = 0; i < namesLen; i++) {
            for (int j = 0; j < paramLen; j++) {
                if (mx < values[i][j]) {
                    mx = values[i][j];
                }
            }
        }
        return mx;
    }

    private void drawLines(Graphics2D g2d, int mx) {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(margin_left, margin_bottom, margin_right, margin_bottom); // X
        g2d.setStroke(new BasicStroke(1));
        double step = height * 0.5 / 4;
        String s = "";
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize / 3);
        g2d.setFont(font);
        for (int i = 0; i < 4; i++) {
            g2d.drawLine(margin_left, (int) (height * 0.3 + i * step), margin_right, (int) (height * 0.3 + i * step));
            s += (int) (mx * 0.25 * (4 - i));
            g2d.drawString(s, (int) (margin_left * 0.8), (int) (height * 0.3 + i * step));
            s = "";
        }

    }

    private void printNames(Graphics2D g2d, String[] names) {
        double step = 1.0 * (margin_right - margin_left) / names.length;
        Font font = new Font("Bahnshrift", Font.PLAIN, fontSize / 4);
        g2d.setFont(font);
        for (int i = 0; i < names.length; i++) {
            g2d.drawString(names[i], (int) (margin_left + i * step + step / 3), (int) (margin_bottom + margin_bottom * 0.07));

        }
    }

    private String[] createRandomColors(String[] colors) {
        String[] newColorArr = new String[colors.length];
        String[] copyColors = Arrays.copyOf(colors, colors.length);
        int pos;
        for (int i = 0; i < colors.length - 2; i++) {
            pos = (int) (Math.random() * (colors.length - (i + 1)));
            newColorArr[i] = copyColors[pos];
            copyColors[pos] = copyColors[colors.length - 1];
        }
        return newColorArr;
    }

    private void checkNegativeNumbers(int[][] values, int namesLen, int paramLen) {
        for (int i = 0; i < namesLen; i++) {
            for (int j = 0; j < paramLen; j++) {
                if (values[i][j] < 0) {
                    throw new IllegalArgumentException("Values can't be negative(<0)");
                }
            }
        }
    }

    private void drawLinesLinear(Graphics2D g2d, int mx) {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(margin_left, (int) (height * 0.3), margin_left, margin_bottom); // Y

        double step = (margin_right - margin_left) / 5.0;
        g2d.setStroke(new BasicStroke(1));
        String s = "";
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize / 3);
        g2d.setFont(font);
        for (int i = 0; i < 5; i++) {
            g2d.drawLine((int) (margin_left + step * (i + 1)), (int) (height * 0.3), (int) (margin_left + step * (i + 1)), margin_bottom);
            s += (int) (mx * 0.2 * (i + 1));
            g2d.drawString(s, (int) (margin_left + step * (i + 1) - margin_left * 0.01), (int) (margin_bottom + margin_bottom * 0.05));
            s = "";
        }

    }

    private void printNamesLinear(Graphics2D g2d, String[] names) {
        double step = 1.0 * (margin_bottom - 0.3 * height) / names.length;
        Font font = new Font("Bahnschrift", Font.PLAIN, fontSize / 4);
        g2d.setFont(font);
        for (int i = 0; i < names.length; i++) {
            g2d.drawString(names[i], (int) (margin_left * 0.6), (int) (height * 0.3 + step * (i) + step / 3)); //?? возможно выйдет за рамки (+ в х поменял на -)
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
