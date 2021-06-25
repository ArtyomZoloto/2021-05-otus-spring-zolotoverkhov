package ru.otus.service.quiz;

import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;
import ru.otus.io.IOService;
import ru.otus.messages.MessageFactory;
import ru.otus.service.questions.QuestionsService;
import ru.otus.service.validator.ValidationServiceImpl;

import static ru.otus.messages.MessagesConstants.*;



@Service
public class QuizServiceImpl implements QuizService {
    private final QuestionsService questionsService;
    private final IOService ioService;
    private final ValidationServiceImpl validationService;
    private final MessageFactory messageFactory;
    private final QuestionsDao dao;

    /** CURRENT STATE OF THE APPLICATION **/
    private User currentUser;
    private QuizResult quizResult;


    public QuizServiceImpl(IOService ioService,
                           QuestionsService questionsService,
                           ValidationServiceImpl validationService,
                           MessageFactory messageFactory,
                           QuestionsDao dao) {
        this.ioService = ioService;
        this.questionsService = questionsService;
        this.validationService = validationService;
        this.messageFactory = messageFactory;
        this.dao = dao;
    }

    @Override
    public void login(String name, String surname) {
        if (currentUser != null) {
            this.print(ALREADY_LOGGED, currentUser.getName(), currentUser.getSurname());
            return;
        }
        currentUser = new User(name, surname);
        this.print(ALREADY_LOGGED, currentUser.getName(), currentUser.getSurname());
    }

    @Override
    public void whoami() {
        if(checkLogin()) {
            print(ALREADY_LOGGED, currentUser.getName(), currentUser.getSurname());
        }
    }

    @Override
    public void logout() {
        if (checkLogin()) {
            currentUser = null;
            print(LOGGED_OUT);
        }
    }

    @Override
    public void startQuiz() {
        if (checkLogin()) {
            quizResult = validationService.getQuizResult(questionsService.processQuestions(dao.getAll()));
            quizResult.setUser(currentUser);
        }
    }

    @Override
    public void getResult() {
        if (!checkLogin()) {
            return;
        }
        if (quizResult == null) {
            print(NEED_PROCESS_QUIZ);
            return;
        }
        print(  quizResult.isPassed() ? QUIZ_PASSED : QUIZ_FAILED,
                quizResult.getUser().getName(),
                quizResult.getUser().getSurname(),
                quizResult.getPercentage());
    }

    @Override
    public void reset() {
        quizResult = null;
        print(QUIZ_DATA_DELETED);
    }

    private void print(String str, Object ...args) {
        ioService.print(messageFactory.getLocalizedMessage(
                str, args));
    }

    private boolean checkLogin() {
        if (currentUser == null) {
            print(NEED_LOGIN);
            return false;
        }
        return true;
    }
}
