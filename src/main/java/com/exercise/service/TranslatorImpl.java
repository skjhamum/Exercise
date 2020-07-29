package com.exercise.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Translates a foreign language word to english
 */
@Component
public class TranslatorImpl implements Translator {

    private Map<String, String> foreignEnglishMap = new HashMap<String, String>();

    /**
     * Sudo code to get english name for the passed foreign language word
     *
     * @param foreignWord - Foreign word
     * @return
     */
    public String translate(String foreignWord) {
        return foreignEnglishMap.getOrDefault(foreignWord, foreignWord);
    }


    /**
     * Maintains a map of foreignWord's to englishWord's
     *
     * @param foreignWord
     * @param englishWord
     * @return
     */
    public void addTranslation(String foreignWord, String englishWord) {
        if (StringUtils.isNotEmpty(foreignWord) && StringUtils.isNotEmpty(englishWord)) {
            foreignEnglishMap.putIfAbsent(foreignWord.toLowerCase(), englishWord.toLowerCase());
        }
    }


}
