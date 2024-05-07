package com.example.java02_final_project;

import java.util.Objects;

public class Question {
    private String correctAnswer;
    private String questionText;
    private QuestionType questionType;

    public Question(){
        this.questionText = "";
        this.correctAnswer = "";
    }
    public Question(String questionText, String correctAnswer, QuestionType questionType){
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
        this.questionType = questionType;
    }


    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public boolean checkAnswer(String answer){
        return Objects.equals(this.correctAnswer, answer);
    }
    public String toString(){
        return "Question text: " + getQuestionText() + "\nCorrect answer·: " + getCorrectAnswer() + "\nQuestion type: " + getQuestionType();
    }
}
