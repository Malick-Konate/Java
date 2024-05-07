package com.example.java02_final_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import org.controlsfx.tools.Platform;
import org.junit.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class HelloApplication extends Application {
    private Exam exam;
    private Label labelShowingGrade = new Label("Grade: ");
    private VBox questionVBoxes;
    private VBox root;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            QuestionBank questionBank = new QuestionBank();
            questionBank.readMCQ("C:\\Users\\malic\\IdeaProjects\\Java02_Final_Project\\src\\main\\resources\\mcq.txt");
            questionBank.readTFQ("C:\\Users\\malic\\IdeaProjects\\Java02_Final_Project\\src\\main\\resources\\tfq.txt");
            Random random = new Random();

            int rand[] = new int[10];
            for (int i = 0; i < rand.length; i++){
                rand[i] = random.nextInt(65);
            }
            questionBank.selectRandQuestion(rand);
            this.exam = new Exam(questionBank.selectRandQuestion(rand));

            MenuBar menuBar = buildMenuBar();
            HBox topBanner = buildTopBanner();
            HBox navigationBar = buildNavigationBar();
            VBox topContainer = new VBox(10, menuBar, topBanner);


            BorderPane borderPane = new BorderPane();
            borderPane.setTop(topContainer);
            borderPane.setBottom(navigationBar);
            VBox[] pages = createPage(exam);
            this.root = new VBox();
            for (VBox page : pages)
                this.root.getChildren().add(page);


            this.labelShowingGrade.setStyle("-fx-alignment: center; -fx-font-weight: bold;-fx-font-size: 15pt");

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(this.root);
            borderPane.setCenter(scrollPane);

            Scene scene = new Scene(borderPane, 850, 600);
            stage.setTitle("ChamplainExam");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public VBox[] createPage(Exam exam){
        VBox[] vBox = new VBox[exam.questions.size()];
        int i = 0;
        // Get entries from HashMap
        for (Map.Entry<Integer, Question> value : exam.questions.entrySet()) {
            Question question = value.getValue(); // Get the question object
            if (question.getQuestionType().equals(QuestionType.MCQ)) {
                vBox[i] = buildMCQ(i, (MCQQuestion) question);
            } else if (question.getQuestionType().equals(QuestionType.TFQ)) {
                vBox[i] = buildTrueFalse(i, (TFQQuestion) question);
            }
            i++;
        }
        return vBox;
    }
    public VBox buildTrueFalse(int questionNumber, TFQQuestion tfqQuestion){
        Label label = new Label((questionNumber + 1) +  ": "+ tfqQuestion.getQuestionText());
        CheckBox tfCheckBox1 = new CheckBox("True");
        tfCheckBox1.setOnAction(e -> setQuestionAnswer(questionNumber, "true"));
        CheckBox tfCheckBox2 = new CheckBox("False");
        tfCheckBox2.setOnAction(e -> setQuestionAnswer(questionNumber, "false"));
        VBox vBox = new VBox(label,tfCheckBox1, tfCheckBox2);
        vBox.setPadding(new Insets(10));

        return vBox;
    }
    public VBox buildMCQ(int questionNumber, MCQQuestion mcqQuestion){
        Label label = new Label((questionNumber + 1) +  ": " + mcqQuestion.getQuestionText());
        RadioButton AResponse = new RadioButton("" + ( mcqQuestion.options.get(0)));
        RadioButton BResponse = new RadioButton("" + ( mcqQuestion.options.get(1)));
        RadioButton CResponse = new RadioButton("" + ( mcqQuestion.options.get(2) ));
        RadioButton DResponse = new RadioButton("" + ( mcqQuestion.options.get(3) ));
        ToggleGroup mcqQuestionToggle = new ToggleGroup();
        AResponse.setToggleGroup(mcqQuestionToggle);
        BResponse.setToggleGroup(mcqQuestionToggle);
        CResponse.setToggleGroup(mcqQuestionToggle);
        DResponse.setToggleGroup(mcqQuestionToggle);
        AResponse.setOnAction(e -> setQuestionAnswer(questionNumber, "a"));
        BResponse.setOnAction(e -> setQuestionAnswer(questionNumber, "b"));
        CResponse.setOnAction(e -> setQuestionAnswer(questionNumber, "c"));
        DResponse.setOnAction(e -> setQuestionAnswer(questionNumber, "d"));
        VBox vBox = new VBox(label,AResponse, BResponse, CResponse, DResponse);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
    private void setQuestionAnswer(int questionNumber, String aTrue) {
        if (this.exam.submittedAnswers == null) {
            this.exam.submittedAnswers = new HashMap<>(); // Initialize if null (or empty)
        }
        this.exam.submittedAnswers.put(questionNumber, aTrue);
    }

    public MenuBar buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu quizMenu = new Menu("Quiz");
        Menu extraMenu = new Menu("Extras");
        Menu helpMenu = new Menu("Help");
        // Add menu items to each menu
        fileMenu.getItems().addAll(new MenuItem("New"){
            {
                setOnAction(e -> handleNew());
            }
        }, new MenuItem("Open"){
            {
                setOnAction(e -> handleOpen());
            }
        }, new MenuItem("Save"){
            {
                setOnAction(e -> handleSave());
            }
        }, new MenuItem("Exit"){
            {
                setOnAction(e -> handleExit());
            }
        });

        editMenu.getItems().addAll(new MenuItem("Cut"){
        }, new MenuItem("Copy"){
            {
                setOnAction(e -> handleCopy());
            }
        }, new MenuItem("Paste"){
            {
                setOnAction(e -> handlePaste());
            }
        }, new MenuItem("Delete"){
            {
                setOnAction(e -> HandleDelete());
            }
        });
        quizMenu.getItems().addAll(new MenuItem("Start Quiz"){
            {
                setOnAction(e -> handleStartQuiz());
            }
        }, new MenuItem("View Results"){
            {
                setOnAction(e -> handleViewResults());
            }
        });
        extraMenu.getItems().addAll(new MenuItem("Setting"){
            {
                setOnAction(e -> handleSetting());
            }
        }, new MenuItem("About"){
            {
                setOnAction(e -> handleAbout());
            }
        });
        helpMenu.getItems().addAll(new MenuItem("About"){
            {
                setOnAction(e -> handleAbout());
            }
        }, new MenuItem("Help Topics"){
            {
                setOnAction(e -> handleHelpTopics());
            }
        });
        menuBar.getMenus().addAll(fileMenu, editMenu, quizMenu, extraMenu, helpMenu);
        menuBar.setStyle("-fx-alignment: center;-fx-font-weight: bold; -fx-background-color: #c4bcbc");
        return menuBar;
    }
    private void handleNew() {
    }
    private void handleHelpTopics() {
    }
    private void handleAbout() {
    }
    private void handleSetting() {
    }
    private void handleViewResults() {
    }
    private void handleStartQuiz() {
    }
    private void handlePaste() {
    }
    private void handleCopy() {
    }
    private void handleSave() {
    }
    private void handleOpen() {
    }
    private void HandleDelete() {
    }
    private void handleExit() {
        System.exit(0);
    }
    public HBox buildNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.setSpacing(10);
        navigationBar.setStyle("-fx-background-color: #336699; -fx-padding: 10;-fx-font-weight: bold");
        // Create navigation button
        Button prevButton = new Button("Clear");
        prevButton.setOnAction(e -> clearExamAnswers());
        Button nextButton = new Button("Save");
        nextButton.setOnAction(e -> saveExamAnswers());
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new SubmitButtonHandler());
        navigationBar.getChildren().addAll(prevButton, nextButton, submitButton);
        //set the tree buttons at the center
        navigationBar.setAlignment(Pos.CENTER);
        return navigationBar;
    }
    public HBox buildTopBanner() {
        Image image = new Image("C:\\Users\\malic\\IdeaProjects\\Java02_Final_Project\\src\\main\\resources\\banner.png");
        ImageView TitleView = new ImageView(image);
        image = new Image("C:\\Users\\malic\\IdeaProjects\\Java02_Final_Project\\src\\main\\resources\\_konate_software_and_game_studio.jpeg");
        ImageView LogoView = new ImageView(image);
        TitleView.setFitWidth(500);
        TitleView.setPreserveRatio(true);
        LogoView.setFitWidth(300);
        LogoView.setPreserveRatio(true);
        HBox topBanner = new HBox(10, LogoView, TitleView);
        VBox vBox = new VBox(10,topBanner, this.labelShowingGrade);
        HBox hBox = new HBox(vBox);
        topBanner.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }
    class SubmitButtonHandler implements EventHandler<ActionEvent>{
        double point = 0.0;

        @Override
        public void handle(ActionEvent actionEvent) {
            point  = 0.0;

            for (Map.Entry<Integer, String> entry : exam.submittedAnswers.entrySet()) {
                int questionNumber = entry.getKey();
                String submittedAnswer = entry.getValue();

                // Check if the question number exists in exam.questions
                if (exam.questions.containsKey(questionNumber)) {
                    String correctAnswer = exam.questions.get(questionNumber).getCorrectAnswer();
                    if (submittedAnswer.equals(correctAnswer)) {
                        point += 1.0;
                    }
                } else {
                    System.out.println("Question number " + questionNumber + " does not exist.");
                }
            }
            labelShowingGrade.setText("Grade(in %): " + (point/ exam.submittedAnswers.size()*100));


        }
    }

    private void saveExamAnswers() {
    }
    private void clearExamAnswers() {

        this.exam.submittedAnswers.clear();
    }
    public static void main(String[] args) throws IOException {
        launch();
    }
}