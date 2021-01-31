package fr.esaudm.ggj2021.binedit;

import fr.esaudm.ggj2021.Main;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class BinaryEditorWindow extends JPanel implements ActionListener, CaretListener, KeyListener {

    private final JFrame window;
    private final JTextArea binaryEditor = new JTextArea(0, 71);
    private final JTextArea utfEditor = new JTextArea();
    private final Button saveButton = new Button("Save");
    private final Button openButton = new Button("Open");
    private Object binaryHighlightInfo;
    private Object utfHighlightInfo;
    private final BinaryText binaryText = new BinaryText();

    public BinaryEditorWindow() {
        this.window = new JFrame("Binary Editor");
        this.window.addWindowListener(new Main.WindowClosingHandler(this));
        this.window.setContentPane(this);
        this.window.setSize(1000, 800);
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.binaryEditor.addCaretListener(this);
        this.utfEditor.addCaretListener(this);
        this.binaryEditor.addKeyListener(this);
        this.utfEditor.addKeyListener(this);
        this.binaryEditor.setWrapStyleWord(true);
        this.utfEditor.setWrapStyleWord(true);
        this.binaryEditor.setLineWrap(true);
        this.utfEditor.setLineWrap(true);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(new JScrollPane(this.binaryEditor), constraints);
        constraints.gridy = 1;
        this.add(new JScrollPane(this.utfEditor), constraints);
        this.saveButton.addActionListener(this);
        this.openButton.addActionListener(this);
        constraints.weighty = 1;
        constraints.gridy = 2;
        this.add(this.saveButton, constraints);
        constraints.gridy = 3;
        this.add(this.openButton, constraints);
        this.window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveButton) {
            this.save();
        } else if (e.getSource() == this.openButton) {
            this.open();
        }
    }

    public void save() {
        FileDialog fileDialog = new FileDialog((Dialog) null, "Save file");
        fileDialog.setVisible(true);
        String file = fileDialog.getFile();
        if (file == null) {
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileDialog.getDirectory() + "/" + file));
            writer.write(this.utfEditor.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        FileDialog fileDialog = new FileDialog((Dialog) null, "Open file");
        fileDialog.setVisible(true);
        String file = fileDialog.getFile();
        if (file == null) {
            return;
        }
        this.binaryText.open(new File(fileDialog.getDirectory() + "/" + file));
        this.binaryEditor.setText(this.binaryText.getBinary());
        this.utfEditor.setText(this.binaryText.getText());
    }


    @Override
    public void caretUpdate(CaretEvent e) {
        Highlighter binaryHighlighter = this.binaryEditor.getHighlighter();
        Highlighter utfHighlighter = this.utfEditor.getHighlighter();
        if (this.utfHighlightInfo != null) {
            utfHighlighter.removeHighlight(this.utfHighlightInfo);
        }
        if (this.binaryHighlightInfo != null) {
            binaryHighlighter.removeHighlight(this.binaryHighlightInfo);
        }
        int start = Math.min(e.getMark(), e.getDot());
        int end = Math.max(e.getMark(), e.getDot());
        if (e.getSource() == this.binaryEditor) {
            try {
                this.utfHighlightInfo = utfHighlighter.addHighlight(start / 9, end / 9 + 1, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
            } catch (BadLocationException exception) {
                exception.printStackTrace();
            }
        } else if (e.getSource() == this.utfEditor) {
            if (e.getDot() == e.getMark()) {
                return;
            }
            try {
                this.binaryHighlightInfo = binaryHighlighter.addHighlight(start * 9, end * 9 - 1, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
            } catch (BadLocationException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == this.binaryEditor) {
            this.binaryText.setBinary(this.binaryEditor.getText());
            this.utfEditor.setText(this.binaryText.getText());
        } else if (e.getSource() == this.utfEditor) {
            this.binaryText.setText(this.utfEditor.getText());
            this.binaryEditor.setText(this.binaryText.getBinary());
        }
    }


}
