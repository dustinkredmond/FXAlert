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
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

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
     * Sets the initModality property of the {@code Alert}
     * @param modality The {@code Alert}'s initial modality
     * @return The AlertBuilder
     */
    public AlertBuilder withInitModality(Modality modality) {
        this.alert.initModality(modality);
        return this;
    }

    /**
     * Sets the initOwner property of the {@code Alert}.
     * @param window The {@code Alert}'s initial owner
     * @return The AlertBuilder
     */
    public AlertBuilder withInitOwner(Window window) {
        this.alert.initOwner(window);
        return this;
    }

    /**
     * Sets the Alert's DialogPane's content.
     * @param content Node for the DialogPane's content
     * @return The AlertBuilder
     */
    public AlertBuilder withContent(Node content) {
        this.alert.getDialogPane().setContent(content);
        return this;
    }

    /**
     * Sets the Alert's DialogPane's expandable content and
     * whether the content is expanded.
     * @param content The DialogPane's expandable content
     * @param expanded True is the expandable content should be expanded
     * @return The AlertBuilder
     */
    public AlertBuilder withExpandableContent(Node content, boolean expanded) {
        this.alert.getDialogPane().setExpandableContent(content);
        this.alert.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * Sets the Alert's DialogPane's expandable content
     * @param content The DialogPane's expandable content
     * @return The AlertBuilder
     */
    public AlertBuilder withExpandableContent(Node content) {
        this.alert.getDialogPane().setExpandableContent(content);
        return this;
    }

    /**
     * Sets the resizability of the Alert
     * @param resizable True if the Alert can be resized
     * @return The AlertBuilder
     */
    public AlertBuilder resizable(boolean resizable) {
        this.alert.setResizable(resizable);
        return this;
    }

    /**
     * Sets the Alert to be resizable
     * @return The AlertBuilder
     */
    public AlertBuilder resizable() {
        this.alert.setResizable(true);
        return this;
    }

    /**
     * Sets the Alert's initial {@code StageStyle}
     * @param style The Alert's initial {@code StageStyle}
     * @return The AlertBuilder
     */
    public AlertBuilder withStyle(StageStyle style) {
        this.alert.initStyle(style);
        return this;
    }

    /**
     * Positions the Alert at the given X position
     * @param x The x position
     * @return The AlertBuilder
     */
    public AlertBuilder atX(double x) {
        this.alert.setX(x);
        return this;
    }

    /**
     * Positions the Alert at the given Y position
     * @param y The y position
     * @return The AlertBuilder
     */
    public AlertBuilder atY(double y) {
        this.alert.setY(y);
        return this;
    }

    /**
     * Positions the Alert at the given X and Y positions
     * @param x The x position
     * @param y The y position
     * @return The AlertBuilder
     */
    public AlertBuilder at(double x, double y) {
        this.alert.setX(x);
        this.alert.setY(y);
        return this;
    }

    /**
     * Sets the width of the Alert
     * @param width The Alert's width
     * @return The AlertBuilder
     */
    public AlertBuilder withWidth(double width) {
        this.alert.setWidth(width);
        return this;
    }

    /**
     * Sets the height of the Alert
     * @param height The Alert's height
     * @return The AlertBuilder
     */
    public AlertBuilder withHeight(double height) {
        this.alert.setHeight(height);
        return this;
    }

    /**
     * Sets the size of the Alert
     * @param width The Alert's width
     * @param height The Alert's height
     * @return The AlertBuilder
     */
    public AlertBuilder withSize(double width, double height) {
        this.alert.setWidth(width);
        this.alert.setHeight(height);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the Alert
     * is requested to be closed.
     * @param e The {@code EventHandler}
     * @return The AlertBuilder
     */
    public AlertBuilder onClose(EventHandler<DialogEvent> e) {
        this.alert.setOnCloseRequest(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the Alert
     * is hidden.
     * @param e The {@code EventHandler}
     * @return The AlertBuilder
     */
    public AlertBuilder onHidden(EventHandler<DialogEvent> e) {
        this.alert.setOnHidden(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the Alert
     * is being hidden.
     * @param e The {@code EventHandler}
     * @return The AlertBuilder
     */
    public AlertBuilder onHiding(EventHandler<DialogEvent> e) {
        this.alert.setOnHiding(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the Alert
     * is shown.
     * @param e The {@code EventHandler}
     * @return The AlertBuilder
     */
    public AlertBuilder onShown(EventHandler<DialogEvent> e) {
        this.alert.setOnShown(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the Alert
     * is showing.
     * @param e The {@code EventHandler}
     * @return The AlertBuilder
     */
    public AlertBuilder onShowing(EventHandler<DialogEvent> e) {
        this.alert.setOnShowing(e);
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
