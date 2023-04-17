package com.mindera.guessword.validations;

/**
 * This is to hold the multiple values of a letter validation result
 * 
 * @author Pattabhi
 *
 */
public class LetterValidationResult {

	private boolean alreadyGuessed;
	private boolean correctGuess;
	private boolean wrongGuess;
	private boolean startNewGame;

	public boolean isAlreadyGuessed() {
		return alreadyGuessed;
	}
	public void setAlreadyGuessed(boolean alreadyGuessed) {
		this.alreadyGuessed = alreadyGuessed;
	}

	public boolean isCorrectGuess() {
		return correctGuess;
	}

	public void setCorrectGuess(boolean correctGuess) {
		this.correctGuess = correctGuess;
	}

	public boolean isWrongGuess() {
		return wrongGuess;
	}

	public void setWrongGuess(boolean wrongGuess) {
		this.wrongGuess = wrongGuess;
	}

	public boolean isStartNewGame() {
		return startNewGame;
	}

	public void setStartNewGame(boolean startNewGame) {
		this.startNewGame = startNewGame;
	}

}
