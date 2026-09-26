package org.example.controller;


import org.example.model.WordInversionModel;
import org.example.view.MainFrame;

public class WordInversionController {
    private final WordInversionModel model;
    private final MainFrame view;

    public WordInversionController(WordInversionModel model, MainFrame view) {
        this.model = model;
        this.view = view;

        this.model.addListener(view);
        this.view.setInputAction(event -> requestSentence());
        this.view.updateFromModel(model);
    }

    private void requestSentence() {
        String sentence = view.showInputDialog(model.getSourceSentence());

        if (sentence == null) {
            return;
        }

        if (sentence.trim().isEmpty()) {
            view.showError("Введите непустое предложение.");
            return;
        }

        model.setSourceSentence(sentence);
    }
}
