package com.mindera.guessword;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Junits for PlayGuessingWord functions
 * 
 * @author Pattabhi
 *
 */
public class PlayGuessingWordTest {

	@Test
	public void test_randomWord_notNull() {
		PlayGuessingWord playGuessingWord = new PlayGuessingWord();
		String randomWrd = playGuessingWord.pickRandomWord();
		assertNotNull(randomWrd);
	}

	@Test
	public void test_word_masked() {
		PlayGuessingWord playGuessingWord = new PlayGuessingWord();
		String randomWrd = playGuessingWord.pickRandomWord();
		assertTrue(playGuessingWord.maskWord(randomWrd).contains("*"));
	}

}
