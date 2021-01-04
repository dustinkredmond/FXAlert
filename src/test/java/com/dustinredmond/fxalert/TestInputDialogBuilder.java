package com.dustinredmond.fxalert;

/*
 *  Copyright 2021  Dustin K. Redmond
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

import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestInputDialogBuilder extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Optional<String> result = FXAlert.input()
            .withText("Enter a String value:")
            .showAndWaitString();
        result.ifPresent(System.out::println);

        Optional<Double> resultDouble = FXAlert.input()
            .withText("Enter a Double value:")
            .showAndWaitDouble();
        resultDouble.ifPresent(System.out::println);

        Optional<Integer> resultInt = FXAlert.input()
            .withText("Enter an Integer value:")
            .showAndWaitInteger();
        resultInt.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        Application.launch(TestInputDialogBuilder.class, args);
    }
}
