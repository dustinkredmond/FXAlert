package com.dustinredmond.fxalert;

/*
 *  Copyright 2020  Dustin K. Redmond
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Runnable test class for FXAlert
 * Displays a series of JavaFX Alerts
 */
public class TestFXAlert extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<AlertBuilder> builders = new ArrayList<>();

        builders.add(FXAlert.info().withText("title", "header", "content"));
        builders.add(FXAlert.warning().withText("content"));
        builders.add(FXAlert.error().withTextFormat("Error code %d", 12));
        builders.add(FXAlert.exception(new RuntimeException()));

        for (AlertBuilder builder : builders) {
            builder.showAndWait();
        }

        Alert customAlert = new Alert(AlertType.NONE);
        ButtonType closeButton = new ButtonType("Close");
        Optional<ButtonType> result = FXAlert.fromAlert(customAlert)
            .withButtonTypes(closeButton)
            .withText("Close me")
            .showAndWait();
        result.ifPresent(e -> customAlert.close());

        List<ButtonType> yesAndNo = Arrays
            .asList(new ButtonType("Yes"), new ButtonType("No"));
        Optional<ButtonType> result2 = FXAlert.confirm()
            .withButtonTypes(yesAndNo)
            .withText("Yes or no?")
            .showAndWait();
        result2.ifPresent(e -> {
            FXAlert.info().withText("User clicked " + e.getText()).show();
        });

        FXAlert.setGlobalTitleBarIcon(new Image(getClass().getResourceAsStream("java.png")));
        FXAlert.showInfo("This has a custom icon."); // well, on some desktops, not all Linux distros

        FXAlert.setGlobalTitleBarIcon(null);
        FXAlert.showInfo("This one does not have a custom icon.");

    }

    public static void main(String[] args) {
        Application.launch(TestFXAlert.class, args);
    }
}
