package pe.bigprime.controller;

//import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.util.Splitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfEdits {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        String url = "C:\\Users\\LENOVO\\Desktop\\demo4.pdf";
        List<String> listaPdf = dividirPdf(url);
        for (String lis: listaPdf){
            System.out.println("documento dividido " + lis.toString());
        }

        System.out.println("finalizo en la ruta : ");

    }
    public static List<String> dividirPdf(String file) {
        List<String> parts = new ArrayList<>();
        /*try{
            PDDocument doc = PDDocument.load(new File(file));

            Splitter splitter = new Splitter();
            List<PDDocument> docs = splitter.split(doc);
            Iterator<PDDocument> iterator = docs.listIterator();
            File ruta = createTempDirectory();
            String full = ruta.getAbsolutePath() + "\\";
            int i = 1;
            while(iterator.hasNext()) {
                PDDocument pd = iterator.next();
                pd.save(full+ i +".pdf");
                parts.add(full+ i +".pdf");
                i++;
            }
        }catch (IOException | COSVisitorException e){
            e.printStackTrace();
        }*/
        return parts;
    }

    public static File createTempDirectory(){
        File temp = null;
        try{
            temp = File.createTempFile("part-", Long.toString(System.nanoTime()));
            if(!(temp.delete())){
                throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
            }
            if(!(temp.mkdir())){
                throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return (temp);
    }
}
