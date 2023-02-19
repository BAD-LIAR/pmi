package ru.itis.les1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class HelloApplication extends Application {

    public static Stage mainStage;

    private final List<String> urls = Arrays.asList("C:\\Study\\pmi\\les1\\src/main/resources/ru/itis/les1/apple.png",
            "C:\\Study\\pmi\\les1\\src/main/resources/ru/itis/les1/lemon.png",
            "C:\\Study\\pmi\\les1\\src/main/resources/ru/itis/les1/strawberry.png");

    private Set<Integer> set;

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        stage.setTitle("Hello!");
        stage.setScene(getMainScene());
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    private Scene getMainScene() {
        Button button = new Button("Start");
        button.setLayoutX(200);
        button.setDefaultButton(true);
        button.setMaxSize(50, 25);
        button.setMinSize(50, 25);
        TextField textField = new TextField();
        button.setOnAction(actionEvent -> start(Integer.parseInt(textField.getText())));
        Group group = new Group();
        group.getChildren().add(button);
        group.getChildren().add(textField);
        return new Scene(group, 800, 800);
    }

    private void start(int digtCounts) {
        Random random = new Random();
        Group group = new Group();
        set = new HashSet<>();
        for (int i = 0; i < digtCounts; i++) {
            if (random.nextBoolean()) {
                int value = random.nextInt(10);
                while (set.contains(value)) {
                    value = random.nextInt(10);
                }
                Text text = new Text(String.valueOf(value));
                set.add(value);
                text.setY(25 + 60 * i);
                text.setX(40);
                int color = random.nextInt(12);
                if (color % 3 == 0) {
                    text.setStyle("-fx-font: 24 arial;color:red;");
                } else {
                    text.setStyle("-fx-font: 24 arial;");
                }
                group.getChildren().add(text);
            } else {
                int picNum = random.nextInt(3);
                int value = random.nextInt(10);
                while (set.contains(value)) {
                    value = random.nextInt(10);
                }
                set.add(value);
                for (int j = 0; j < value; j++) {
                    ImageView imageView = new ImageView(urls.get(picNum));
                    imageView.setY(25 + 60 * i);
                    imageView.setX(30 * j);
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    group.getChildren().add(imageView);
                }
            }
        }
        mainStage.setScene(new Scene(group, 800, 800));

        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        event -> {timeOverScene();}));
        fiveSecondsWonder.setCycleCount(1);
        fiveSecondsWonder.play();
    }

    private void timeOverScene() {
        Text text = new Text("Time over! What numbers do you remember?");
        TextField textField = new TextField();
        text.setY(50);
        String value = set.stream().map(String::valueOf).collect(Collectors.joining(", "));
        Text text1 = new Text(value);
        text1.setY(150);
        text1.setVisible(false);
        Button button = new Button("Check");
        button.setLayoutY(100);
        button.setOnAction(actionEvent -> {
            text1.setVisible(true);
        });

        Group group = new Group();
        group.getChildren().add(text);
        group.getChildren().add(text1);
        group.getChildren().add(button);
        group.getChildren().add(textField);

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(actionEvent -> mainStage.setScene(getMainScene()));
        restartButton.setLayoutY(250);
        group.getChildren().add(restartButton);
        mainStage.setScene(new Scene(group, 800, 800));
    }
}