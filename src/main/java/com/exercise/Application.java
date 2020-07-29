package com.exercise;
import com.exercise.service.Translator;
import com.exercise.service.WordCounter;
import com.exercise.service.CustomeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner{
	private static Logger LOG = LoggerFactory
            .getLogger(Application.class);

    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    private WordCounter wordCounter;

    @Autowired
    private Translator translator;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Application.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args)
            throws IOException {
        LOG.info("EXECUTING : command line runner");


        /**
         * Please un-comment the below lines if you want to run the application from command line.
         *
         */


        /**      translator.addTranslation("flor", "flower");
        translator.addTranslation("blume", "flower");
        Scanner scanner = new Scanner(System.in);
        LOG.info("Enter the number of words: ");
        int numberOfWords = scanner.nextInt();
        for (int i = 0; i <numberOfWords ; i++) {
            LOG.info("Enter the {} word: ", i+1);
            try {
                wordCounter.addWords(scanner.next());
            } catch (CustomeException e) {
                LOG.error("Error:: " + e.getMessage());
            }
        }
        LOG.info("You have entered {} words", numberOfWords);
        LOG.info("Enter the word for which you want the count to be calculated " );
        String key = scanner.next();
        try {
            LOG.info("Number of times word {} is entered is {} ", key, wordCounter.getWordCount(key));
        } catch (CustomeException e) {
            LOG.error("Error:: " + e.getMessage());
        }
	*/
    }

}
