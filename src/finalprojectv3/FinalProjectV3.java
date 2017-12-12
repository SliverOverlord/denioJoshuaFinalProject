/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectv3;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Turtle
 */
public class FinalProjectV3 extends Application {
    public Stage stage;
    public Scene scene1 , scene2, scene3;
    private VBox lastBox;
    public GridPane grid2;
    private static TextField userT;
    private static TextField passT; 
    private AnchorPane aPane;
    
    private static TextField fName ;
    private static TextField lName ;
    private static TextField state ;
    private static ChoiceBox numQuestions;
    private Button browse;
    private Button start;
    private TableView scoreTable;
    private TableColumn fNCol;
    private TableColumn lNCol;
    private TableColumn stateCol;
    private TableColumn numCol;
    private TableColumn scoreCol;
    private TextArea questionText1;
    private TextArea questionText2;
    private TextArea questionText3;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;

    private RadioButton option4;

    private static TextField entry;
 
    private Button submitBtn;
 
    private Label firstNLabel;
 
    private Label lastNLabel;
    
    private Label stateLabel;
 
    private Label scoreLabel;
  
    private Button loadQuestionBtn;
   
    private Button btnSave;
 
    ToggleGroup buttonGroup = new ToggleGroup();
    
    private ArrayList txtList;
    private ArrayList scoreList;
    private static String infoStr;
    
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    private FileChooser fileChooser;
    private final QuestionQueries questionQueries = new QuestionQueries();
    private static ObservableList<Question> questionList = FXCollections.observableArrayList();
    private static ObservableList<Question> questionList2 = FXCollections.observableArrayList();
    private static int maxSize = 1;
    
    private static int currentIndex = 0;
    private static int gradeCounter = 0;
    private static String finalGrade = "";
    private static ObservableList<Integer> keyList = FXCollections.observableArrayList();
    private static String firstNameStr;
    private static String lastNameStr;
    private static String stateStr;
    private static String scoreStr;
  
    private static String questionType;
    
    private static String solution;
    
    public static Question tmpQuestionOb;
    
