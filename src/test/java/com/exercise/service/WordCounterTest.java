package com.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import com.exercise.Application;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WordCounterTest {

    private static Logger LOG = LoggerFactory
            .getLogger(Application.class);

    @Mock
    private Translator translator;

    private WordCounter wordCounter;

    @BeforeEach
    void setMocktranslator() {
        wordCounter = new WordCounterImpl(translator);
    }


    @DisplayName("Gives correct count when same words in different languages are added")
    @Test
    void whenSameWordsInDifferentLanguagesAreAdded_thenCorrectCountIsReturned() throws CustomeException {
        givenTranslationsAreSet();
        givenAlphabeticWordsAreAdded();
        assertEquals(3, wordCounter.getWordCount("flor"));
        assertEquals(1, wordCounter.getWordCount("yellow"));
    }

    @DisplayName("Word case is ignored")
    @Test
    void whenWordsInUpperCaseAreAddedAndCountIsCalledInLowerCase_thenCorrectCountIsReturned() throws CustomeException {
        givenTranslationsAreSet();
        givenUpperCaseAlphabeticWordsAreAdded();
        assertEquals(1, wordCounter.getWordCount("red"));
        assertEquals(1, wordCounter.getWordCount("RED"));
        assertEquals(1, wordCounter.getWordCount("Red"));
        assertEquals(1, wordCounter.getWordCount("Blue"));
    }

    @DisplayName("WordCounterNonAlphabetException is thrown for null world")
    @Test
    void whenNullWordIsAdded_thenCountIsZero() {
        try {
            givenNonAlphabeticWordsAreAdded(null);
            assertEquals(0, wordCounter.getWordCount(null));
        } catch (CustomeException e) {
            LOG.error("Error in getting word count for null.");
            LOG.error("Error:: " + e.getMessage());
        }
    }

    @DisplayName("WordCounterNonAlphabetException is thrown for worlds with spaces")
    @Test
    void whenWordWithSpaceIsAdded_thenExceptionIsThrown() {
        try {
            givenNonAlphabeticWordsAreAdded(" ");
            assertEquals(0, wordCounter.getWordCount(" "));
        } catch (CustomeException e) {
            LOG.error("Error in getting word count for {}", " ");
            LOG.error("Error:: " + e.getMessage());
        }
    }

    @DisplayName("WordCounterNonAlphabetException is thrown for empty world")
    @Test
    void whenBlankWordIsAdded_thenExceptionIsThrown() {
        try {
            givenNonAlphabeticWordsAreAdded(" ");
            assertEquals(0, wordCounter.getWordCount(""));
        } catch (CustomeException e) {
            LOG.error("Error in getting word count for empty word");
            LOG.error("Error:: " + e.getMessage());
        }
    }

    @DisplayName("WordCounterNonAlphabetException is throw for Non-Alphabetic words with special chars")
    @Test
    void whenNonAlphabeticWordsWithSpecialCharsAreAdded_thenExceptionIsThrown() {
        try {
            givenNonAlphabeticWordsAreAdded("notadded_&£$*()");
            assertEquals(0, wordCounter.getWordCount("notadded_&£$*()"));
        } catch (CustomeException e) {
            LOG.error("Error in getting word count for empty {} word", "notadded_&£$*()");
            LOG.error("Error:: " + e.getMessage());
        }
    }

    @DisplayName("WordCounterNonAlphabetException is thrown for Non-Alphabetic words with numbers")
    @Test
    void whenNonAlphabeticWordsWithNumbersAreAdded_thenExceptionIsThrown() {
        try {
            givenNonAlphabeticWordsAreAdded("notadded12");
            assertEquals(0, wordCounter.getWordCount("notadded12"));
        } catch (CustomeException e) {
            LOG.error("Error in getting word count for empty {} word", "notadded12");
            LOG.error("Error:: " + e.getMessage());
        }
    }

    private void givenNonAlphabeticWordsAreAdded(String word) throws CustomeException {
        wordCounter.addWords(word);
    }

    private void givenTranslationsAreSet() {
        when(translator.translate("flor")).thenReturn("flower");
        when(translator.translate("blume")).thenReturn("flower");
        when(translator.translate("flower")).thenReturn("flower");
        when(translator.translate("red")).thenReturn("red");
        when(translator.translate("blue")).thenReturn("blue");
    }

    private void givenAlphabeticWordsAreAdded() throws CustomeException {
        wordCounter.addWords("flower");
        wordCounter.addWords("flor");
        wordCounter.addWords("blume");
        wordCounter.addWords("yellow");
    }

    private void givenUpperCaseAlphabeticWordsAreAdded() throws CustomeException {
        wordCounter.addWords("RED");
        wordCounter.addWords("Blue");
    }
}
