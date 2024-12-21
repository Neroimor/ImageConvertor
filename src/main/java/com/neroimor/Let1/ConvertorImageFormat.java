package com.neroimor.Let1;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

public class ConvertorImageFormat {
    public void convertorFormat(String pathToImage, String pathToOutput){
        String inputImagePath = pathToImage;
        String outputDir=pathToOutput;

        new File(outputDir).mkdirs();

        String[] formats = {"png", "bmp", "jpeg", "gif"};
        try {
            BufferedImage image = ImageIO.read(new File(inputImagePath));
            String nameImage = new File(inputImagePath).getName();
            nameImage = nameImage.substring(0, nameImage.lastIndexOf("."));
            for(var format : formats) {
                String outputFileName = outputDir + nameImage + "." + format;
                File outputFile = new File(outputFileName);
                boolean result = ImageIO.write(image, format, outputFile);
                if(result) {
                    System.out.println("Изображение сохранено");
                } else {
                    System.out.println("Изображение не сохранено");
                }
            }
        } catch (IOException e){
            System.out.println("Произошла ошибка");
        }
    }


    public void rgbToGray(String pathToImage, String pathToOutput){

        String inputImagePath = pathToImage;
        String outputDir=pathToOutput;
        new File(outputDir).mkdirs();

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        try{
            String nameImage = new File(inputImagePath).getName();
            BufferedImage bufferedImage = ImageIO.read(new File(inputImagePath));
            BufferedImage image = op.filter(bufferedImage, null);

            String outputFileName = outputDir +"Gray"+nameImage;
            File outputFile = new File(outputFileName);
            String format ="jpg";
            boolean result = ImageIO.write(image, format, outputFile);
            if(result) {
                System.out.println("Изображение сохранено");
            } else {
                System.out.println("Изображение не сохранено");
            }
        } catch (IOException e){
            System.out.println("Ошибка преобразования в серый");
        }

    }
}