    public GridPane grid;
    @Override
    public void start(Stage primaryStage) {
        
        
        stage = primaryStage;
        scene1 = new Scene(new Group());
        Parent root;          
//        stage =(Stage) start.getScene().getWindow();
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(12);
        grid.setHgap(12);
        grid.setPadding(new Insets(10));
        
        scoreTable= new TableView();
        scoreTable.setEditable(true);
        
        fNCol = new TableColumn("First Name");
        lNCol = new TableColumn("Last Name");
        stateCol = new TableColumn("Salary");
        numCol = new TableColumn("NUmber Of Questions");
        scoreCol = new TableColumn("Score");
        
        fNCol.setCellValueFactory(new PropertyValueFactory<Score,String>("firstN"));
        lNCol.setCellValueFactory(new PropertyValueFactory<Score,String>("LastN"));
        stateCol.setCellValueFactory(new PropertyValueFactory<Score,String>("StateH"));
        numCol.setCellValueFactory(new PropertyValueFactory<Score,String>("numSelected"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<Score,String>("scoreSubmitted"));
        
        
        
        //scoreTable.setItems(empList);
        scoreTable.getColumns().addAll(fNCol, lNCol, stateCol, numCol, scoreCol);

        Text welcomeTxt = new Text("Joshua's Trivia Quiz");
        welcomeTxt.setFont(Font.font("TimesRoman",FontWeight.LIGHT,15));
        welcomeTxt.setFill(Color.BLUE);
        grid.add(welcomeTxt,0,0);
                        
        Label fNameLabel = new Label("First Name");
        grid.add(fNameLabel,0,1);
        
        fName = new TextField();
        fName.setPromptText("First Name");
        grid.add(fName,1,1);
        
        Label lNameLabel = new Label("Last Name");
        grid.add(lNameLabel,0,2);
        
        lName =  new TextField();
        lName.setPromptText("Last Name");
        grid.add(lName,1,2);
        
        Label salary = new Label("State");
        grid.add(salary,0,3);
        
        state =  new TextField();
        state.setPromptText("State");
        grid.add(state,1,3);
       
        //Buttons
        browse = new Button("Load Record");
        grid.add(browse,1,4);
        browse.setDefaultButton(true);
        browse.setOnAction(a-> search(a));
    
        start = new Button("Start");
        grid.add(start,3,4);
        start.setOnAction(b->{
            try {
                startAction(b);
            } catch (IOException ex) {
                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        numQuestions = new ChoiceBox();
        grid.add(numQuestions,2,4);
        
    
//        Button lBtn3 = new Button("Print");
//        empWindow.add(lBtn3,3,4);
//        lBtn3.setOnAction(d->printEmp(d));
    

            
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(grid, scoreTable);
 
        ((Group) scene1.getRoot()).getChildren().addAll(vbox);        
        //Change the scene.

        stage.setScene(scene1);
        stage.show();
        
//        scene1 = new Scene(vbox,600,500);
//        stage.setTitle("Trivia Quiz");
//        stage.setScene(scene1);
//        stage.show();
//        
        try{
        
        
        txtList = new ArrayList<String>();
        scoreList = new ArrayList<String>();
        
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll
                (
                new FileChooser.ExtensionFilter("Text Files", "*txt")
               );
        numQuestions.getItems().addAll(10);
        numQuestions.getItems().addAll(20);
        }
        catch(Exception e){
            
        }
    
                        //Set fields.
//                        VBox box = new VBox();
//                        box.getChildren().addAll(option1, option2);
//                        grid.add(box,0,1);
//        
//                        
//                        //Buttons
//        
//                        Button lBtn3 = new Button("Submit");
//                        grid.add(lBtn3,3,4);
//                        
//                 
//                        lBtn3.setOnAction( new EventHandler<ActionEvent> (){
//                            @Override
//                            public void handle(ActionEvent event){
//                                try {
//                                    submit2();
//                                } catch (Exception ex) {
//                                    Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                
//                            }});
//    
//
//            
//                        Scene scene2;   
//                        //Change the scene.
//                        scene2 = new Scene(grid,600,500);
//                        stage.setTitle("Joshua's Trivia Quiz");
//                        stage.setScene(scene2);
//                        stage.show();
//        
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
    
    private void search(ActionEvent a) 
        {
        
            
            Stage stage;
            stage = new Stage();
            Parent root;
            file = fileChooser.showOpenDialog(stage);
            if (file == null)
                error();
            if (file != null)
            {
            try{
                //desktop.open(file);
                //save data to an arrayList.
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line != null)
                {
                        System.out.print(" : " + line);
                        txtList.add(line);
                        String[] items = line.split(" ");
                        
                        List<String> itemList = new ArrayList<String>(Arrays.asList(items));
                        Score tmp = new Score(itemList.get(0), itemList.get(1), itemList.get(2), itemList.get(3), itemList.get(4));
                        System.out.println(itemList);
                        scoreTable.getItems().addAll(tmp);
                        
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                //hb.getChildren().addAll(lblMsg, newText);
                for (int i=0; i<txtList.size(); i++){
                    //scoreList = txtList.get(i).split("");
            }}
            catch (IOException ex) {
                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            }
        }
    public void error()
    {
    //set up the alert.
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText("File not found.");
    alert.show();
    }
    private void setEntries(){
        
        questionList.setAll(questionQueries.getAllQuestions());
        //System.out.println(questionList);
  
    }
    
    private void startAction(ActionEvent b) throws IOException 
        {
         
           
        try{
            firstNameStr = fName.getText();
            lastNameStr = lName.getText();
            stateStr = state.getText();
            infoStr = fName.getText()+ " "+ lName.getText()+ " "+state.getText()+
                    " "+ numQuestions.getSelectionModel().getSelectedItem();
            System.out.println(infoStr);
            
            String tmp = numQuestions.getSelectionModel().getSelectedItem().toString();
            //System.out.println(tmp);
            setEntries();
            if ("10".equals(tmp)){
                maxSize = 10;
                for (int i = 1; i < 11; i++){
                    Random rand = new Random();
                    int  n = rand.nextInt(20) + 1;
                    //System.out.println(n);
                    keyList.add(n);
                    String num = Integer.toString(n);
                    for (int inde=0; inde<questionList.size(); inde++){
                        
                        Question tmpQuestion = questionList.get(inde);
                        String tmpkeyID = Integer.toString(tmpQuestion.getKeyID());
                        if ( num.equals(tmpkeyID)){ 
                        questionList2.add(questionList.get(inde));
                        }
                    }
                }
                
                loadNext();    
            }
            else if ("20".equals(tmp)){
                maxSize = 20;
                for (int i = 1; i < 21; i++){
                    Random rand = new Random();
                    int  n = rand.nextInt(20) + 1;
                    //System.out.println(n);
                    keyList.add(n);
                    String num = Integer.toString(n);
                    for (int inde=0; inde<questionList.size(); inde++){
                        
                        Question tmpQuestion = questionList.get(inde);
                        String tmpkeyID = Integer.toString(tmpQuestion.getKeyID());
                        if ( num.equals(tmpkeyID)){ 
                        questionList2.add(questionList.get(inde));
                        }
                    }
                }
                loadNext();    
            }
            
        }
        catch(Exception e){
            error2();
        }
        }
    public void error2()
    {
    //set up the alert.
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error");
    alert.setContentText("Please enter valid input and select a quiz size.");
    alert.show();
    }
    @FXML
    private void submit(ActionEvent c) throws IOException{
        System.out.println(">>>>>>>>>>>>");
        System.out.println(questionType);
        System.out.println(gradeCounter);
   
        try{
            if (questionType.equals("3")){
                String userInput = entry.getText().toLowerCase();
                if (solution.equals(userInput)){
                    gradeCounter = gradeCounter + 1;
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Correct", outPutStr);
                }
                else{
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Incorrect", outPutStr);
                }
            }
            
            else if (questionType.equals("2")){
                
                String userInput = buttonGroup.getSelectedToggle().getUserData().toString();
                if (solution.equals(userInput)){
                    gradeCounter = gradeCounter + 1;
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Correct", outPutStr);
                }
                else{
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Incorrect", outPutStr);
                    
                }
            }
            else if (questionType.equals("1")){
                String userInput = buttonGroup.getSelectedToggle().getUserData().toString();
                if (solution.equals(userInput)){
                    gradeCounter = gradeCounter + 1;
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Correct", outPutStr);
                }
                else{
                    String outPutStr = "The Answer is : "+solution;
                    displayAlert(INFORMATION, "Incorrect", outPutStr);
                }
            }
        }
        catch(Exception ex){
                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
                }
        System.out.println(gradeCounter);
        loadNext();
    }
    
    private void loadNext() throws IOException{
//        System.out.println(keyList);
//        System.out.println(maxSize);
//        System.out.println(questionList2);
        try{
        if(currentIndex < maxSize){
            //System.out.println(maxSize);
            //gradeQuiz();
            for (int ind=0; ind<questionList2.size(); ind++)
            {
                String ind2 = Integer.toString(ind);
                
                if (ind2.equals(Integer.toString(currentIndex))){
                    Question tmpQuestion = questionList2.get(ind);
                    System.out.println(tmpQuestion);
                    String tmpType = Integer.toString(tmpQuestion.getTypeID());
                    System.out.println(tmpType);
                    if (tmpType.equals("1")){
                        questionType = "1";
                        solution = tmpQuestion.getSolutionStr();
                        System.out.println(solution);
                        System.out.println(questionType);
                        questionText1 = new TextArea();
                        tmpQuestionOb = tmpQuestion;
                        option1 = new RadioButton(tmpQuestion.getOption1());
                        option2 = new RadioButton(tmpQuestion.getOption2());
                        option3 = new RadioButton(tmpQuestion.getOption3());
                        option4 = new RadioButton(tmpQuestion.getOption4());
                        
                        questionText1.setText(tmpQuestion.getQuestionStr());
                        String newTmp3 = questionText1.getText();
                        
                        
                        System.out.println(newTmp3);
                        System.out.println(option1.getText());
                        System.out.println(option2.getText());
                        System.out.println(option3.getText());
                        System.out.println(option4.getText());
                        
                        option1.setUserData(tmpQuestion.getOption1());
                        option2.setUserData(tmpQuestion.getOption2());
                        option3.setUserData(tmpQuestion.getOption3());
                        option4.setUserData(tmpQuestion.getOption4());
                        
//                        System.out.println("Testing input for userData");
//                        System.out.println(option1.getUserData());
//                        System.out.println(option2.getUserData());
//                        System.out.println(option3.getUserData());
//                        System.out.println(option4.getUserData());
                        
                        option1.setToggleGroup(buttonGroup);
                        option2.setToggleGroup(buttonGroup);
                        option3.setToggleGroup(buttonGroup);
                        option4.setToggleGroup(buttonGroup);
                        
                        buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                        @Override
                        public void changed(ObservableValue<? extends Toggle>ov, Toggle old_toggle,Toggle new_toggle){
                        if(buttonGroup.getSelectedToggle()!= null){
                        String tmp;
                        tmp = buttonGroup.getSelectedToggle().getUserData().toString();   
                        }      
                        }});
                        
//                        FXMLLoader loader = new FXMLLoader(
//                        getClass().getResource("QuizMC.fxml")
//                        );
//                        loader.setController(this);
//                        Parent root = (Parent) loader.load();
                        
                        //Stage stage;
//                        try{       
//                        stage =(Stage) start.getScene().getWindow();
//                        }
//                        catch(Exception ex){
//                            stage =(Stage) submitBtn.getScene().getWindow();
//                        }


                        //FXMLLoader loader=new FXMLLoader(getClass().getResource("QuizMC.fxml"));
                       // loader.setController(this);
                       // Parent root=(Parent) loader.load();
                       
                       
//                        Parent root = FXMLLoader.load(getClass().getResource("QuizMC.fxml"));
//                        System.out.println("Switching Scene"); 
                        
                        //setPrimaryStage(window);
                        
  //                      Scene scene = new Scene(root);
                        //scene.getChildren().addAll(buttonGroup, questionText1 );
//                        stage.setScene(scene);
//                        
//                        String newString = tmpQuestion.getQuestionStr();
//                        System.out.println(newString);
//                        System.out.println(questionType);
//                        System.out.println(">>>>>>>>>>");

                        grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setVgap(12);
                        grid.setHgap(12);
                        grid.setPadding(new Insets(10));
        
                        Text welcomeTxt = new Text(tmpQuestion.getQuestionStr());
                        welcomeTxt.setFont(Font.font("TimesRoman",FontWeight.LIGHT,15));
                        welcomeTxt.setFill(Color.BLUE);
                        grid.add(welcomeTxt,0,0);
                        //Set fields.
                        VBox box = new VBox();
                        box.getChildren().addAll(option1, option2, option3, option4);
                        grid.add(box,0,1);
        
                        
                        //Buttons
        
                        submitBtn = new Button("Submit");
                        grid.add(submitBtn,3,4);
                        
                        
                        //Change the scene.
                        scene2 = new Scene(grid,600,500);
                        stage.setTitle("Joshua's Trivia Quiz");
                        stage.setScene(scene2);
                        stage.show();
                        
                        submitBtn.setOnAction(c->{
                            try {
                                System.out.println("------------------------------");
                                submit(c);
                            } catch (IOException ex) {
                                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            });
                        
                       
                    }
                    else if (tmpType.equals("2")){
                        questionType = "2";
                        System.out.println(questionType);
                        solution = tmpQuestion.getSolutionStr();
                        System.out.println(solution);
                        
                        questionText2 = new TextArea();
                        option1 = new RadioButton(tmpQuestion.getOption1());
                        option2 = new RadioButton(tmpQuestion.getOption2());                      
                        questionText2.setText(tmpQuestion.getQuestionStr());
                        System.out.println("Testing input for Option text");
                        System.out.println(option1.getText());
                        System.out.println(option2.getText());
                        
                        option1.setUserData(tmpQuestion.getOption1());
                        option2.setUserData(tmpQuestion.getOption2());
                        
//                        System.out.println("Testing input for userData");
//                        System.out.println(option1.getUserData());
//                        System.out.println(option2.getUserData());
                        option1.setToggleGroup(buttonGroup);
                        option2.setToggleGroup(buttonGroup);
                        
                        buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                        @Override
                        public void changed(ObservableValue<? extends Toggle>ov, Toggle old_toggle,Toggle new_toggle){
                        if(buttonGroup.getSelectedToggle()!= null){
                        String tmp;
                        tmp = buttonGroup.getSelectedToggle().getUserData().toString();   
                        }      
                        }});
                        
//                        FXMLLoader loader = new FXMLLoader(
//                        getClass().getResource("Quiz.fxml")
//                        );
//                        loader.setController(this);
//                        Parent root = (Parent) loader.load();
                        
//
//                        FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(getClass().getResource("Quiz.fxml"));
//                        try{
//                            loader.load();
//                        }
//                        catch(Exception ex){
//                            
//                        }
//                        QuizController tmpController = loader.getController();
//                        tmpController.setEntries(buttonGroup);
//                        
//                        Parent root; 
//                        
//                        try{       
//                        stage =(Stage) start.getScene().getWindow();
//                        }
//                        catch(Exception ex){
//                            stage =(Stage) submitBtn.getScene().getWindow();
//                        }
//                        //stage =(Stage) start.getScene().getWindow();
//                        root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
//                        System.out.println("Switching Scene"); 
//                        Scene scene = new Scene(root);
//                        //scene.getChildren().addAll(buttonGroup);
//                        //scene = scene(buttonGroup);
//                        //setPrimaryStage(window);
//                        
//                        stage.setScene(scene);
//                        System.out.println(tmpQuestion.getQuestionStr());
//                        
//                        buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//                        @Override
//                        public void changed(ObservableValue<? extends Toggle>ov, Toggle old_toggle,Toggle new_toggle){
//                        if(buttonGroup.getSelectedToggle()!= null){
//                        String tmp;
//                        tmp = buttonGroup.getSelectedToggle().getUserData().toString();   
//                    }      
//                }});  
                       

                        
                        //Parent root;          
                       // stage =(Stage) start.getScene().getWindow();
                       
                        grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setVgap(12);
                        grid.setHgap(12);
                        grid.setPadding(new Insets(10));
        
                        Text welcomeTxt = new Text(tmpQuestion.getQuestionStr());
                        welcomeTxt.setFont(Font.font("TimesRoman",FontWeight.LIGHT,15));
                        welcomeTxt.setFill(Color.BLUE);
                        grid.add(welcomeTxt,0,0);
                        //Set fields.
                        VBox box = new VBox();
                        box.getChildren().addAll(option1, option2);
                        grid.add(box,0,1);
        
                        
                        //Buttons
        
                        submitBtn = new Button("Submit");
                        grid.add(submitBtn,3,4);
                        
                        
                        
                        
                        //Change the scene.
                        scene2 = new Scene(grid,600,500);
                        stage.setTitle("Joshua's Trivia Quiz");
                        stage.setScene(scene2);
                        stage.show();
                        
                        submitBtn.setOnAction(c->{
                            try {
                                System.out.println("------------------------------");
                                submit(c);
                            } catch (IOException ex) {
                                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            });
                        
                        
                                
                        
                    }
                    else{
                        questionType = "3";
                        solution = tmpQuestion.getSolutionStr();
                        System.out.println(solution);
                        
//                        questionText3 = new TextArea();
//                        entry = new TextField();
//                        questionText3.setText(tmpQuestion.getQuestionStr());
                        
                        grid = new GridPane();
                        grid.setAlignment(Pos.CENTER);
                        grid.setVgap(12);
                        grid.setHgap(12);
                        grid.setPadding(new Insets(10));
        
                        Text welcomeTxt = new Text(tmpQuestion.getQuestionStr());
                        welcomeTxt.setFont(Font.font("TimesRoman",FontWeight.LIGHT,15));
                        welcomeTxt.setFill(Color.BLUE);
                        grid.add(welcomeTxt,0,0);
                        //Set fields.
                        VBox box = new VBox();
                        
                        entry = new TextField();
                        box.getChildren().addAll(entry);
                        grid.add(box,0,1);
        
                        
                        //Buttons
        
                        submitBtn = new Button("Submit");
                        grid.add(submitBtn,3,4);
                        
                        
                        
                        
                        //Change the scene.
                        scene2 = new Scene(grid,600,500);
                        stage.setTitle("Joshua's Trivia Quiz");
                        stage.setScene(scene2);
                        stage.show();
                        
                        submitBtn.setOnAction(c->{
                            try {
                                System.out.println("------------------------------");
                                submit(c);
                            } catch (IOException ex) {
                                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            });
                        
//                        FXMLLoader loader = new FXMLLoader(
//                        getClass().getResource("QuizSA.fxml")
//                        );
//                        loader.setController(this);
//                        Parent root = (Parent) loader.load();
//                        
                        
//                        Parent root;   
//                        try{       
//                        stage =(Stage) start.getScene().getWindow();
//                        }
//                        catch(Exception ex){
//                            stage =(Stage) submitBtn.getScene().getWindow();
//                        }
                        //stage =(Stage) start.getScene().getWindow();
//                        root = FXMLLoader.load(getClass().getResource("QuizSA.fxml"));
//                        
//                        System.out.println("Switching Scene"); 
//                        Scene scene = new Scene(root);
//                        scene.setUserData(maxSize);
//                        stage.setScene(scene);
                        
                        System.out.println(tmpQuestion.getQuestionStr());
                        
                    }
                  
                    
                    
                }
            
            }
            currentIndex += 1;
        }
        if(currentIndex >= maxSize){
            gradeQuiz();
        }
        }
        catch(Exception ex){
            Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
//    private void submit2() throws IOException{
//        try{
//            if (questionType.equals("3")){
//                String userInput = entry.getText().toLowerCase();
//                if (solution.equals(userInput)){
//                    gradeCounter = gradeCounter + 1;
//                }
//            }
//            
//            else if (questionType.equals("2")){
//                String userInput = buttonGroup.getSelectedToggle().getUserData().toString();
//                if (solution.equals(userInput)){
//                    gradeCounter = gradeCounter + 1;
//                }
//            }
//            else if (questionType.equals("2")){
//                String userInput = buttonGroup.getSelectedToggle().getUserData().toString();
//                if (solution.equals(userInput)){
//                    gradeCounter = gradeCounter + 1;
//                }
//            }
//        }
//        catch(Exception ex){
//                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        loadNext();
//    }
    private void gradeQuiz() throws IOException{
        try{
            
            grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER);
            grid2.setVgap(12);
            grid2.setHgap(12);
            grid2.setPadding(new Insets(10));
        firstNLabel = new Label();
        lastNLabel = new Label();
        scoreLabel = new Label();
        stateLabel = new Label();
        
        double tmpMax = maxSize;
        double gradeCounter2 = gradeCounter;
        double tmpScore = gradeCounter2/tmpMax;
        System.out.println(gradeCounter);
        System.out.println(maxSize);
        System.out.println(tmpScore);
        System.out.println("---========");
        double tmpScore2 = tmpScore*100;
        String tmpScore3 = Double.toString(tmpScore2);
        System.out.println(tmpScore3);
        //int tmpScore4 = Integer.parseInt(tmpScore3);
        //String tmpScore5 = Integer.toString(tmpScore4);
        scoreLabel.setText(tmpScore3);
        firstNLabel.setText(firstNameStr);
        lastNLabel.setText(lastNameStr);
        stateLabel.setText(stateStr);
        scoreStr = tmpScore3;
        
        Text welcomeTxt = new Text("Quiz Score");
        welcomeTxt.setFont(Font.font("TimesRoman",FontWeight.LIGHT,15));
        welcomeTxt.setFill(Color.BLUE);
        grid2.add(welcomeTxt,0,0);
      
        grid2.add(firstNLabel,0,1);
      
        grid2.add(lastNLabel,1,1);      
        
        grid2.add(stateLabel,0,2);
        
        
        grid2.add(scoreLabel,1,2);
        
     
        //Buttons
        btnSave = new Button("Save Record");
        grid2.add(btnSave,1,4);
        btnSave.setDefaultButton(true);
        
    
        
        
        
        
        
    
//        Button lBtn3 = new Button("Print");
//        empWindow.add(lBtn3,3,4);
//        lBtn3.setOnAction(d->printEmp(d));
    

            
        lastBox = new VBox();
        lastBox.setSpacing(5);
        lastBox.setPadding(new Insets(10, 0, 0, 10));
        lastBox.getChildren().addAll(grid);
 
        //((Group) scene3.getRoot()).getChildren().addAll(lastBox);        
        //Change the scene.
        scene3 = new Scene(grid2,600,500);
        stage.setTitle("Joshua's Trivia Quiz");
        stage.setScene(scene3);
        stage.show();
        
        btnSave.setOnAction(e->{
            try {
                saveFile(e);
            } catch (Exception ex) {
                Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
//        stage.setScene(scene3);
//        stage.show();
//        Stage stage;
//        Parent root; 
//        
//        try{       
//                        stage =(Stage) start.getScene().getWindow();
//                        }
//                        catch(Exception ex){
//                            stage =(Stage) submitBtn.getScene().getWindow();
//                        }
//        //stage =(Stage) start.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("ScoreScreen.fxml"));
//        System.out.println("Switching Scene"); 
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        }
        catch(Exception ex){
            Logger.getLogger(FinalProjectV3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void displayAlert(Alert.AlertType type, String title, String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void saveFile(ActionEvent e){
        btnSave = new Button("Save File");
        try
            {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            //fileWriter = new FileWriter(file);
            String tmpInfo = infoStr+" "+scoreStr;
            bWriter.write( infoStr);
            bWriter.newLine();
            
            //fileWriter.write(txtList.toString());
            bWriter.close();
            con();
            }
        catch(Exception ex)
        {
             
        }
    }
    public void con()
    {
    //set up the alert.
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Confirmation");
    alert.setContentText("Score added to the list.");
    alert.show();
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
