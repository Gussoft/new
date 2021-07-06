package pe.bigprime.controller;

import com.qoppa.pdfText.PDFText;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class welcome {

    public static void main(String[] args) {
        try{
            PDFText pdfText = new PDFText("C:\\Users\\LENOVO\\Desktop\\demo4.pdf", null);
            int match = 0;//Anexo03_PostulantesNoApto
            int pageCount = pdfText.getPageCount();
            for (int i = 0; i< pageCount; i++){
                Vector words = pdfText.getWords(i);
                System.out.println("Pagina : " + (i + 1));
                for (int wC = 0;wC < words.size(); wC++){
                    if (words.get(wC).equals("[FIRMA]")) {
                        match++;
                        System.out.println(words.get(wC));
                    }
                }
                /*String text = pdfText.getText(i);
                System.out.println("Pagina : "+ (i+1));
                System.out.println("text = " + text);*/
            }
            //buscarTexto("C:\\Users\\LENOVO\\Desktop\\demo4.pdf");
            System.out.println("Numero de paginas = " + pageCount + " Match : " + match);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void buscarTexto(String archivo) {
        Scanner sn = new Scanner(System.in);
        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            System.out.println("Introduce un texto para buscar");
            String buscar = sn.nextLine();
            String linea = br.readLine();

            int contLineas = 0;
            int contPalabras = 0;
            while (linea != null) {
                StringTokenizer st = new StringTokenizer(linea, " ,.");
                contLineas++;
                while (st.hasMoreTokens()) {
                    if (st.nextToken().equalsIgnoreCase(buscar)) {
                        contPalabras++;
                        System.out.println(buscar + " aparece en la linea "
                                + contLineas);
                    }
                }
                linea = br.readLine();
            }
            if (contPalabras > 0) {
                System.out.println("La palabra " + buscar + " aparece: "
                        + contPalabras + " veces");
            } else {
                System.out.println("No se ha encontrado esa palabra");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura " + e.getMessage());
        }
    }
}
