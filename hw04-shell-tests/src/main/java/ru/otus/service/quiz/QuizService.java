package ru.otus.service.quiz;

/**
 * Main service of Quiz app
 */

public interface QuizService {

    /**
     * Login the user
     * @param name user name
     * @param surname user surname
     */
    void login(String name, String surname);

    /**
     * Prints current logged user
     */
    void whoami();

    /**
     * Logout user from service.
     */
    void logout();


    /**
     * Start the quiz process for user
     */
    void startQuiz();

    /**
     * Send result of test to the output
     */
    void getResult();


    /**
     * Reset quiz data
     */
    void reset();

}
