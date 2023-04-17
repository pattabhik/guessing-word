package com.mindera.guessword.validations;

import com.mindera.guessword.constants.GameConstants;

/**
 * Validation class for validating the entered letter
 * 
 * @author Pattabhi
 *
 */
public class GuessWordValidator {
	private int lifes;
	private String actualWord;
	private String partiallyMskedWord;

	public GuessWordValidator(int lifes, String actualWord, String maskedWord) {
		super();
		this.lifes = lifes;
		this.actualWord = actualWord;
		this.partiallyMskedWord = maskedWord;
	}

	/**
	 * This will validate the entered letter and return the result
	 * 
	 * @param enteredChar
	 * @return
	 */
	public LetterValidationResult validateLetter(final char enteredChar) {
		final LetterValidationResult validationResult = new LetterValidationResult();
		if (actualWord.contains(String.valueOf(enteredChar))) {
			if (isLetterAlreadyGuessed(enteredChar)) {
				validationResult.setAlreadyGuessed(true);
			} else {
				validationResult.setCorrectGuess(true);
				this.partiallyMskedWord = unMaskGuessedLetters(enteredChar);
			}
		} else {
			validationResult.setWrongGuess(true);
			--this.lifes;
		}

		if (this.lifes <= 0 || this.partiallyMskedWord.indexOf(GameConstants.MASKING_CHAR) < 0) {
			validationResult.setStartNewGame(true);
		}
		return validationResult;
	}

	/**
	 * This will un-mask the letters which guessed correctly
	 * 
	 * @param enteredChar
	 * @return
	 */
	private String unMaskGuessedLetters(final char enteredChar) {
		char[] actlWrdCharArr = this.actualWord.toCharArray();
		char[] partlMskdWordArr = this.partiallyMskedWord.toCharArray();
		for (int i = 0; i < actlWrdCharArr.length; i++) {
			if (Character.compare(actlWrdCharArr[i], enteredChar) == 0
					&& Character.compare(partlMskdWordArr[i], GameConstants.MASKING_CHAR) == 0) {
				partlMskdWordArr[i] = enteredChar;
			}
		}
		return String.valueOf(partlMskdWordArr);
	}

	/**
	 * This will check whether the entered letter is already masked or not
	 * 
	 * @param enteredChar
	 * @return
	 */
	private boolean isLetterAlreadyGuessed(final char enteredChar) {
		char[] actlWrdCharArr = this.actualWord.toCharArray();
		char[] partlMskdWordArr = this.partiallyMskedWord.toCharArray();
		boolean isAlrdyGuessed = false;
		for (int i = 0; i < actlWrdCharArr.length; i++) {
			if (Character.compare(actlWrdCharArr[i], enteredChar) == 0
					&& Character.compare(partlMskdWordArr[i], GameConstants.MASKING_CHAR) != 0) {
				isAlrdyGuessed = true;
				break;
			}
		}
		return isAlrdyGuessed;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public String getActualWord() {
		return actualWord;
	}

	public void setActualWord(String actualWord) {
		this.actualWord = actualWord;
	}

	public String getPartiallyMskedWord() {
		return partiallyMskedWord;
	}

	public void setPartiallyMskedWord(String partiallyMskedWord) {
		this.partiallyMskedWord = partiallyMskedWord;
	}

}
