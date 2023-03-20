package ru.itis.les1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.io.FileUtils;

public class CoppingFilesGUIApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        stage.setTitle("Hello!");
        stage.setScene(getMainScene());
        stage.show();

    }

    private Scene getMainScene() {



        Button addFile = new Button("Add file to copping");
        addFile.setLayoutX(300);
        addFile.setDefaultButton(true);
        addFile.setMaxSize(120, 25);
        addFile.setMinSize(120, 25);

        Button choseFolder = new Button("Chose folder");
        choseFolder.setLayoutY(450);
        choseFolder.setDefaultButton(true);
        choseFolder.setMaxSize(120, 25);
        choseFolder.setMinSize(120, 25);

        Button copyFiles = new Button("Copy files");
        copyFiles.setLayoutY(550);
        copyFiles.setDefaultButton(true);
        copyFiles.setMaxSize(120, 25);
        copyFiles.setMinSize(120, 25);
        copyFiles.setDisable(true);

        Label chosen = new Label("Folder not chosen", choseFolder);
        chosen.setLayoutY(450);


        ListView<String> list = new ListView<>();
        List<File> files = new ArrayList<>();
        addFile.setOnAction(actionEvent -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(mainStage);
            if (file != null) {
                files.add(file);
                list.getItems().add(file.getPath());
                if (choseFolder.isDisable()){
                    copyFiles.setDisable(false);
                }
            }
        });
        AtomicReference<File> directory = new AtomicReference<>();
        choseFolder.setOnAction(actionEvent -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File file = chooser.showDialog(mainStage);
            if (file != null) {
                directory.set(file);
                choseFolder.setDisable(true);
                chosen.setText("Chosen directory:   " + directory.get().getPath());
                if (!files.isEmpty()) {
                    copyFiles.setDisable(false);
                }
            }
        });
        copyFiles.setOnAction(actionEvent -> {
            for(File file : files) {
                try {
                    FileUtils.copyFile(file, new File(directory.get().getPath() + "\\" + file.getName()));

                    System.out.println(new File(directory.get().getPath() + "\\" + file.getName()).toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Group group = new Group();
        group.getChildren().add(addFile);
        group.getChildren().add(list);
        group.getChildren().add(choseFolder);
        group.getChildren().add(chosen);
        group.getChildren().add(copyFiles);
        return new Scene(group, 800, 800);
    }

    private void addFile(String s){

    }
}
