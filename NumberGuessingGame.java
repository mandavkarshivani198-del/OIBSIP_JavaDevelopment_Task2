package com.mycompany.numberguessinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {

    JLabel title, instruction, resultLabel, attemptsLabel, scoreLabel;
    JTextField guessField;
    JButton startButton, guessButton, resetButton;

    int number;
    int attempts = 0;
    int maxAttempts = 10;
    int score = 0;

    Random random = new Random();

    public NumberGuessingGame() {

        setTitle("Number Guessing Game");
        setSize(420,320);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);

        getContentPane().setBackground(new Color(220,235,255));

        title = new JLabel("NUMBER GUESSING GAME");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        instruction = new JLabel("Guess a number between 1 and 100");

        startButton = new JButton("Start Game");

        guessField = new JTextField(10);

        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        resultLabel = new JLabel("Result: ");

        attemptsLabel = new JLabel("Attempts: 0/10");

        scoreLabel = new JLabel("Score: 0");

        guessButton.setEnabled(false);

        // TITLE
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // INSTRUCTION
        gbc.gridy = 1;
        add(instruction, gbc);

        // START BUTTON
        gbc.gridy = 2;
        add(startButton, gbc);

        // TEXT FIELD
        gbc.gridy = 3;
        add(guessField, gbc);

        // GUESS BUTTON
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(guessButton, gbc);

        // RESET BUTTON
        gbc.gridx = 1;
        add(resetButton, gbc);

        // RESULT
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        // ATTEMPTS
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(attemptsLabel, gbc);

        // SCORE
        gbc.gridx = 1;
        add(scoreLabel, gbc);

        startButton.addActionListener(this);
        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            number = random.nextInt(100) + 1;
            attempts = 0;

            resultLabel.setText("Game Started!");
            attemptsLabel.setText("Attempts: 0/10");

            guessButton.setEnabled(true);
        }

        else if (e.getSource() == guessButton) {

            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == number) {

                int points = (maxAttempts - attempts + 1) * 10;
                score += points;

                scoreLabel.setText("Score: " + score);

                JOptionPane.showMessageDialog(this,
                        "Correct! You earned " + points + " points");

                guessButton.setEnabled(false);
                resultLabel.setText("You Won!");
            }

            else if (guess > number) {
                resultLabel.setText("Too High!");
            }

            else {
                resultLabel.setText("Too Low!");
            }

            attemptsLabel.setText("Attempts: " + attempts + "/10");

            if (attempts >= maxAttempts && guess != number) {

                JOptionPane.showMessageDialog(this,
                        "Game Over! Number was " + number);

                guessButton.setEnabled(false);
            }

            guessField.setText("");
        }

        else if (e.getSource() == resetButton) {

            number = random.nextInt(100) + 1;
            attempts = 0;

            resultLabel.setText("New Game Started");
            attemptsLabel.setText("Attempts: 0/10");

            guessButton.setEnabled(true);
            guessField.setText("");
        }
    }

    public static void main(String[] args) {

        new NumberGuessingGame();
    }
}