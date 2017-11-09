package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Ratkaisin {

    private String sana;
    private TreeSet<String> suomenkielenSanat;

    public Ratkaisin() {
        this.sana = "";
        this.suomenkielenSanat = new TreeSet<>();
        try {
            lisaaSanat();
        } catch (Exception e) {
            System.out.println("VIRHE");
        }
    }

    public void lisaaSanat() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\s1700306\\Documents\\NetBeansProjects\\SanaPeliRatkaisin\\src\\peli\\sanat.txt")), "UTF8"));

        reader.lines().forEach(e -> {
            String[] list = e.split("<");
            String lisattava = list[0];
            if (lisattava.length() > 1) {
                lisattava.replaceAll("-", "");
                lisattava.trim();
                suomenkielenSanat.add(lisattava);
            }

        });

        reader.close();

    }

    public Set<String> ratkaiseSana(String sana, int times) {
        this.sana = sana;
        Set<String> palautettava = new HashSet<>();
        Random r = new Random();
        StringBuffer uusiSana = new StringBuffer();
        
        // Outer loop, goes first by word length, then word length-1 etc...
        for (int i = sana.length(); i > 1; i--) {
            List<Character> sananKirjaimet = new ArrayList<>();
            List<Character> sananKirjaimetKopio = new ArrayList<>();

            sananKirjaimet.clear();
            //Lisätään kirjaimet listaan
            for (int j = 0; j < sana.length(); j++) {
                sananKirjaimet.add(sana.charAt(j));
            }
            int count = 0;
            while (count < times) {
                sananKirjaimetKopio.addAll(sananKirjaimet);

                for (int k = 0; k < i; k++) {
                    int indeksi = r.nextInt(sananKirjaimetKopio.size());
                    uusiSana.append(sananKirjaimetKopio.get(indeksi));
                    sananKirjaimetKopio.remove(indeksi);
                }

                palautettava.add(uusiSana.toString());

                count++;
                sananKirjaimetKopio.clear();
                uusiSana.replace(0, uusiSana.length(), "");
            }
        }

        return palautettava;

    }

    public boolean onSuomea(String word) {
        return suomenkielenSanat.contains(word);
    }

    public String getSana() {
        return sana;
    } 

    public TreeSet<String> getSuomenkielenSanat() {
        return suomenkielenSanat;
    }
    
    
}
