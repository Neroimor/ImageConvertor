package com.neroimor;

import com.neroimor.Let1.ConvertorImageFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new ConvertorImageFormat().convertorFormat("src/main/resources/art.jpg",
                "src/main/resources/output/");

        new ConvertorImageFormat().rgbToGray("src/main/resources/art.jpg",
                "src/main/resources/output/");

        new ConvertorImageFormat().outputToScreen("src/main/resources/art.jpg");
    }
}