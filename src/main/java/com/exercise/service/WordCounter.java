package com.exercise.service;

/**
 * Allows to add words and keeps a count of the same words passed in different languages.
 */
public interface WordCounter {

    /**
     * Add words to a map, by keeping count of the same words passed in different languages.
     * Uses Translator to translate the passed word into english and stores in the map. If the same word is passed
     * then the value of the hashmap is incremented.
     *
     * @param word
     */
    public void addWords(String word) throws CustomeException;

    /**
     * Returns the count of how many times a given word was added to the word counter
     *
     * @param key - word
     * @return - Returns the count of how many times a given word was added to the word counter
     */
    public int getWordCount(String key) throws CustomeException;
}
