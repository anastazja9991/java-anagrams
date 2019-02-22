package anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Anagramy {
    public static void main(String[] args) throws Exception {

        Anagramy a = new Anagramy();
        List<String> listaWczytanych = new ArrayList<>();
        List<String> listaAnagramow = new ArrayList<>();
        listaWczytanych = a.wczytajPlik();

        if(listaWczytanych.size() == 0){
            System.out.println("Nie wczytano żadnych słów!");
        } else{
            System.out.println(String.format("Wczytano %,d unikalnych słów.", listaWczytanych.size()));
            listaAnagramow = a.znajdzAnagramy(listaWczytanych);
            System.out.println(String.format("Znaleziono %,d anagramów.", listaAnagramow.size()));

            for(String s : listaAnagramow){
                System.out.println(s);
            }
        }
    }

    public List<String> wczytajPlik() throws Exception {
        List<String> listaSlow = new ArrayList<>();
        Set<String> slowaSet = new HashSet<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("anagram.txt"));
        try{
            String odczytanaLinia;
            while((odczytanaLinia = bufferedReader.readLine())!= null){
                if(!odczytanaLinia.equals("")){
                    slowaSet.add(odczytanaLinia);
                }
            }
            bufferedReader.close();
            listaSlow.addAll(slowaSet);
        } catch(Exception e){
            System.out.println("Błąd odczytu pliku!");
        }
        return listaSlow;
    }

    public List<String> znajdzAnagramy(List<String> listaSlow){
        Date dStart = new Date();

        String s = "Wyszukiwanie anagramów";
        long iloscPorownan = 0;
        List<String> lista = new ArrayList<>();
        System.out.print(s);

        for(int x = 0; x < listaSlow.size(); x++){
            for(int y = x + 1; y < listaSlow.size(); y++){
                String str1 = listaSlow.get(x).toString();
                String str2 = listaSlow.get(y).toString();

                iloscPorownan++;

                if(porownajSlowa(str1, str2)){
                    if((x % 20) == 0){
                        System.out.print(".");
                    }
                    lista.add(str1 + " - " + str2);
                }
            }
        }
        System.out.println(String.format("\n\nLiczba wykonanych porównań %,d.", iloscPorownan));
        Date dKoniec = new Date();
        long czas = (dKoniec.getTime() - dStart.getTime()) / 1000;
        System.out.println(String.format("Porównanie trwało %,d sekund.", czas));
        return lista;
    }

    public boolean porownajSlowa(String slowo1, String slowo2){
        String pSlowo1 = slowo1.toLowerCase();
        String pSlowo2 = slowo2.toLowerCase();

        char[] c1 = pSlowo1.toCharArray();
        char[] c2 = pSlowo2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        String sortuj1 = new String(c1);
        String sortuj2 = new String(c2);

        return sortuj1.equals(sortuj2);
    }
}
