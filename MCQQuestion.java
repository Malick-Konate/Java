package com.example.java02_final_project;

import java.util.LinkedList;

public class MCQQuestion extends Question{
    LinkedList<String> options;

    public MCQQuestion(LinkedList<String> AnswerOption){
        super("", "", QuestionType.MCQ);
        this.options = new LinkedList<>();
        this.options.addAll(AnswerOption);
    }
    public MCQQuestion(QuestionType questionType, String questionText, String correctAnswer, LinkedList<String> option){
        super(questionText, correctAnswer, QuestionType.MCQ);
        this.options = new LinkedList<>();
        this.options.addAll(option);
    }
    public  MCQQuestion(){
        super("", "", QuestionType.MCQ);
        this.options = new LinkedList<>();
    }

    public LinkedList<String> getOptions() {
        return options;
    }

    public void setOptions(LinkedList<String> options) {
        this.options = options;
    }
}
