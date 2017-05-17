package org.hopto.gameserver.basicencryptioncracker.frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.hopto.gameserver.basicencryptioncracker.engine.AlphabetRotationEngine;
import org.hopto.gameserver.basicencryptioncracker.engine.PasswordRotationEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame {
    private JPanel panel;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JRadioButton verschlüsselnRadioButton;
    private JRadioButton entschlüsselnRadioButton;
    private JRadioButton alphabetRotationRadioButton;
    private JRadioButton geheimwortRadioButton;
    private JSpinner spinner1;
    private JTextField textField1;
    private JPanel modePanel;
    private JPanel alphaPanel;
    private JPanel optionsPanel;
    private JRadioButton vigenereHäufigkeitsanalyseRadioButton;
    private JButton häufigkeitsanalyseStartenButton;
    private JLabel label1;
    private boolean encrypt = true;
    private int mode = 0;

    private void doWork() {
        if (encrypt) {
            if (mode == 0) {
                String text = AlphabetRotationEngine.encryptString(textArea1.getText(), (Integer) spinner1.getValue());
                textArea2.setText(text);
            }
            else if (mode == 1) {
                PasswordRotationEngine pre = new PasswordRotationEngine(textField1.getText());
                textArea2.setText(pre.encrypt(textArea1.getText()));
            }
        }
        else {
            if (mode == 0) {
                String text = AlphabetRotationEngine.decryptString(textArea1.getText(), (Integer) spinner1.getValue());
                textArea2.setText(text);
            }
            else if (mode == 1) {
                PasswordRotationEngine pre = new PasswordRotationEngine(textField1.getText());
                textArea2.setText(pre.decrypt(textArea1.getText()));
            }
        }
    }

    public MainFrame() {
        //Border
        this.modePanel.setBorder(BorderFactory.createTitledBorder("Modus"));
        this.alphaPanel.setBorder(BorderFactory.createTitledBorder("Generierung"));
        this.optionsPanel.setBorder(BorderFactory.createTitledBorder("Optionen"));

        final ButtonGroup modeBg = new ButtonGroup();
        modeBg.add(verschlüsselnRadioButton);
        modeBg.add(entschlüsselnRadioButton);
        verschlüsselnRadioButton.setSelected(true);
        ButtonGroup alphaBg = new ButtonGroup();
        alphaBg.add(alphabetRotationRadioButton);
        alphaBg.add(geheimwortRadioButton);
        alphaBg.add(vigenereHäufigkeitsanalyseRadioButton);
        alphabetRotationRadioButton.setSelected(true);

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 25, 1);
        spinner1.setModel(sm);

        verschlüsselnRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                encrypt = true;
            }
        });
        entschlüsselnRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                encrypt = false;
            }
        });
        alphabetRotationRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mode = 0;
                spinner1.setEnabled(true);
                textField1.setEnabled(false);
                häufigkeitsanalyseStartenButton.setEnabled(false);
                SpinnerModel sm = new SpinnerNumberModel(0, 0, 25, 1);
                spinner1.setModel(sm);
                label1.setText("Rotation um");
            }
        });
        geheimwortRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mode = 1;
                spinner1.setEnabled(false);
                textField1.setEnabled(true);
                häufigkeitsanalyseStartenButton.setEnabled(false);
            }
        });
        vigenereHäufigkeitsanalyseRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mode = 2;
                spinner1.setEnabled(true);
                textField1.setEnabled(false);
                häufigkeitsanalyseStartenButton.setEnabled(true);
                SpinnerModel sm = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
                spinner1.setModel(sm);
                label1.setText("Schlüssellänge");
            }
        });
        häufigkeitsanalyseStartenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new VigenereFrequencyFrame(textArea1.getText(), (Integer) spinner1.getValue());
            }
        });


        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                doWork();
            }
        });

        spinner1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                doWork();
            }
        });


        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setResizable(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BasicEncryptionCrackerHelperTool");
        frame.setVisible(true);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        textArea1 = new JTextArea();
        panel.add(textArea1, new GridConstraints(1, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(354, 50), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Eingabetext");
        panel.add(label2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(244, 15), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Ausgabe");
        panel.add(label3, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(244, 15), null, 0, false));
        textArea2 = new JTextArea();
        textArea2.setEditable(false);
        panel.add(textArea2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(354, 50), null, 0, false));
        modePanel = new JPanel();
        modePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(modePanel, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        verschlüsselnRadioButton = new JRadioButton();
        verschlüsselnRadioButton.setText("Verschlüsseln");
        modePanel.add(verschlüsselnRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        entschlüsselnRadioButton = new JRadioButton();
        entschlüsselnRadioButton.setText("Entschlüsseln");
        modePanel.add(entschlüsselnRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alphaPanel = new JPanel();
        alphaPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(alphaPanel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        alphabetRotationRadioButton = new JRadioButton();
        alphabetRotationRadioButton.setText("Alphabet-Rotation");
        alphaPanel.add(alphabetRotationRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        geheimwortRadioButton = new JRadioButton();
        geheimwortRadioButton.setText("Geheimwort");
        alphaPanel.add(geheimwortRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        vigenereHäufigkeitsanalyseRadioButton = new JRadioButton();
        vigenereHäufigkeitsanalyseRadioButton.setText("Vigenere Häufigkeitsanalyse");
        alphaPanel.add(vigenereHäufigkeitsanalyseRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(optionsPanel, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label1 = new JLabel();
        label1.setText("Rotation um");
        optionsPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinner1 = new JSpinner();
        optionsPanel.add(spinner1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Geheimwort");
        optionsPanel.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEnabled(false);
        optionsPanel.add(textField1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        häufigkeitsanalyseStartenButton = new JButton();
        häufigkeitsanalyseStartenButton.setEnabled(false);
        häufigkeitsanalyseStartenButton.setText("Häufigkeitsanalyse starten");
        panel.add(häufigkeitsanalyseStartenButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
