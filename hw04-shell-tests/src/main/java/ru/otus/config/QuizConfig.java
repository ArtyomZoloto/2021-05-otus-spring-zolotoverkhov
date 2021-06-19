package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.otus.exceptions.QuizException;

import javax.annotation.PostConstruct;
import java.util.Locale;

@ConfigurationProperties(prefix = "quiz")
@Component
public class QuizConfig {

    private Integer passScore;

    private String fileName;

    private String locale = "en-EN"; //default locale is EN

    @NonNull
    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    @NonNull
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @PostConstruct
    public void validate() {
        if (passScore == null) {
            throw new QuizException("Pass score is not set!");
        }
        if (fileName == null || fileName.isEmpty()) {
            throw new QuizException("Can't get source file name from settings.");
        }
        if (passScore < 0 || passScore > 100) {
            throw new QuizException("Please define pass score from 0 to 100 %");
        }
    }
}
