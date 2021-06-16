package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;
import ru.otus.exceptions.QuizException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@Repository
public class QuestionsDaoImpl implements QuestionsDao {
    public static final int TEXT = 0;
    public static final int ANSWER = 5;


    private final String fileName;

    public QuestionsDaoImpl(@Value("${quiz.file.name}") String fileName) {
        this.fileName = fileName;
    }

    public List<Question> getAll() {
        List<Question> questionList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream(fileName)
        ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] segments = line.split(",");
                String text = segments[TEXT];
                int answer = Integer.parseInt(segments[ANSWER]);
                String[] options = new String[4];
                for (int i = 1; i < 5; i++) {
                    options[i - 1] = segments[i];
                }
                questionList.add(new Question(answer, text, options));
            }
            return questionList;
        } catch (IOException e) {
            throw new QuizException("Can't read source file");
        }
    }
}
