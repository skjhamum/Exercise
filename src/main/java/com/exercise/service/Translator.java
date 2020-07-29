package com.exercise.service;

/**
 * Translates a foreign language word to English
 */
public interface Translator {
    /**
     * Sudo code to get English name for the passed foreign language word
     *
     * @param foreignWord - Foreign word
     * @return
     */
    public String translate(String foreignWord);


    /**
     * Maintains a map of foreignWord's to englishWord's
     *
     * @param foreignWord
     * @param englishWord
     * @return
     */
    public void addTranslation(String foreignWord, String englishWord);
}
