package com.example.java02_final_project;

import java.util.LinkedList;

public class TFQQuestion extends Question{

    public TFQQuestion(QuestionType questionType, String questionText, String correctAnswer){
        super(questionText, correctAnswer, QuestionType.TFQ);
    }
    public  TFQQuestion(){
        super("", "", QuestionType.TFQ);
    }
}
