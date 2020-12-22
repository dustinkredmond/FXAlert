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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Contains API that simplifies creating JavaFX
 * {@code javafx.scene.control.Alert}s
 */
public class AlertBuilder {

    /**
     * Default constructor, creates a default AlertBuilder
     * with type {@code javafx.scene.control.Alert.AlertType.INFORMATION}
     */
    public AlertBuilder() {
        alert = new Alert(AlertType.INFORMATION);
    }

    /**
     * Creates an AlertBuilder suitable for the passed
     * {@code javafx.scene.control.Alert.AlertType}
     * @param type The desired {@code AlertType}
     */
    public AlertBuilder(AlertType type) {
        this.alert = new Alert(type);
    }

    /**
     * Creates an AlertBuilder suitable for modifying the passed Alert
     * @param alert A {@code javafx.scene.control.Alert}
     */
    public AlertBuilder(Alert alert) {
        this.alert = alert;
    }

    /**
     * Creates an AlertBuilder suitable for creating Alerts
     * that display {@code Exception} stack traces.
     * @param t The throwable
     * @param title The Alert's title text
     * @param header The Alert's header text
     * @param contentText The Alert's content text
     */
    public AlertBuilder(Throwable t, String title, String header, String contentText) {
        this.alert = new Alert(AlertType.ERROR);
        alert.setResizable(true);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setContent(new Label(title));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);

        TextArea ta = new TextArea(sw.toString());
        ta.setEditable(false);
        ta.setWrapText(true);
        ta.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane grid = new GridPane();
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.add(new Label(contentText), 0, 0);
        grid.add(ta, 0, 1);
        alert.getDialogPane().setExpandableContent(grid);
        alert.getDialogPane().setExpanded(true);
    }

    /**
     * Sets the alert's title text, header text, and content text.
     * @param title The alert's title text
     * @param header The alert's header text
     * @param content The alert's content text
     * @return The AlertBuilder
     */
    public AlertBuilder withText(String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return this;
    }

    /**
     * Sets the alert's header text and content text.
     * @param header The alert's header text
     * @param content The alert's content text
     * @return The AlertBuilder
     */
    public AlertBuilder withText(String header, String content) {
        alert.setTitle(EMPTY_STRING);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return this;
    }

    /**
     * Sets the alert's main content text.
     * @param contentText The content text
     * @return The AlertBuilder
     */
    public AlertBuilder withText(String contentText) {
        alert.setTitle(EMPTY_STRING);
        alert.setHeaderText(EMPTY_STRING);
        alert.setContentText(contentText);
        return this;
    }

    /**
     * Sets the formatted text as the alert's main content text.
     * @param contentText The content text (format string)
     * @param args The format string's arguments.
     * @return The AlertBuilder
     */
    public AlertBuilder withTextFormat(String contentText, Object... args) {
        alert.setContentText(String.format(contentText, args));
        return this;
    }

    /**
     * Adds the Image as the alert's title bar icon.
     * @param image A JavaFX {@code Image}
     * @return The AlertBuilder
     */
    public AlertBuilder withTitleBarIcon(Image image) {
        ((Stage) this.alert.getDialogPane().getScene().getWindow())
            .getIcons().add(image);
        return this;
    }

    /**
     * Adds the ImageView as the alert's title bar icon.
     * @param imageView A JavaFX {@code ImageView}
     * @return The AlertBuilder
     */
    public AlertBuilder withTitleBarIcon(ImageView imageView) {
        return withTitleBarIcon(imageView.getImage());
    }

    /**
     * Adds the Image at the given path as the alert's title
     * bar icon.
     * @param imagePath Path to an image
     * @return The AlertBuilder
     */
    public AlertBuilder withTitleBarIcon(String imagePath) {
        withTitleBarIcon(new Image(imagePath));
        return this;
    }

    /**
     * Adds the Image at the given path as the alert's header graphic.
     * @param imagePath Path to an image
     * @return The AlertBuilder
     */
    public AlertBuilder withGraphic(String imagePath) {
        this.alert.getDialogPane().setGraphic(new ImageView(imagePath));
        return this;
    }

    /**
     * Adds the image as the alert's header graphic.
     * @param image Image for the header graphic.
     * @return The AlertBuilder
     */
    public AlertBuilder withGraphic(Node image) {
        this.alert.getDialogPane().setGraphic(image);
        return this;
    }

    /**
     * Adds the passed {@code javafx.scene.control.ButtonType}s to the
     * alert.
     * @param buttonTypes ButtonTypes to add to the alert.
     * @return The AlertBuilder
     */
    public AlertBuilder withButtonTypes(ButtonType... buttonTypes) {
        this.alert.getButtonTypes().clear();
        this.alert.getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Adds the passed {@code javafx.scene.control.ButtonType}s to the
     * alert.
     * @param buttonTypes ButtonTypes to add to the alert.
     * @return The AlertBuilder
     */
    public AlertBuilder withButtonTypes(List<ButtonType> buttonTypes) {
        this.alert.getButtonTypes().clear();
        this.alert.getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Shows a blocking alert.
     * @return The {@code javafx.scene.control.ButtonType} activated by the user.
     */
    public Optional<ButtonType> showAndWait() {
        addGlobalIconIfConfigured();
        return this.alert.showAndWait();
    }

    /**
     * Shows a non-blocking alert on the JavaFX Application thread.
     */
    public void showLater() {
        addGlobalIconIfConfigured();
        Platform.runLater(this.alert::show);
    }

    /**
     * Shows a blocking alert on the JavaFX Application thread.
     * @return The {@code javafx.scene.control.ButtonType} activated by the user.
     */
    public Optional<ButtonType> showAndWaitLater() {
        addGlobalIconIfConfigured();
        AtomicReference<Optional<ButtonType>> buttonType = new AtomicReference<>();
        Platform.runLater(() -> buttonType.set(this.alert.showAndWait()));
        return buttonType.get();
    }

    private void addGlobalIconIfConfigured() {
        if (alert == null) {
            return;
        }

        Stage alertStage = ((Stage) alert.getDialogPane().getScene().getWindow());
        if (FXAlert.getIconImage() != null) {
            alertStage.getIcons().add(FXAlert.getIconImage());
        } else {
            alertStage.getIcons().removeAll();
        }
    }

    /**
     * Shows a non-blocking alert.
     */
    public void show() {
        addGlobalIconIfConfigured();
        this.alert.show();
    }

    /**
     * Returns the {@code Alert} as currently constructed by {@code AlertBuilder}
     * @return Built {@code javafx.scene.control.AlertType}
     */
    public Alert build() {
        addGlobalIconIfConfigured();
        return this.alert;
    }

    /**
     * Returns the {@code Alert} as currently constructed by {@code AlertBuilder}
     * @return Built {@code javafx.scene.control.AlertType}
     */
    public Alert getAlert() {
        return build();
    }

    private final Alert alert;
    private static final String EMPTY_STRING = "";

}
