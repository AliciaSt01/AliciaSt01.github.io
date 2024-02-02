import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatingGame {

	private JFrame frame;
	private JPanel panel;
	private JPanel guessingPanel;
	private JLabel randomRatingLabel;
	private Double randomRating;
	private Random RNG = new Random();

	private JButton higher;
	private JButton lower;
	private JButton correct;
	private JButton exit;

	private Movie selected;

	public RatingGame(Movie selected) {
		this.selected = selected;
	}

	public void createGameFrame() {
		frame = new JFrame();
		frame.setTitle("Guess the Rating!");

		panel = new JPanel(new BorderLayout());
		guessingPanel = new JPanel(new BorderLayout());

		randomRatingLabel = new JLabel();
		randomRating = RNG.nextDouble() * (10.0 - 0) + 0;
		randomRatingLabel.setText(String.format("%.2f", randomRating));
		panel.add(randomRatingLabel, BorderLayout.NORTH);

		higher = new JButton("+");
		higher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (randomRating >= Double.parseDouble(selected.rating())) {
					guessWrong();
				} else if (randomRating < Double.parseDouble(selected.rating())) {
					panel.remove(randomRatingLabel);
					randomRating = RNG.nextDouble() * (10.0 - randomRating) + randomRating;
					randomRatingLabel.setText(String.format("%.2f", randomRating));
					panel.add(randomRatingLabel, BorderLayout.CENTER);
				}
			}
		});

		lower = new JButton("-");
		lower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (randomRating <= Double.parseDouble(selected.rating())) {
					guessWrong();
				} else if (randomRating > Double.parseDouble(selected.rating())) {
					panel.remove(randomRatingLabel);
					randomRating = RNG.nextDouble() * (randomRating - 0) + 0;
					randomRatingLabel.setText(String.format("%.2f", randomRating));
					panel.add(randomRatingLabel, BorderLayout.CENTER);
				}
			}
		});

		correct = new JButton("==");
		correct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (randomRating >= Double.parseDouble(selected.rating()) - 0.5
						|| randomRating <= Double.parseDouble(selected.rating()) + 0.5) {
					guessCorrect();
				} else {
					guessWrong();
				}
			}
		});

		guessingPanel.add(higher, BorderLayout.WEST);
		guessingPanel.add(correct, BorderLayout.CENTER);
		guessingPanel.add(lower, BorderLayout.EAST);

		exit = new JButton("X");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeGame();
			}
		});

		panel.add(guessingPanel, BorderLayout.CENTER);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void guessWrong() {
    displayRightOrWrong("Wrong! Try Again!");
    JButton retry = new JButton("RETRY");
    retry.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
    	
    });
  }

	private void guessCorrect() {
		displayRightOrWrong("Correct!");
	}

	private void displayRightOrWrong(String rightOrWrong) {
		randomRatingLabel.setVisible(false);
		guessingPanel.setVisible(false);
		panel.remove(randomRatingLabel);
		panel.remove(guessingPanel);
		panel.add(new JLabel(rightOrWrong), BorderLayout.CENTER);
	}

	private void closeGame() {
		frame.setVisible(false);
	}

	public void run() {
		createGameFrame();
	}
}
