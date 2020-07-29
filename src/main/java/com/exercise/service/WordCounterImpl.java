package com.exercise.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exercise.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows to add words and keeps a count of the same words passed in different languages.
 */
@Component
public class WordCounterImpl implements WordCounter {
    private static Logger LOG = LoggerFactory
            .getLogger(Application.class);

    private Map<String, Integer> wordCountMap = new HashMap<String, Integer>();

    private Translator translator;

    @Autowired
    public WordCounterImpl(Translator translator) {
        this.translator = translator;
    }

    /**
     * Add words to a map, by keeping count of the same words passed in different languages.
     * Uses Translator to translate the passed word into english and stores in the map. If the same word is passed
     * then the value of the hashmap is incremented.
     *
     * @param word
     */
    public void addWords(String word) throws CustomeException {
        if (valIdate(word)) {
            String englishWord = translator.translate(word.toLowerCase());
            wordCountMap.putIfAbsent(englishWord, 0);
            int count = wordCountMap.get(englishWord);
            wordCountMap.put(englishWord, ++count);
        }
    }

    /**
     * Returns the count of how many times a given word was added to the word counter
     *
     * @param key - word
     * @return - Returns the count of how many times a given word was added to the word counter
     */
    public int getWordCount(String key) throws CustomeException {
        if (valIdate(key)) {
            String englishWord = translator.translate(key.toLowerCase());
            return wordCountMap.getOrDefault(englishWord, 0);
        } else {
            return 0;
        }
    }

    /**
     * Checks if the passed word is alphabet
     *
     * @param word
     * @return - returns true if passed word is alphabet
     */
    private boolean valIdate(String word) throws CustomeException {
        if (StringUtils.isAlpha(word)) {
            return true;
        } else {
            LOG.error("Ignoring the entered world {}, as it is not an alphabetic", word);
            throw new CustomeException("Ignoring the entered world " + word + ", as it is not an alphabetic");
        }
    }
}
