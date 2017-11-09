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

    public Set<String> ratkaiseSana(String sana) {
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

            int times = 0;
            while (times < 500000) {
                sananKirjaimetKopio.addAll(sananKirjaimet);

                for (int k = 0; k < i; k++) {
                    int indeksi = r.nextInt(sananKirjaimetKopio.size());
                    uusiSana.append(sananKirjaimetKopio.get(indeksi));
                    sananKirjaimetKopio.remove(indeksi);
                }

                palautettava.add(uusiSana.toString());

                times++;
                sananKirjaimetKopio.clear();
                uusiSana.replace(0, uusiSana.length(), "");
            }
        }

        return palautettava;

    }

    public boolean onSuomea(String sana) {
        // First let's check if word is found in TreeSet
        if (suomenkielenSanat.contains(sana)) {
            return true;
        }
        // Following code was way too heavy for longer words
        for (String string : suomenkielenSanat) {
            if (string.length() >= sana.length()) {
                if (string.substring(0, sana.length()).equals(sana)) {
                    // Let's check that last letter isn't M, K,V tai J
                    if (sana.charAt(sana.length() - 1) != 'm' && sana.charAt(sana.length() - 1) != 'k'
                            && sana.charAt(sana.length() - 1) != 'v' && sana.charAt(sana.length() - 1) != 'j'
                            && sana.charAt(sana.length() - 1) != 'p') {
                        // Let's check that last letter isn't r if word length is 4 or less
                        if (sana.length() <= 4 && sana.charAt(sana.length() - 1) == 'r') {
                            continue;
                            // Let's check that last letter isn't l if word length is 4 or less
                        } else if (sana.length() <= 4 && sana.charAt(sana.length() - 1) == 'l') {
                            continue;
                        } else {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }
}
