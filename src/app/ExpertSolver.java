package app;

import java.util.*;
import java.util.stream.Collectors;

public class ExpertSolver {

    private ExpertModeWindow expertModeWindow;
    private Set<String> possibleWords;

    public ExpertSolver(ExpertModeWindow expertModeWindow) {
        this.expertModeWindow = expertModeWindow;
    }

    public List<String> getAllPossibleWordsByLength() {
        int index = expertModeWindow.getWordLengthComboBox().getSelectionModel().getSelectedIndex();
        possibleWords = expertModeWindow.getSolver().getAllWordsInFinnish();
        possibleWords = possibleWords.stream().filter(n -> n.length() > index)
                .map(n -> n.substring(0, index + 1).replaceAll("-", "")).collect(Collectors.toCollection(HashSet::new));
        Set<String> listofAllWords = solveWord(expertModeWindow.getSolver().getWord(), 100000 * (index + 1), index + 1);

        // Checking the combobox language selection
        if (expertModeWindow.getLanguageComboBox().getSelectionModel().getSelectedIndex() == 0) {
            return listofAllWords.stream().filter(s -> this.isPossiblyFinnish(s)).collect(Collectors.toCollection(ArrayList::new));
        } else {
            return listofAllWords.stream().collect(Collectors.toCollection(ArrayList::new));
        }
    }

    public boolean isPossiblyFinnish(String word) {
        return possibleWords.contains(word);
    }

    public Set<String> solveWord(String word, int times, int wordLength) {

        Set<String> setToReturn = new HashSet<>();
        Random r = new Random();
        StringBuilder newWord = new StringBuilder();

        List<Character> wordCharacters = new ArrayList<>();
        List<Character> wordCharactersCopy = new ArrayList<>();

        wordCharacters.clear();

        for (int j = 0; j < word.length(); j++) {
            wordCharacters.add(word.charAt(j));
        }
        int count = 0;
        while (count < times) {
            wordCharactersCopy.addAll(wordCharacters);

            for (int k = 0; k < wordLength; k++) {
                int index = r.nextInt(wordCharactersCopy.size());
                newWord.append(wordCharactersCopy.remove(index));
            }
            setToReturn.add(newWord.toString());
            count++;
            wordCharactersCopy.clear();
            newWord.replace(0, newWord.length(), "");
        }

        return setToReturn;

    }

    public void handleExpertSearching() {
       List<String> list = getAllPossibleWordsByLength();
        expertModeWindow.setListViewItems(list);
    }

}
