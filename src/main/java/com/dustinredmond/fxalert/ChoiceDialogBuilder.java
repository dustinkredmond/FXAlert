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

import java.util.List;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Builder-style syntax for creating a JavaFX ChoiceDialog
 */
public class ChoiceDialogBuilder {

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
     * Adds the given title, header, and content texts
     * to the ChoiceDialog
     * @param title ChoiceDialog's title
     * @param header ChoiceDialog header
     * @param content ChoiceDialog content
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withText(String title, String header, String content) {
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return this;
    }

    /**
     * Adds the given header and content texts
     * to the ChoiceDialog
     * @param header ChoiceDialog's header
     * @param content ChoiceDialog's content
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withText(String header, String content) {
        return withText("", header, content);
    }

    /**
     * Adds the given content text to the
     * ChoiceDialog
     * @param content ChoiceDialog's content
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withText(String content) {
        return withText("", "", content);
    }

    /**
     * Adds the formatted text to the ChoiceDialog's
     * content text.
     * @param content Content text
     * @param args Formatted text's arguments
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withTextFormat(String content, Object... args) {
        withText("", "", String.format(content, args));
        return this;
    }

    /**
     * Adds the given image as the ChoiceDialog's title
     * bar icon.
     * @param image Title bar icon image
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withTitleBarIcon(Image image) {
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(image);
        return this;
    }

    /**
     * Adds the given ImageView's Image as the ChoiceDialog's
     * title bar icon.
     * @param imageView The ImageView whose image to use
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withTitleBarIcon(ImageView imageView) {
        return withTitleBarIcon(imageView.getImage());
    }

    /**
     * Adds the given Node as a graphic to the ChoiceDialog's
     * dialog pane.
     * @param graphic Graphic for the ChoiceDialog
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withGraphic(Node graphic) {
        dialog.setGraphic(graphic);
        return this;
    }

    /**
     * Adds the image at the given path as the ChoiceDialog's
     * dialog pane graphic.
     * @param imageUrl Path to an image
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withGraphic(String imageUrl) {
        dialog.setGraphic(new ImageView(imageUrl));
        return this;
    }

    /**
     * Clears and adds the given ButtonTypes to the ChoiceDialog
     * @param buttonTypes ButtonTypes to add to the ChoiceDialog
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withButtonTypes(ButtonType... buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Clears then adds the given list of ButtonTypes to the ChoiceDialog
     * @param buttonTypes List of ButtonTypes to add
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withButtonTypes(List<ButtonType> buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Sets the initModality property of the {@code ChoiceDialog}
     * @param modality The {@code ChoiceDialog}'s initial modality
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withInitModality(Modality modality) {
        dialog.initModality(modality);
        return this;
    }

    /**
     * Sets the initOwner property of the {@code ChoiceDialog}.
     * @param window The {@code ChoiceDialog}'s initial owner
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withInitOwner(Window window) {
        dialog.initOwner(window);
        return this;
    }

    /**
     * Sets the ChoiceDialog's DialogPane content.
     * @param content Node for the DialogPane's content
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withContent(Node content) {
        dialog.getDialogPane().setContent(content);
        return this;
    }

    /**
     * Sets the ChoiceDialog's expandable content and
     * whether the content is expanded.
     * @param content The DialogPane's expandable content
     * @param expanded True is the expandable content should be expanded
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withExpandableContent(Node content, boolean expanded) {
        dialog.getDialogPane().setExpandableContent(content);
        dialog.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * Sets the ChoiceDialog's expandable content
     * @param content The DialogPane's expandable content
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withExpandableContent(Node content) {
        dialog.getDialogPane().setExpandableContent(content);
        return this;
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
     * Sets the resizability of the ChoiceDialog.
     * @param resizable True if the ChoiceDialog can be resized
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder resizable(boolean resizable) {
        dialog.setResizable(resizable);
        return this;
    }

    /**
     * Sets the ChoiceDialog to be resizable
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder resizable() {
        dialog.setResizable(true);
        return this;
    }

    /**
     * Sets the ChoiceDialog's initial {@code StageStyle}
     * @param style The ChoiceDialog's initial {@code StageStyle}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withStyle(StageStyle style) {
        dialog.initStyle(style);
        return this;
    }

    /**
     * Positions the ChoiceDialog at the given X position
     * @param x The x position
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder atX(double x) {
        dialog.setX(x);
        return this;
    }

    /**
     * Positions the ChoiceDialog at the given Y position
     * @param y The y position
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder atY(double y) {
        dialog.setY(y);
        return this;
    }

    /**
     * Positions the ChoiceDialog at the given X and Y positions
     * @param x The x position
     * @param y The y position
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder at(double x, double y) {
        dialog.setX(x);
        dialog.setY(y);
        return this;
    }

    /**
     * Sets the width of the ChoiceDialog.
     * @param width The ChoiceDialog's width
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withWidth(double width) {
        dialog.setWidth(width);
        return this;
    }

    /**
     * Sets the height of the ChoiceDialog
     * @param height The ChoiceDialog's height
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withHeight(double height) {
        dialog.setHeight(height);
        return this;
    }

    /**
     * Sets the size of the ChoiceDialog.
     * @param width The ChoiceDialog's width
     * @param height The ChoiceDialog's height
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder withSize(double width, double height) {
        dialog.setWidth(width);
        dialog.setHeight(height);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the ChoiceDialog
     * is requested to be closed.
     * @param e The {@code EventHandler}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder onClose(EventHandler<DialogEvent> e) {
        dialog.setOnCloseRequest(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the ChoiceDialog
     * is hidden.
     * @param e The {@code EventHandler}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder onHidden(EventHandler<DialogEvent> e) {
        dialog.setOnHidden(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the ChoiceDialog
     * is being hidden.
     * @param e The {@code EventHandler}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder onHiding(EventHandler<DialogEvent> e) {
        dialog.setOnHiding(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the ChoiceDialog
     * is shown.
     * @param e The {@code EventHandler}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder onShown(EventHandler<DialogEvent> e) {
        dialog.setOnShown(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the ChoiceDialog
     * is showing.
     * @param e The {@code EventHandler}
     * @return The ChoiceDialogBuilder
     */
    public ChoiceDialogBuilder onShowing(EventHandler<DialogEvent> e) {
        dialog.setOnShowing(e);
        return this;
    }

    /**
     * Waits for the user to select a choice or close
     * the dialog. Optionally, returns the selected String
     * @return Optionally, the selected choice (unless dialog closed)
     */
    public Optional<String> showAndWait() {
        return dialog.showAndWait();
    }

    private final ChoiceDialog<String> dialog;

}
