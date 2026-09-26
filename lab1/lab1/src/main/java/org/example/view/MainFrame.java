package org.example.view;

import org.example.model.ModelListener;
import org.example.model.WordInversionModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ModelListener {
    private final JTextArea sourceArea = createDisplayArea();
    private final JTextArea resultArea = createDisplayArea();
    private final JButton inputButton = new JButton("Ввести данные");

    public MainFrame() {
        super("Лабораторная работа 1. Вариант 8");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(620, 420));
        setLayout(new BorderLayout(12, 12));

        JLabel titleLabel = new JLabel("Утилита для инвертирования слов");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));

        JPanel dataPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
        dataPanel.add(createTextBlock("Последний ввод:", sourceArea));
        dataPanel.add(createTextBlock("Результат:", resultArea));

        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 16, 16, 16));
        actionPanel.add(inputButton, BorderLayout.EAST);

        add(titleLabel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void setInputAction(ActionListener listener) {
        inputButton.addActionListener(listener);
    }

    public String showInputDialog(String initialSentence) {
        InputDialog dialog = new InputDialog(this, initialSentence);
        dialog.setVisible(true);
        return dialog.getResult();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }

    public void updateFromModel(WordInversionModel model) {
        sourceArea.setText(model.getSourceSentence());
        resultArea.setText(model.getInvertedSentence());
    }

    @Override
    public void modelChanged(WordInversionModel model) {
        updateFromModel(model);
    }

    private JTextArea createDisplayArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        return textArea;
    }

    private JPanel createTextBlock(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.add(new JLabel(title), BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
}
