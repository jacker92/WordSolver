package data;

import java.io.*;
import java.util.*;

public class Solver {

    private String word;
    private TreeSet<String> allWordsInFinnish;

    public Solver() {
        this.word = "";
        this.allWordsInFinnish = new TreeSet<>();
        try {
            addWords();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void addWords() {
        File file = new File(getClass().getClassLoader().getResource("app/sanat.txt").getFile());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            reader.lines().forEach(e -> {
                String[] list = e.split("<");
                String needToAdd = list[0];
                if (needToAdd.length() > 1) {
                    needToAdd.replaceAll("-", "").trim();
                    allWordsInFinnish.add(needToAdd);
                }
            });
        } catch (IOException e) {
            System.out.println("Error loading file");
        }

    }

    public Set<String> solveWord(String word, int times) {
        this.word = word;
        Set<String> setToReturn = new HashSet<>();
        Random r = new Random();
        StringBuilder newWord = new StringBuilder();
        
        // Outer loop, goes first by word length, then word length-1 etc...
        for (int i = word.length(); i > 1; i--) {
            List<Character> wordCharacters = new ArrayList<>();
            List<Character> wordCharactersCopy = new ArrayList<>();

            wordCharacters.clear();
            
            for (int j = 0; j < word.length(); j++) {
                wordCharacters.add(word.charAt(j));
            }
            int count = 0;
            while (count < times) {
                wordCharactersCopy.addAll(wordCharacters);

                for (int k = 0; k < i; k++) {
                    int index = r.nextInt(wordCharactersCopy.size());
                    newWord.append(wordCharactersCopy.remove(index));
                }

                setToReturn.add(newWord.toString());

                count++;
                wordCharactersCopy.clear();
                newWord.replace(0, newWord.length(), "");
            }
        }
        return setToReturn;
    }

    public boolean isFinnish(String word) {
        return allWordsInFinnish.contains(word);
    }

    public String getWord() {
        return word;
    } 

    public TreeSet<String> getAllWordsInFinnish() {
        return allWordsInFinnish;
    }
    
    
}
