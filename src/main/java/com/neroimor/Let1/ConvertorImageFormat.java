package com.neroimor.Let1;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;


public class ConvertorImageFormat {
    public void convertorFormat(String pathToImage, String pathToOutput) {
        String inputImagePath = pathToImage;
        String outputDir = pathToOutput;

        new File(outputDir).mkdirs();

        String[] formats = {"png", "bmp", "jpeg", "gif"};
        try {
            BufferedImage image = ImageIO.read(new File(inputImagePath));
            String nameImage = new File(inputImagePath).getName();
            nameImage = nameImage.substring(0, nameImage.lastIndexOf("."));
            for (var format : formats) {
                String outputFileName = outputDir + nameImage + "." + format;
                File outputFile = new File(outputFileName);
                boolean result = ImageIO.write(image, format, outputFile);
                if (result) {
                    System.out.println("Изображение сохранено");
                } else {
                    System.out.println("Изображение не сохранено");
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }
    }

    public void rgbToGray(String pathToImage, String pathToOutput) {

        String inputImagePath = pathToImage;
        String outputDir = pathToOutput;
        new File(outputDir).mkdirs();

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        try {
            String nameImage = new File(inputImagePath).getName();
            BufferedImage bufferedImage = ImageIO.read(new File(inputImagePath));
            BufferedImage image = op.filter(bufferedImage, null);

            String outputFileName = outputDir + "Gray" + nameImage;
            File outputFile = new File(outputFileName);
            String format = "jpg";
            boolean result = ImageIO.write(image, format, outputFile);
            if (result) {
                System.out.println("Изображение сохранено");
            } else {
                System.out.println("Изображение не сохранено");
            }
        } catch (IOException e) {
            System.out.println("Ошибка преобразования в серый");
        }

    }

    public void outputToScreen(String pathToImage) {
        try {
            BufferedImage img = ImageIO.read(new File(pathToImage));
            ImageIcon icon = new ImageIcon(img);

            JFrame frame = new JFrame("Просмотр изображения");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel lbl = new JLabel(icon, JLabel.CENTER);
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            frame.setLayout(new BorderLayout());
            frame.add(lbl, BorderLayout.CENTER);

            frame.setSize(icon.getIconWidth(), icon.getIconHeight());
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        } catch (IOException e) {
            System.out.println("Ошибка вывода изображения на экран: " + e.getMessage());
        }
    }


    public void blurringOnTheImage(String imagePath, String outputPath) {
        try {
            // Загрузка изображения
            String nameImage = new File(imagePath).getName();
            BufferedImage image = ImageIO.read(new File(imagePath));
            // Создание матрицы фильтра усреднения
            float[] matrix = new float[15 * 15];
            for (int i = 0; i < matrix.length; i++) {
                matrix[i] = 1f / (15 * 15); // 15x15 фильтр усреднения
            }
            // Создание фильтра с использованием Kernel
            Kernel kernel = new Kernel(15, 15, matrix);  // 15x15 фильтр
            ConvolveOp convolveOp = new ConvolveOp(kernel);
            // Применение фильтра на изображение
            BufferedImage blurredImage = convolveOp.filter(image, null);
            String outputPathStr = outputPath + "Blur" + nameImage;
            // Сохранение обработанного изображения
            File outputFile = new File(outputPathStr);
            String format = "jpg";
            boolean result =  ImageIO.write(blurredImage, format,outputFile);
            if (result) {
                System.out.println("Изображение сохранено");
            } else {
                System.out.println("Изображение не сохранено");
            }
        } catch (IOException e) {
            System.out.println("Ошибка преобразования в серый");
        }
    }


}
