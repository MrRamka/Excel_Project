package com.yabcompany.GraphPrinter;
//ToDO: JavaDoc
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class PropertiesScanner {
    private FileInputStream fis;
    private Properties property = new Properties();
    private Scanner scanner = null;
    private int width;
    private int height;
    private String bgColor;
    private String textColor;
    private String[] colors;
    private String configPath;

    public PropertiesScanner(String configPath) {
        this.configPath = configPath;
    }


    public void setProperties() throws IOException {
        fis = new FileInputStream(configPath);
        int ans = checkPropertiesLength();
        property.load(fis);
        width = Integer.parseInt(property.getProperty("screen.width"));
        height = Integer.parseInt(property.getProperty("screen.height"));
        bgColor = property.getProperty("color.bg");
        textColor = property.getProperty("color.text");
        colors = new String[ans - 4];
        for (int i = 0; i < colors.length; i++) {
            String currentColor = "color." + (i + 1);
            colors[i] = property.getProperty(currentColor);
        }

    }

    private int checkPropertiesLength() throws FileNotFoundException {
        int ans = 0;
        scanner = new Scanner(new File(configPath));
        while (scanner.hasNextLine()) {
            ans++;
            String line = scanner.nextLine();
        }
        return ans;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBgColor() {
        return bgColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String[] getColors() {
        return colors;
    }
}
