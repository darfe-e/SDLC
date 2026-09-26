package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class WordInversionModel {
    private final List<ModelListener> listeners = new ArrayList<>();
    private String sourceSentence = "";
    private String invertedSentence = "";

    public String getSourceSentence() {
        return sourceSentence;
    }

    public String getInvertedSentence() {
        return invertedSentence;
    }

    public void setSourceSentence(String sourceSentence) {
        this.sourceSentence = sourceSentence;
        this.invertedSentence = invertWords(sourceSentence);
        notifyListeners();
    }

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.modelChanged(this);
        }
    }

    private String invertWords(String sentence) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            char symbol = sentence.charAt(i);
            if (Character.isLetterOrDigit(symbol)) {
                word.append(symbol);
            } else {
                appendReversedWord(result, word);
                result.append(symbol);
            }
        }

        appendReversedWord(result, word);
        return result.toString();
    }

    private void appendReversedWord(StringBuilder result, StringBuilder word) {
        if (word.isEmpty()) {
            return;
        }

        result.append(word.reverse());
        word.setLength(0);
    }
}