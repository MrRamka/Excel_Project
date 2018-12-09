package com.yabcompany.Graphs;

import com.yabcompany.GraphPrinter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PointGraph extends GraphPrinter {
    //String graphName, String[] names, String[] params, int[][] values


    public static void pointGraph() {
        initProperties();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        int[] bg = getColor(textColor);
        g2d.setColor(new Color(bg[0], bg[1], bg[2]));
        //ToDo: Check color and colors int array
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        File file = new File("C:\\Users\\ramil\\Desktop\\image.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
