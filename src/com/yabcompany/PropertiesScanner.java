package com.yabcompany;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class PropertiesScanner {
    private static FileInputStream fis;
    private static Properties property = new Properties();
    private static Scanner scanner = null;
    private static int width;
    private static int height;
    private static String bgColor;
    private static String textColor;
    private static String[] colors;


    public static void setProperties() {
        try {
            fis = new FileInputStream("config.properties");
            int ans = checkProperties();
            property.load(fis);
            width = Integer.parseInt(property.getProperty("screen.width"));
            height = Integer.parseInt(property.getProperty("screen.height"));
            bgColor = property.getProperty("color.bd");
            textColor = property.getProperty("color.text");
            colors = new String[ans-4];
            for (int i = 0; i < colors.length; i++){
                String currentColor = "color." + (i+1);
                colors[i] = property.getProperty(currentColor);
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter correct screen size");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkProperties() {
        int ans = 0;
        try {
            scanner = new Scanner(new File("config.properties"));

            while (scanner.hasNextLine()) {
                ans++;
                String line = scanner.nextLine();
                System.out.println(ans + " " + line);
            }
            return ans;
        } catch (FileNotFoundException e) {
            System.out.println("Cant find properties file");
        }
        return 4;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static String getBgColor() {
        return bgColor;
    }

    public static String getTextColor() {
        return textColor;
    }

    public static String[] getColors() {
        return colors;
    }
}
