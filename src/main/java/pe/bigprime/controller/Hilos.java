package pe.bigprime.controller;

public class Hilos implements Runnable{
    @Override
    public void run() {
        doc sa = rango();
        System.out.println("rango :: " +sa.getPageAndCoords());
    }
    public doc rango(){
        int np = 99;

        int cociente = np / 2;
        int res = np % cociente;
        int round = Math.round(cociente);
        doc docs= null;

        for (int i = 1; i <= np; i = i + round) {
            int resta = 0;
            boolean ab = false;
            if ((i + res) == (np+1)) {
                //System.out.println("Residuo= " + i + "::" + (i + res -1));
                resta = 1;
                ab = true;
                System.out.println("final");
                //break;
            }
            System.out.println("rango de paginas " + i);
            if (!ab) {
                System.out.println("A,B = " + (i) + "::" + (i + round - 1 - resta));

            }else{
                System.out.println("A,B R= " +(i)+"::"+(np));
            }
            docs = new doc();
            docs.setSortByPosition(true);
            docs.setStartPage(i);
            docs.setEndPage(i+round-1);
            docs.setPageAndCoords("coords");
        }
        return docs;
    }
}

class go{
    public static void main(String[] args) {
        Thread hilo = new Thread(new Hilos());
        hilo.start();
    }
}

class doc{

    private boolean SortByPosition;
    private int StartPage;
    private int EndPage;
    private String PageAndCoords;

    public doc(){

    }
    public boolean isSortByPosition() {
        return SortByPosition;
    }

    public void setSortByPosition(boolean sortByPosition) {
        SortByPosition = sortByPosition;
    }

    public int getStartPage() {
        return StartPage;
    }

    public void setStartPage(int startPage) {
        StartPage = startPage;
    }

    public int getEndPage() {
        return EndPage;
    }

    public void setEndPage(int endPage) {
        EndPage = endPage;
    }

    public String getPageAndCoords() {
        return PageAndCoords;
    }

    public void setPageAndCoords(String pageAndCoords) {
        PageAndCoords = pageAndCoords;
    }
}