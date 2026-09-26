package org.example.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class InputDialog extends JDialog {
    private final JTextArea sentenceArea = new JTextArea(6, 32);
    private String result;

    public InputDialog(JFrame owner, String initialSentence) {
        super(owner, "Ввод данных", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        sentenceArea.setText(initialSentence);
        sentenceArea.setLineWrap(true);
        sentenceArea.setWrapStyleWord(true);
        sentenceArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JLabel promptLabel = new JLabel("Введите предложение:");
        JPanel contentPanel = new JPanel(new BorderLayout(8, 8));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        contentPanel.add(promptLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(sentenceArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Отмена");

        okButton.addActionListener(event -> {
            result = sentenceArea.getText();
            dispose();
        });
        cancelButton.addActionListener(event -> {
            result = null;
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(okButton);
        pack();
        setLocationRelativeTo(owner);
    }

    public String getResult() {
        return result;
    }
}
