package com.exercise.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TranslatorTest {

    @Autowired
    private Translator translator;

    @DisplayName("When translations are set and translate is called then correct translated english value is returned")
    @Test
    void whenTranslationisSet_thenTranslateReturnsCorrectTranslatedEnglishValue() {
        translator.addTranslation("flor", "flower");
        translator.addTranslation("blume", "flower");

        assertEquals("flower", translator.translate("flor"));
    }

    @DisplayName("When translations are set in upper case and translate is called then correct translated english value is returned, as case is ignored while setting the value")
    @Test
    void whenTranslationisSetInUpperCase_thenTranslateReturnsCorrectTranslatedEnglishValue() {
        translator.addTranslation("flor", "flower");
        translator.addTranslation("BLUME", "flower");

        assertEquals("flower", translator.translate("blume"));
    }

    @DisplayName("When translations are not set and translate is called then the passed untranslated value is returned")
    @Test
    void whenTranslationisNotSet_thenTranslateReturnsTheSamePassedValue() {
        assertEquals("rose", translator.translate("rose"));
    }
}
