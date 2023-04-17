package com.mindera.guessword;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.mindera.guessword.constants.GameConstants;
import com.mindera.guessword.validations.GuessWordValidator;
import com.mindera.guessword.validations.LetterValidationResult;

/**
 * Junits for GameValidator functions
 * 
 * @author Pattabhi
 *
 */
public class GameValidatorTest {

	@Test
	public void test_wordValidation() {
		String actualWord = "Book";
		GuessWordValidator wordValidator = new GuessWordValidator(GameConstants.TOTAL_LIFES, actualWord,
				new PlayGuessingWord().maskWord(actualWord));
		LetterValidationResult validationResult = null;

		// letter B trial
		validationResult = wordValidator.validateLetter('B');
		assertEquals(false, validationResult.isWrongGuess());
		assertEquals(true, validationResult.isCorrectGuess());
		assertEquals(false, validationResult.isAlreadyGuessed());
		assertEquals(5, wordValidator.getLifes());
		assertEquals("B***", wordValidator.getPartiallyMskedWord());

		// letter o trial
		validationResult = wordValidator.validateLetter('o');
		assertEquals(false, validationResult.isWrongGuess());
		assertEquals(true, validationResult.isCorrectGuess());
		assertEquals(false, validationResult.isAlreadyGuessed());
		assertEquals(5, wordValidator.getLifes());
		assertEquals("Boo*", wordValidator.getPartiallyMskedWord());

		// wrong letter l trial
		validationResult = wordValidator.validateLetter('l');
		assertEquals(true, validationResult.isWrongGuess());
		assertEquals(false, validationResult.isCorrectGuess());
		assertEquals(false, validationResult.isAlreadyGuessed());
		assertEquals(4, wordValidator.getLifes());
		assertEquals("Boo*", wordValidator.getPartiallyMskedWord());

		// letter k trial
		validationResult = wordValidator.validateLetter('k');
		assertEquals(false, validationResult.isWrongGuess());
		assertEquals(true, validationResult.isCorrectGuess());
		assertEquals(false, validationResult.isAlreadyGuessed());
		assertEquals(4, wordValidator.getLifes());
		assertEquals("Book", wordValidator.getPartiallyMskedWord());
	}

}
