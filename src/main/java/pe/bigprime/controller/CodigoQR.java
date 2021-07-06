package pe.bigprime.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CodigoQR {
    public static void main(String[] args) {
        CodigoQR qrCode = new CodigoQR();
        File file = new File("C:\\Users\\LENOVO\\Desktop\\qr.png");
        String text = "https://monoschinos2.com";

        try {
            qrCode.generarQR(file,text,300,300);
            System.out.println("Generated QR = " + file.getAbsolutePath());
            System.out.println("Decodificando el QR Generado");
            String qr = qrCode.decoder(file);
            System.out.println("Text QRCode = " + qr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  File generarQR(File file, String text, int h, int w) throws WriterException, IOException {

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE,w,h);

        BufferedImage image = new BufferedImage(matrix.getWidth(),matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,matrix.getWidth(),matrix.getHeight());
        graphics2D.setColor(Color.green);

        for (int i = 0; i < matrix.getWidth(); i++){
            for (int j = 0; j < matrix.getHeight(); j++){
                if (matrix.get(i,j)){
                    graphics2D.fillRect(i,j,1,1);
                }
            }
        }
        ImageIO.write(image,"png",file);

        return file;
    }

    public String decoder(File file) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        BufferedImage image = ImageIO.read(inputStream);

        int ancho = image.getWidth();
        int alto = image.getHeight();
        int[] pixels = new int[ancho * alto];

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);

        return new String(result.getText());
    }
}
