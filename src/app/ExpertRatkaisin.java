package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpertRatkaisin {
    
private long count = 0;
    private ExpertModeWindow expertModeWindow;
    private Set<String> possibleWords;

    public ExpertRatkaisin(ExpertModeWindow expertModeWindow) {
        this.expertModeWindow = expertModeWindow;
    }

    public List<String> getAllPossibleWordsByLength(int index) {
        possibleWords = expertModeWindow.getRatkaisin().getSuomenkielenSanat();
        possibleWords = possibleWords.stream().filter(n -> n.length() > index)
                .map(n -> n.substring(0, index+1).replaceAll("-", "")).collect(Collectors.toCollection(HashSet::new));
        Set<String> listofAllWords = solveWord(expertModeWindow.getRatkaisin().getSana(), 100000*(index+1), index+1);
        return listofAllWords.stream().filter(s -> this.isPossiblyFinnish(s)).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isPossiblyFinnish(String word) {
        return possibleWords.contains(word);
    }

    public Set<String> solveWord(String word, int times, int wordLength) {

        Set<String> palautettava = new HashSet<>();
        Random r = new Random();
        StringBuffer uusiSana = new StringBuffer();

        // Outer loop, goes first by word length, then word length-1 etc...
        List<Character> sananKirjaimet = new ArrayList<>();
        List<Character> sananKirjaimetKopio = new ArrayList<>();

        sananKirjaimet.clear();
        //Lisätään kirjaimet listaan
        for (int j = 0; j < word.length(); j++) {
            sananKirjaimet.add(word.charAt(j));
        }
        int count = 0;
        while (count < times) {
            sananKirjaimetKopio.addAll(sananKirjaimet);

            for (int k = 0; k < wordLength; k++) {
                int indeksi = r.nextInt(sananKirjaimetKopio.size());
                uusiSana.append(sananKirjaimetKopio.get(indeksi));
                sananKirjaimetKopio.remove(indeksi);
            }
            palautettava.add(uusiSana.toString());
            count++;
            sananKirjaimetKopio.clear();
            uusiSana.replace(0, uusiSana.length(), "");
        }

        return palautettava;

    }

}
