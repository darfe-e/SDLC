package org.example;

import org.example.controller.WordInversionController;
import org.example.model.WordInversionModel;
import org.example.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordInversionModel model = new WordInversionModel();
            MainFrame view = new MainFrame();
            new WordInversionController(model, view);

            view.setVisible(true);
        });
    }
}
