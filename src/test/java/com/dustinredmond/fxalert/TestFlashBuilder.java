package com.dustinredmond.fxalert;

/*
 *  Copyright 2022  Dustin K. Redmond
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

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TestFlashBuilder extends Application {

    @Override
    public void start(Stage stage) {
        // Displays a "flash" alert of type error
        // with a header text and buttons for the content
        FXAlert.flash(stage).error()
            .withInitOwner(stage)
            .withHeader(new Label("Test1"))
            .withContent(new HBox(5, new Button("Close"), new Button("Ignore")))
            .show();

        // Displays a "flash" alert of type info
        // with content text
        FXAlert.flash(stage).withContent("Hello, World!").show();

        // Displays a "flash" alert of type warning
        // with custom icon
        FXAlert.flash(stage).warn()
            .withGraphic(new Image(getClass().getResourceAsStream("java.png")))
            .withContent("An alert with an icon!")
            .show();

        // Displays a "flash" alert with a white background
        FXAlert.flash(stage).withStyle("-fx-background-color: WHITE").show();

        // Displays a "flash" alert without initOwner
        // Will create separate taskbar icon
        FXAlert.flash().withContent("Test").show();

    }

    public static void main(String[] args) {
        Application.launch(TestFlashBuilder.class, args);
    }
}
