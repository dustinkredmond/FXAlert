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
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Builder-style syntax for creating a JavaFX ChoiceDialog
 */
public class ChoiceDialogBuilder implements IDialogBuilder<ChoiceDialogBuilder> {

    /**
     * Prepares a ChoiceDialog with a list of choices
     * @param choices Choices for user to choose from
     */
    public ChoiceDialogBuilder(String... choices) {
        dialog = new ChoiceDialog<>();
        dialog.getItems().addAll(choices);
        dialog.setSelectedItem(dialog.getItems().get(0));
    }

    /**
     * Prepares a ChoiceDialog.
     * Call @{code withChoices(String... choices)}
     */
    public ChoiceDialogBuilder() {
        dialog = new ChoiceDialog<>();
    }

    /**
     * Clears previous list of choices and adds the specified
     * to the ChoiceDialog's list of values.
     * @param choices Choices for user to choose from
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withChoices(String... choices) {
        dialog.getItems().clear();
        dialog.getItems().addAll(choices);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withText(String title, String header, String content) {
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withStyle(String style) {
        dialog.getDialogPane().setStyle(style);
        return this;
    }

    @Override
    public ChoiceDialogBuilder withStylesheet(String url) {
        dialog.getDialogPane().getStylesheets().add(url);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withTitleBarIcon(Image image) {
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(image);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withGraphic(Node graphic) {
        dialog.setGraphic(graphic);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withButtonTypes(ButtonType... buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withInitModality(Modality modality) {
        dialog.initModality(modality);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withInitOwner(Window window) {
        dialog.initOwner(window);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withContent(Node content) {
        dialog.getDialogPane().setContent(content);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withExpandableContent(Node content, boolean expanded) {
        dialog.getDialogPane().setExpandableContent(content);
        dialog.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder resizable(boolean resizable) {
        dialog.setResizable(resizable);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withStageStyle(StageStyle style) {
        dialog.initStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder at(double x, double y) {
        dialog.setX(x);
        dialog.setY(y);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder withSize(double width, double height) {
        dialog.setWidth(width);
        dialog.setHeight(height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder onClose(EventHandler<DialogEvent> e) {
        dialog.setOnCloseRequest(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder onHidden(EventHandler<DialogEvent> e) {
        dialog.setOnHidden(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder onHiding(EventHandler<DialogEvent> e) {
        dialog.setOnHiding(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder onShown(EventHandler<DialogEvent> e) {
        dialog.setOnShown(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChoiceDialogBuilder onShowing(EventHandler<DialogEvent> e) {
        dialog.setOnShowing(e);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> showAndWait() {
        return dialog.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        showAndWait();
    }

    private final ChoiceDialog<String> dialog;

}
