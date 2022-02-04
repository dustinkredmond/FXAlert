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

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Builder-style syntax for creating
 * TextInputDialogs
 */
public class InputDialogBuilder implements IDialogBuilder<InputDialogBuilder> {

    public InputDialogBuilder() {
        dialog = new TextInputDialog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withText(String title, String header, String content) {
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withStyle(String style) {
        dialog.getDialogPane().setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withStylesheet(String url) {
        dialog.getDialogPane().getStylesheets().add(url);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withTitleBarIcon(Image image) {
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(image);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withGraphic(Node graphic) {
        dialog.setGraphic(graphic);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withButtonTypes(ButtonType... buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withInitModality(Modality modality) {
        dialog.initModality(modality);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withInitOwner(Window window) {
        dialog.initOwner(window);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withContent(Node content) {
        dialog.getDialogPane().setContent(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withExpandableContent(Node content, boolean expanded) {
        dialog.getDialogPane().setExpandableContent(content);
        dialog.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder resizable(boolean resizable) {
        dialog.setResizable(resizable);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withStageStyle(StageStyle style) {
        dialog.initStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder at(double x, double y) {
        dialog.setX(x);
        dialog.setY(y);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder withSize(double width, double height) {
        dialog.setWidth(width);
        dialog.setHeight(height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder onClose(EventHandler<DialogEvent> e) {
        dialog.setOnCloseRequest(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder onHidden(EventHandler<DialogEvent> e) {
        dialog.setOnHidden(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder onHiding(EventHandler<DialogEvent> e) {
        dialog.setOnHiding(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder onShown(EventHandler<DialogEvent> e) {
        dialog.setOnShown(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputDialogBuilder onShowing(EventHandler<DialogEvent> e) {
        dialog.setOnShowing(e);
        return this;
    }

    /**
     * Shows a blocking alert, optionally returns a String
     * @return Optionally, a String value
     */
    @Override
    public Optional<String> showAndWait() {
        addGlobalIconIfConfigured();
        return dialog.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        showAndWait();
    }

    /**
     * Shows a blocking alert, optionally returns a String
     * @return Optionally, a String value
     */
    public Optional<String> showAndWaitString() {
        addGlobalIconIfConfigured();
        return dialog.showAndWait();
    }

    /**
     * Shows a blocking alert, optionally returns an Integer
     * @return Optionally, an Integer value
     */
    public Optional<Integer> showAndWaitInteger() {
        addGlobalIconIfConfigured();
        dialog.getEditor().textProperty().addListener((ov,old,newVal) -> {
            if (!newVal.matches("\\d+") && !newVal.isEmpty()) {
                dialog.getEditor().setText(old);
            }
        });
        Optional<String> result = dialog.showAndWait();
        return Optional.of(Integer.parseInt(result.orElse("0")));
    }

    /**
     * Shows a blocking alert, optionally returns a Double
     * @return Optionally, a Double
     */
    public Optional<Double> showAndWaitDouble() {
        addGlobalIconIfConfigured();
        TextFormatter<String> formatter = new TextFormatter<>(change ->
            Pattern.compile("\\d*|\\d+.\\d*").matcher(change.getControlNewText()).matches() ? change : null);
        dialog.getEditor().setTextFormatter(formatter);
        Optional<String> result = dialog.showAndWait();
        return Optional.of(Double.parseDouble(result.orElse("0")));
    }

    /**
     * Returns the dialog, as constructed by the builder.
     * @return The TextInputDialog
     */
    public TextInputDialog build() {
        return getDialog();
    }

    /**
     * Returns the dialog, as constructed by the builder.
     * @return The TextInputDialog
     */
    public TextInputDialog getDialog() {
        return dialog;
    }

    private void addGlobalIconIfConfigured() {
        Stage alertStage = ((Stage) dialog.getDialogPane().getScene().getWindow());
        if (FXAlert.getIconImage() != null) {
            alertStage.getIcons().add(FXAlert.getIconImage());
        } else {
            alertStage.getIcons().removeAll();
        }
    }

    private final TextInputDialog dialog;

}
