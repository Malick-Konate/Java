package com.example.java02_final_project;

import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Exam {
    protected HashMap <Integer, Question> questions;
    protected HashMap <Integer, String> submittedAnswers;

    public Exam(HashMap<Integer, Question> questions, HashMap <Integer, String> submittedAnswers){
        this.questions = new HashMap<>();
        this.submittedAnswers = new HashMap<>();
        this.questions.putAll(questions);
        this.submittedAnswers.putAll(submittedAnswers);

    }
    public Exam(LinkedList <Question> qList){
        this.questions = new HashMap<>();
        for (int i = 0; i < qList.size(); i++){
            this.questions.put(i , qList.get(i));
        }
    }
    public Exam(){
        this.submittedAnswers = new HashMap<Integer, String>();
        this.questions = new HashMap<Integer, Question>();
    }

    public Question getQuestions(int i) {

        return questions.get(i);
    }

    public String getSubmittedAnswers(int i) {


        return submittedAnswers.get(i);
    }
    public void clearQuestions(){
        questions.clear();

    }
    public void printAllQuestion(){
        for (Map.Entry<Integer, Question> entry : questions.entrySet()) {
            Integer key = entry.getKey();
            Question value = entry.getValue();
            System.out.println("Question " + key + ": " + value);

        }
    }
}
