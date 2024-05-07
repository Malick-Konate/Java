package com.example.java02_final_project;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class QuestionBank {
    protected LinkedList<Question> questions;
    public QuestionBank(LinkedList<Question> questions){
        for (int i = 0; i < questions.size(); i++)
            this.questions.add(i, questions.get(i));

    }
    public QuestionBank(){
        this.questions = new LinkedList<Question>();
    }

    public LinkedList<Question> selectRandQuestion(int indices[]){
        LinkedList<Question> selection = new LinkedList<Question>();

        for (int i: indices)
            selection.add(questions.get(i));

        return selection;
    }

    public Question getQuestions(int i) {
//        if (i > 0 && i < this.questions.size())
            return questions.get(i);
//        else
//            return 0;
    }
    public void readTFQ(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner read = new Scanner(file);
        String question;
        String questionWithNumber;
        String answer;
        String spl[];
        String splQuestion[];
        String rightOne;

        while (read.hasNext()){
            questionWithNumber = read.nextLine();
            splQuestion = questionWithNumber.split("\\.");
            question = splQuestion[1];
            if (splQuestion.length > 2)
                question = splQuestion[1] + "." + splQuestion[2];
            answer = read.nextLine();
            spl = answer.split(" ");
            rightOne = spl[1];
            questions.add(new TFQQuestion(QuestionType.TFQ, question, rightOne));

        }
    }
    public void readMCQ(String fName)throws IOException {
        File file = new File(fName);
        Scanner read = new Scanner(file);
        String questionWithNumber;
        String question;
        String answerA;
        String answerB;
        String answerC;
        String answerD;
        String goodAnswer, letterGood;
        String spl[];
        String splQuestion[];
        LinkedList<String> list = new LinkedList<String>();

        while (read.hasNext()){
            list.clear();
            questionWithNumber = read.nextLine();
            splQuestion = questionWithNumber.split("\\.");
            question = splQuestion[1];
            if (splQuestion.length > 2)
                question = splQuestion[1] + "." + splQuestion[2];
//            question = read.nextLine();
            answerA = read.nextLine();
            answerB = read.nextLine();
            answerC = read.nextLine();
            answerD = read.nextLine();
            goodAnswer = read.nextLine();
            spl = goodAnswer.split(" ");
            letterGood = spl[1];
            list.add(answerA);
            list.add(answerB);
            list.add(answerC);
            list.add(answerD);

            questions.add(new MCQQuestion(QuestionType.MCQ,question, letterGood, list));

            System.out.println();
        }
    }
    public void printAllQuestion(){
        for (Question display: this.questions) {
            Label label = new Label(display.toString());
            System.out.println(display);
        }
    }


}
