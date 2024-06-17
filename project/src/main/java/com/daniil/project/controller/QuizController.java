package com.daniil.project.controller;

import com.daniil.project.entity.*;
import com.daniil.project.form.QuestionForm;
import com.daniil.project.form.QuizForm;
import com.daniil.project.service.question.QuestionService;
import com.daniil.project.service.quiz.QuizService;
import com.daniil.project.service.score.ScoreService;
import com.daniil.project.service.topic.TopicService;
import com.daniil.project.service.user.UserService;
import com.daniil.project.utility.Utility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final UserService userService;
    private final ScoreService scoreService;
    private final TopicService topicService;

    public QuizController(QuizService quizService, QuestionService questionService, UserService userService, ScoreService scoreService, TopicService topicService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.userService = userService;
        this.scoreService = scoreService;
        this.topicService = topicService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("featuredQuizzes", quizService.getFeaturedQuizzes());
        return "home";
    }

    @GetMapping("/create-quiz")
    public String createQuizPage(Model model) {
        model.addAttribute("quizForm", new QuizForm());
        model.addAttribute("topics", topicService.getAllTopics());
        return "quiz/create-quiz";
    }

    @GetMapping("/quiz/{quizId}")
    public String quizPage(@PathVariable int quizId, Model model) {
        model.addAttribute("quiz", quizService.findQuizById(quizId));
        model.addAttribute("questionList", questionService.getQuestionsForQuiz(quizId));
        return "quiz/quiz";
    }

    @GetMapping("/results/{quizId}")
    public String resultPage(@PathVariable int quizId, Model model, Principal principal,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Score> scorePage = scoreService.getUserScoresForQuiz(principal.getName(), quizId, currentPage - 1, pageSize);
        model.addAttribute("pageNumbers", Utility.getPageNumbers(scorePage));
        model.addAttribute("scores", scorePage);
        return "quiz/result";
    }

    @GetMapping("/results")
    public String resultPage(Model model, Principal principal,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Score> scorePage = scoreService.getUserScores(principal.getName(), currentPage - 1, pageSize);
        model.addAttribute("pageNumbers", Utility.getPageNumbers(scorePage));
        model.addAttribute("scores", scorePage);
        return "quiz/result";
    }

    @GetMapping("/quizzes")
    public String quizPage(Model model, @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Quiz> quizPage = quizService.findAllQuizzes(currentPage - 1, pageSize);
        model.addAttribute("quizzes", quizPage);
        model.addAttribute("pageNumbers", Utility.getPageNumbers(quizPage));
        return "quiz/quizzes";
    }

    @GetMapping("/quizzes/filter={filter}")
    public String filteredQuizPage(@PathVariable String filter, Model model, @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Quiz> quizPage = quizService.findQuizzesByName(currentPage - 1, pageSize, filter);
        model.addAttribute("quizzes", quizPage);
        model.addAttribute("pageNumbers", Utility.getPageNumbers(quizPage));
        return "quiz/quizzes";
    }

    @PostMapping("/quiz/submitAnswers")
    public String submitAnswers(@ModelAttribute("quizId") int quizId, @RequestParam("selectedOptions") List<Integer> selectedOptions, Principal principal) {

        Quiz quizFromDB = quizService.findQuizById(quizId);
        int correctAnswers = 0;
        for (int i = 0; i < selectedOptions.size(); i++) {
            int selectedOptionId = selectedOptions.get(i);
            List<Option> options = quizFromDB.getQuestionList().get(i).getOptionList();
            for (var option : options) {
                if (option.getId() == selectedOptionId && option.getIsCorrect() == 1) {
                    correctAnswers++;
                }
            }
        }
        User user = userService.findByUsername(principal.getName());
        Score score = new Score();
        score.setUser(user);
        score.setQuiz(quizFromDB);
        score.setScore(correctAnswers);

        scoreService.save(score);

        return "redirect:/results/" + quizId;
    }

    @PostMapping("/save")
    public String saveQuiz(@ModelAttribute("quizForm") QuizForm quizForm) {
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizForm.getQuizName());
        quiz.setTopic(topicService.findTopicById(quizForm.getSelectedTopicId()));
        List<Question> questionList = new ArrayList<>();
        for (var question : quizForm.getQuestions()) {
            if (question.getText() != null) {
                Question questionToAdd = getQuestion(question, quiz);
                questionList.add(questionToAdd);
            }
        }
        quiz.setQuestionList(questionList);
        quizService.save(quiz);
        return "redirect:/";
    }

    private Question getQuestion(QuestionForm question, Quiz quiz) {
        Question questionToAdd = new Question();
        questionToAdd.setQuiz(quiz);
        questionToAdd.setText(question.getText());
        int index = 0;
        for (var option : question.getOptions()) {
            Option optionToAdd = new Option();
            optionToAdd.setText(option.getText());
            if (index == question.getCorrectOptionIndex())
                optionToAdd.setIsCorrect(1);
            optionToAdd.setQuestion(questionToAdd);
            questionToAdd.addOption(optionToAdd);
            index++;
        }
        return questionToAdd;
    }

}
