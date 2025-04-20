package com.example.backend.effectutils;

import com.example.backend.effectmodels.SmallImage;
import lombok.SneakyThrows;
import lombok.var;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Utils {

    @SneakyThrows
    public static byte[] cropImg(String img, SmallImage smallImage, int zoomLevel) {
        var bais = new ByteArrayInputStream(Base64.getDecoder().decode(img.split("\\,")[1]));
        try {
            BufferedImage buff = ImageIO.read(bais);
            Graphics2D g = buff.createGraphics();
            buff = buff.getSubimage(smallImage.x * zoomLevel, smallImage.y * zoomLevel, smallImage.w * zoomLevel, smallImage.h * zoomLevel);
            g.dispose();
            var baos = new java.io.ByteArrayOutputStream();
            try {
                ImageIO.write(buff, "png", baos);
                return baos.toByteArray();
            } finally {
                baos.close();
            }
        } finally {
            bais.close();
        }

    }

}
