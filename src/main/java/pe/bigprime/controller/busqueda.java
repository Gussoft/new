package pe.bigprime.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class busqueda extends PDFTextStripper{

    List<String> parsedtext;

    public busqueda() throws IOException {
    }

    public List<String> parsedPdfDocument(String file){
        parsedtext = new ArrayList<>();
        PDDocument pdDocument = null;
        try {
            pdDocument = PDDocument.load(new File(file));
            this.setSortByPosition(true);
            this.setStartPage(1);
            this.setEndPage(pdDocument.getNumberOfPages());

            Writer dummyWriter = new OutputStreamWriter(new ByteArrayOutputStream());
            this.writeText(pdDocument,dummyWriter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pdDocument != null){
                try {
                    pdDocument.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return  parsedtext;
    }

    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        parsedtext.add(string);
        for (TextPosition text: textPositions){
            if (text.getUnicode().equals(" ")){
                System.out.println("vacio");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        busqueda bus = new busqueda();
        List<String> strings = bus.parsedPdfDocument("C:\\Users\\LENOVO\\Desktop\\Documento.pdf");
    }

}
