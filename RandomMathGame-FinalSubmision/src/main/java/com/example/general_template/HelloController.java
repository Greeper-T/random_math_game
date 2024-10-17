package com.example.general_template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloController {
    @FXML
    public TextField answer;
    @FXML
    public Label quetion;
    @FXML
    public Label correctOrIncorrect;
    @FXML
    public Label operationLbl;
    @FXML
    public Label numberOfInts;
    @FXML
    public Label difficulty;
    @FXML
    public AnchorPane gameScreen;
    @FXML
    public AnchorPane mainScreen;
    @FXML
    public AnchorPane achivementScreen;
    @FXML
    public ImageView bronzeAddition;
    @FXML
    public ImageView silverAddition;
    @FXML
    public ImageView goldAddition;
    @FXML
    public ImageView bronzeSubtraction;
    @FXML
    public ImageView silverSubtraction;
    @FXML
    public ImageView goldSubtraction;
    @FXML
    public ImageView bronzeMultiplacation;
    @FXML
    public ImageView silverMultiplacation;
    @FXML
    public ImageView goldMultiplacation;
    @FXML
    public ImageView bronzeDivition;
    @FXML
    public ImageView silverDivition;
    @FXML
    public ImageView goldDivition;
    @FXML
    public Label divitionRight;
    @FXML
    public Label multiplacationRight;
    @FXML
    public Label subtractionRight;
    @FXML
    public Label additionRight;

    @FXML
    public void add(ActionEvent actionEvent) {
        operation = "addition";
        operationLbl.setText("current operation: addition");
    }

    public int random(int lowest, int highest){
        highest -= lowest - 1;
        return (int)(Math.random()*highest + lowest);
    }

    public int addition(ArrayList<Integer> number1){
        int ans = 0;
        for (int i=0; i<number1.size(); i++){
            ans += number1.get(i);
        }
        return ans;
    }

    public void sub(ActionEvent actionEvent) {
        operation = "subtraction";
        operationLbl.setText("current operation: subtraction");
    }

    public int subtraction(ArrayList<Integer> number1){
        int ans = number1.get(0);
        for (int i=0; i<number1.size()-1; i++){
            ans -= number1.get(i + 1);
        }
        return ans;
    }

    public void mult(ActionEvent actionEvent) {
        operation = "multiplication";
        operationLbl.setText("current operation: multiplication");
    }

    public int multiplacation(ArrayList<Integer> number1){
        int ans = number1.get(0);
        for (int i=0; i<number1.size() -1; i++){
            ans *= number1.get(i + 1);
        }
        return ans;
    }

    public void checkAnswer(ActionEvent actionEvent) {
        int ans = Integer.parseInt(answer.getText());
        switch (operation) {
            case "addition" -> {
                if (ans == addition(randomNumbers)) {
                    correctOrIncorrect.setText("you are correct");
                    answer.setText("");
                    if (currentDifficulty.equals("hard")){
                        additionCorrect += 1;
                        additionRight.setText("addition answered: " + additionCorrect);
                    }
                    generateQ();
                } else {
                    correctOrIncorrect.setText("wrong :(, try agian");
                }
            }
            case "subtraction" -> {
                if (ans == subtraction(randomNumbers)) {
                    correctOrIncorrect.setText("you are correct");
                    answer.setText("");
                    if (currentDifficulty.equals("hard")){
                        subtractionCorrect += 1;
                        subtractionRight.setText("subtraction answered: " + subtractionCorrect);
                    }
                    generateQ();
                } else {
                    correctOrIncorrect.setText("wrong :(, try again");
                }
            }
            case "division" -> {
                if (ans == randomNumbers.get(0) / randomNumbers.get(1)) {
                    correctOrIncorrect.setText("you are correct");
                    answer.setText("");
                    if (currentDifficulty.equals("hard")){
                        divitionCorrect += 1;
                        divitionRight.setText("division answered: " + divitionCorrect);
                    }
                    generateQ();
                } else {
                    correctOrIncorrect.setText("wrong :(, try again");
                }
            }
            case "multiplication" -> {
                if (ans == multiplacation(randomNumbers)) {
                    correctOrIncorrect.setText("you are correct");
                    answer.setText("");
                    if (currentDifficulty.equals("hard")){
                        multiplacationCorrect += 1;
                        multiplacationRight.setText("multiplication answered: " + multiplacationCorrect);
                    }
                    generateQ();
                } else {
                    correctOrIncorrect.setText("wrong :(, try again");
                }
            }
        }
        checkForAchivements();
    }

    public void generateQuetion(ActionEvent actionEvent) {
        generateQ();
    }

    public void generateQ(){
        StringBuilder quetions = new StringBuilder("what is ");
        while (true){
            randomNumbers.clear();
            for (int i = 0; i<totalNumOfIntigers; i++){
                if (operation.equals("division")){
                    randomNumbers.add(random(1,highestNum*4));
                }else if (operation.equals("multiplication")){
                    randomNumbers.add(random(1,highestNum/2));
                }else{
                    randomNumbers.add(random(1,highestNum));
                }
            }
            if (operation.equals("division") && (randomNumbers.get(0)%randomNumbers.get(1)) == 0){
                break;
            }else if (!operation.equals("division")){
                break;
            }
        }
        quetions.append(randomNumbers.get(0));
        if (operation.equals("division")){
            quetions.append("/").append(randomNumbers.get(1));
        }
        for (int i = 1; i<totalNumOfIntigers; i++){
            switch (operation) {
                case "addition" -> quetions.append("+").append(randomNumbers.get(i));
                case "subtraction" -> quetions.append("-").append(randomNumbers.get(i));
                case "multiplication" -> quetions.append("*").append(randomNumbers.get(i));
            }
        }
        quetions.append("?");
        quetion.setText(quetions.toString());
    }

    public void addOneInt(ActionEvent actionEvent) {
        if (!operation.equals("division")){
            totalNumOfIntigers += 1;
            numberOfInts.setText(String.valueOf(totalNumOfIntigers));
        }
    }

    public void subOneInt(ActionEvent actionEvent) {
        if (totalNumOfIntigers >= 3 && !operation.equals("division")){
            totalNumOfIntigers -= 1;
            numberOfInts.setText(String.valueOf(totalNumOfIntigers));
        }
    }

    public void division(ActionEvent actionEvent) {
        operation = "division";
        operationLbl.setText("current operation: division");
        totalNumOfIntigers = 2;
        numberOfInts.setText(String.valueOf(totalNumOfIntigers));
    }

    public void easy(ActionEvent actionEvent) {
        highestNum = 10;
        difficulty.setText("current difficulty: easy");
        currentDifficulty = "easy";
    }

    public void medium(ActionEvent actionEvent) {
        highestNum = 30;
        difficulty.setText("current difficulty: medium");
        currentDifficulty = "medium";
    }

    public void hard(ActionEvent actionEvent) {
        highestNum = 50;
        difficulty.setText("current difficulty: hard");
        currentDifficulty = "hard";
    }

    public void toGame(ActionEvent actionEvent) {
        mainScreen.setVisible(false);
        mainScreen.setDisable(true);
        gameScreen.setDisable(false);
        gameScreen.setVisible(true);
    }

    public void gameToMain(ActionEvent actionEvent) {
        mainScreen.setVisible(true);
        mainScreen.setDisable(false);
        gameScreen.setDisable(true);
        gameScreen.setVisible(false);
    }

    public void achivementToMain(ActionEvent actionEvent) {
        mainScreen.setVisible(true);
        mainScreen.setDisable(false);
        achivementScreen.setDisable(true);
        achivementScreen.setVisible(false);
    }

    public void toAchivements(ActionEvent actionEvent) {
        mainScreen.setVisible(false);
        mainScreen.setDisable(true);
        achivementScreen.setDisable(false);
        achivementScreen.setVisible(true);
    }

    public void checkForAchivements(){
        try{
            FileInputStream input = new FileInputStream("src/main/resources/picture/bronzeTrophy.jpg");
            if (additionCorrect == 10){
                input = new FileInputStream("src/main/resources/picture/bronzeTrophy.jpg");
                bronzeAddition.setImage(new Image(input));
            }else if (additionCorrect == 50){
                input = new FileInputStream("src/main/resources/picture/silverTrophy.jpg");
                silverAddition.setImage(new Image(input));
            }else if (additionCorrect == 100){
                input = new FileInputStream("src/main/resources/picture/goldTrophy.jpg");
                goldAddition.setImage(new Image(input));
            }
            if (subtractionCorrect == 10){
                input = new FileInputStream("src/main/resources/picture/bronzeTrophy.jpg");
                bronzeSubtraction.setImage(new Image(input));
            }else if (subtractionCorrect == 50){
                input = new FileInputStream("src/main/resources/picture/silverTrophy.jpg");
                silverSubtraction.setImage(new Image(input));
            }else if (subtractionCorrect == 100){
                input = new FileInputStream("src/main/resources/picture/goldTrophy.jpg");
                goldSubtraction.setImage(new Image(input));
            }
            if (multiplacationCorrect == 10){
                input = new FileInputStream("src/main/resources/picture/bronzeTrophy.jpg");
                bronzeMultiplacation.setImage(new Image(input));
            }else if (multiplacationCorrect == 50){
                input = new FileInputStream("src/main/resources/picture/silverTrophy.jpg");
                silverMultiplacation.setImage(new Image(input));
            }else if (multiplacationCorrect == 100){
                input = new FileInputStream("src/main/resources/picture/goldTrophy.jpg");
                goldMultiplacation.setImage(new Image(input));
            }
            if (divitionCorrect == 10){
                input = new FileInputStream("src/main/resources/picture/bronzeTrophy.jpg");
                bronzeDivition.setImage(new Image(input));
            }else if (divitionCorrect == 50){
                input = new FileInputStream("src/main/resources/picture/silverTrophy.jpg");
                silverDivition.setImage(new Image(input));
            }else if (divitionCorrect == 100){
                input = new FileInputStream("src/main/resources/picture/goldTrophy.jpg");
                goldDivition.setImage(new Image(input));
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private int highestNum = 10;
    private int totalNumOfIntigers = 2;
    private String operation = "addition";
    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private int additionCorrect = 50;
    private int subtractionCorrect = Integer.MIN_VALUE;
    private int multiplacationCorrect = 0;
    private int divitionCorrect = Integer.MAX_VALUE;
    public String currentDifficulty = "hard";
}