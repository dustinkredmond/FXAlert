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

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Class for easily building JavaFX Alerts.
 * API is available as static methods of {@code FXAlert}.
 */
public class FXAlert {

    /**
     * Prepares a JavaFX Alert of AlertType.INFORMATION
     * @return AlertBuilder API
     */
    public static AlertBuilder info() {
        return new AlertBuilder(AlertType.INFORMATION);
    }

    /**
     * Shows an Alert of type "INFORMATION" with specified
     * title, header, and content texts.
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showInfo(String title, String header, String content) {
        info().withText(title, header, content).show();
    }

    /**
     * Shows an Alert of type "INFORMATION" with specified
     * header and content texts.
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showInfo(String header, String content) {
        info().withText(header, content).show();
    }

    /**
     * Shows an Alert of type "INFORMATION" with specified
     * header and content texts.
     * @param content Alert content text.
     */
    public static void showInfo(String content) {
        info().withText(content).show();
    }


    /**
     * Prepares a JavaFX Alert of AlertType.CONFIRMATION
     * @return AlertBuilder API
     */
    public static AlertBuilder confirm() {
        return new AlertBuilder(AlertType.CONFIRMATION);
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * title, header, and content texts.
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     * @return Result of {@code alert.showAndWait()}
     */
    public static Optional<ButtonType> showConfirm(String title, String header, String content) {
        return confirm().withText(title, header, content).showAndWait();
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * header and content texts.
     * @param header Alert header text
     * @param content Alert content text
     * @return Result of {@code alert.showAndWait()}
     */
    public static Optional<ButtonType> showConfirm(String header, String content) {
        return confirm().withText(header, content).showAndWait();
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * content text.
     * @param content Alert content text
     * @return Result of {@code alert.showAndWait()}
     */
    public static Optional<ButtonType> showConfirm(String content) {
        return confirm().withText(content).showAndWait();
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * text. Returns true if and only if user clicks "Ok".
     * @param header Alert header text
     * @param content Alert content text
     * @return true if user clicks "Ok", otherwise false
     */
    public static boolean showConfirmed(String header, String content) {
        return showConfirmed(EMPTY_STRING, header, content);
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * text. Returns true if and only if user clicks "Ok".
     * @param content Alert content text
     * @return true if user clicks "Ok", otherwise false
     */
    public static boolean showConfirmed(String content) {
        return showConfirmed(EMPTY_STRING, EMPTY_STRING, content);
    }

    /**
     * Shows an Alert of type "CONFIRMATION" with specified
     * text. Returns true if and only if user clicks "Ok".
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     * @return true if user clicks "Ok", otherwise false
     */
    public static boolean showConfirmed(String title, String header, String content) {
        Optional<ButtonType> result = confirm().withText(title, header, content).showAndWait();
        return result.isPresent() && "Ok".equals(result.get().getText());
    }

    /**
     * Prepares a JavaFX Alert of AlertType.ERROR
     * @return AlertBuilder API
     */
    public static AlertBuilder error() {
        return new AlertBuilder(AlertType.ERROR);
    }

    /**
     * Shows an Alert of type "ERROR" with specified
     * title, header, and content texts.
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showError(String title, String header, String content) {
        error().withText(title, header, content).show();
    }

    /**
     * Shows an Alert of type "ERROR" with specified
     * header and content texts.
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showError(String header, String content) {
        error().withText(header, content).show();
    }

    /**
     * Shows an Alert of type "ERROR" with specified
     * content text.
     * @param content Alert content text
     */
    public static void showError(String content) {
        error().withText(content).show();
    }

    /**
     * Prepares a JavaFX Alert of AlertType.WARNING
     * @return AlertBuilder API
     */
    public static AlertBuilder warning() {
        return new AlertBuilder(AlertType.WARNING);
    }

    /**
     * Shows an Alert of type "WARNING" with specified
     * title, header, and content texts.
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showWarning(String title, String header, String content) {
        warning().withText(title, header, content).show();
    }

    /**
     * Shows an Alert of type "WARNING" with specified
     * header and content texts.
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showWarning(String header, String content) {
        warning().withText(header, content).show();
    }

    /**
     * Shows an Alert of type "WARNING" with specified
     * content text.
     * @param content Alert content text
     */
    public static void showWarning(String content) {
        warning().withText(content).show();
    }

    /**
     * Prepares a JavaFX Alert suitable for displaying
     * a {@code java.lang.Exception} or {@code Throwable}'s
     * stack trace.
     * @param e Throwable
     * @return AlertBuilder API
     */
    public static AlertBuilder exception(Throwable e) {
        return new AlertBuilder(e, EMPTY_STRING, EMPTY_STRING,
            "The exception stacktrace was:");
    }

    /**
     * Shows an Alert for displaying an {@code Exception}
     * with specified title, header, and content texts.
     * @param e Throwable
     * @param title Alert title text
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showException(Throwable e, String title, String header, String content) {
        new AlertBuilder(e, title, header, content).show();
    }

    /**
     * Shows an Alert for displaying an {@code Exception}
     * with specified header and content texts.
     * @param e Throwable
     * @param header Alert header text
     * @param content Alert content text
     */
    public static void showException(Throwable e, String header, String content) {
        new AlertBuilder(e, EMPTY_STRING, header, content).show();
    }

    /**
     * Shows an Alert for displaying an {@code Exception}
     * with specified content text.
     * @param e Throwable
     * @param content Alert content text
     */
    public static void showException(Throwable e, String content) {
        new AlertBuilder(e, EMPTY_STRING, EMPTY_STRING, content).show();
    }

    /**
     * Prepares the FXAlert API from an existing Alert.
     * @param alert An existing Alert
     * @return AlertBuilder API
     */
    public static AlertBuilder fromAlert(Alert alert) {
        return new AlertBuilder(alert);
    }

    private static final String EMPTY_STRING = "";

}
