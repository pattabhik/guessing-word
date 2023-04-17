package com.mindera.guessword;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.mindera.guessword.constants.GameConstants;
import com.mindera.guessword.validations.GuessWordValidator;
import com.mindera.guessword.validations.LetterValidationResult;

/**
 * Game implementation class
 * 
 * @author Pattabhi
 *
 */
public class PlayGuessingWord implements PlayGame {
	// this holds list of words
	private static final List<String> WORDS_LIST = new ArrayList<>();

	/**
	 * defining the list of words to play which will be added to the list at the
	 * class load
	 */
	static {
		WORDS_LIST.add("apple");
		WORDS_LIST.add("dog");
		WORDS_LIST.add("cat");
		WORDS_LIST.add("book");
	}
	private Scanner readInput = new Scanner(System.in);
	private GuessWordValidator wordValidator;

	/**
	 * This is the starting point of the game
	 */
	@Override
	public void startGame() {
		final String actualWord = pickRandomWord();
		String maskedWord = maskWord(actualWord);
		System.out.println(">> Guess the word:" + maskedWord);
		this.wordValidator = new GuessWordValidator(GameConstants.TOTAL_LIFES, actualWord, maskedWord);
		guessLetter();
	}

	/**
	 * This will ask a letter to enter and validates. The message will be printed on
	 * the screen based on the validation result. Restarts the game or asks to guess
	 * further letters.
	 * 
	 */
	private void guessLetter() {
		System.out.print(">> Enter a Guessing Letter: ");
		final char enteredChar = readInput.next().toLowerCase().charAt(0);
		final LetterValidationResult validationResult = this.wordValidator.validateLetter(enteredChar);
		displayResult(validationResult);
		if (validationResult.isStartNewGame()) {
			startGame();
		} else {
			guessLetter();
		}
	}

	/**
	 * This will print the message on the monitor based on the validation result
	 * 
	 * @param validationResult
	 */
	private void displayResult(final LetterValidationResult validationResult) {
		if (this.wordValidator.getPartiallyMskedWord().indexOf(GameConstants.MASKING_CHAR) < 0) {
			System.out.println(">> You have won the game. The word was '" + this.wordValidator.getActualWord() + "'.");
		} else {
			if (validationResult.isCorrectGuess()) {
				System.out.println(">> Correct. " + this.wordValidator.getPartiallyMskedWord());
			} else if (validationResult.isAlreadyGuessed()) {
				System.out.println(">> You have already tried this letter");
			} else if (validationResult.isWrongGuess()) {
				if (this.wordValidator.getLifes() > 0) {
					System.out.println(">> Incorrect 1 life lost." + this.wordValidator.getLifes()
							+ " remaining. The current word is " + this.wordValidator.getPartiallyMskedWord());
				} else {
					System.out.println(">> You have lost the game.");
				}
			}
		}
	}

	/**
	 * This will pick the random word from the WORDS_LIST
	 * 
	 * @return
	 */
	public String pickRandomWord() {
		return WORDS_LIST.get(new Random().nextInt(WORDS_LIST.size()));
	}

	/**
	 * this will mask all the characters in the actual word with MASKING_CHAR value
	 * 
	 * @param actualWord
	 * @return
	 */
	public String maskWord(final String actualWord) {
		final StringBuilder maskBldr = new StringBuilder();
		for (final char eachChar : actualWord.toCharArray()) {
			maskBldr.append(GameConstants.MASKING_CHAR);
		}
		return maskBldr.toString();
	}
}
