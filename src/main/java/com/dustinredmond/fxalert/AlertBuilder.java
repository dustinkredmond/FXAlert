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
public class AlertBuilder implements IDialogBuilder<AlertBuilder> {

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
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withText(String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withTitleBarIcon(Image image) {
        ((Stage) this.alert.getDialogPane().getScene().getWindow())
            .getIcons().add(image);
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withGraphic(Node image) {
        this.alert.getDialogPane().setGraphic(image);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withButtonTypes(ButtonType... buttonTypes) {
        this.alert.getButtonTypes().clear();
        this.alert.getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withInitModality(Modality modality) {
        this.alert.initModality(modality);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withInitOwner(Window window) {
        this.alert.initOwner(window);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withContent(Node content) {
        this.alert.getDialogPane().setContent(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withExpandableContent(Node content, boolean expanded) {
        this.alert.getDialogPane().setExpandableContent(content);
        this.alert.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder resizable(boolean resizable) {
        this.alert.setResizable(resizable);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withStyle(StageStyle style) {
        this.alert.initStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder at(double x, double y) {
        this.alert.setX(x);
        this.alert.setY(y);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder withSize(double width, double height) {
        this.alert.setWidth(width);
        this.alert.setHeight(height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder onClose(EventHandler<DialogEvent> e) {
        this.alert.setOnCloseRequest(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder onHidden(EventHandler<DialogEvent> e) {
        this.alert.setOnHidden(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder onHiding(EventHandler<DialogEvent> e) {
        this.alert.setOnHiding(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder onShown(EventHandler<DialogEvent> e) {
        this.alert.setOnShown(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AlertBuilder onShowing(EventHandler<DialogEvent> e) {
        this.alert.setOnShowing(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
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

}
