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
import java.util.OptionalDouble;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Builder-style syntax for creating
 * TextInputDialogs
 */
public class InputDialogBuilder {

    public InputDialogBuilder() {
        dialog = new TextInputDialog();
    }

    /**
     * Adds the given title, header, and content texts
     * to the TextInputDialog
     * @param title TextInputDialog's title
     * @param header TextInputDialog header
     * @param content TextInputDialog content
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withText(String title, String header, String content) {
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return this;
    }

    /**
     * Adds the given header and content texts
     * to the TextInputDialog
     * @param header TextInputDialog's header
     * @param content TextInputDialog's content
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withText(String header, String content) {
        withText("", header, content);
        return this;
    }

    /**
     * Adds the given content text to the
     * TextInputDialog
     * @param content TextInputDialog's content
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withText(String content) {
        withText("", "", content);
        return this;
    }

    /**
     * Adds the formatted text to the TextInputDialog's
     * content text.
     * @param content Content text
     * @param args Formatted text's arguments
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withTextFormat(String content, Object... args) {
        withText("", "", String.format(content, args));
        return this;
    }

    /**
     * Adds the given image as the TextInputDialog's title
     * bar icon.
     * @param image Title bar icon image
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withTitleBarIcon(Image image) {
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(image);
        return this;
    }

    /**
     * Adds the given ImageView's Image as the TextInputDialog's
     * title bar icon.
     * @param imageView The ImageView whose image to use
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withTitleBarIcon(ImageView imageView) {
        withTitleBarIcon(imageView.getImage());
        return this;
    }

    /**
     * Adds the given Node as a graphic to the TextInputDialog's
     * dialog pane.
     * @param graphic Graphic for the TextInputDialog
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withGraphic(Node graphic) {
        dialog.setGraphic(graphic);
        return this;
    }

    /**
     * Adds the image at the given path as the TextInputDialog's
     * dialog pane graphic.
     * @param imageUrl Path to an image
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withGraphic(String imageUrl) {
        dialog.setGraphic(new ImageView(imageUrl));
        return this;
    }

    /**
     * Clears and adds the given ButtonTypes to the TextInputDialog
     * @param buttonTypes ButtonTypes to add to the TextInputDialog
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withButtonTypes(ButtonType... buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Clears then adds the given list of ButtonTypes to the TextInputDialog
     * @param buttonTypes List of ButtonTypes to add
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withButtonTypes(List<ButtonType> buttonTypes) {
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);
        return this;
    }

    /**
     * Sets the initModality property of the {@code TextInputDialog}
     * @param modality The {@code TextInputDialog}'s initial modality
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withInitModality(Modality modality) {
        dialog.initModality(modality);
        return this;
    }

    /**
     * Sets the initOwner property of the {@code TextInputDialog}.
     * @param window The {@code TextInputDialog}'s initial owner
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withInitOwner(Window window) {
        dialog.initOwner(window);
        return this;
    }

    /**
     * Sets the TextInputDialog's DialogPane's content.
     * @param content Node for the DialogPane's content
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withContent(Node content) {
        dialog.getDialogPane().setContent(content);
        return this;
    }

    /**
     * Sets the TextInputDialog's expandable content and
     * whether the content is expanded.
     * @param content The DialogPane's expandable content
     * @param expanded True is the expandable content should be expanded
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withExpandableContent(Node content, boolean expanded) {
        dialog.getDialogPane().setExpandableContent(content);
        dialog.getDialogPane().setExpanded(expanded);
        return this;
    }

    /**
     * Sets the TextInputDialog's expandable content
     * @param content The DialogPane's expandable content
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withExpandableContent(Node content) {
        dialog.getDialogPane().setExpandableContent(content);
        return this;
    }

    /**
     * Sets the resizability of the TextInputDialog.
     * @param resizable True if the TextInputDialog can be resized
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder resizable(boolean resizable) {
        dialog.setResizable(resizable);
        return this;
    }

    /**
     * Sets the TextInputDialog to be resizable
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder resizable() {
        dialog.setResizable(true);
        return this;
    }

    /**
     * Sets the TextInputDialog's initial {@code StageStyle}
     * @param style The TextInputDialog's initial {@code StageStyle}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withStyle(StageStyle style) {
        dialog.initStyle(style);
        return this;
    }

    /**
     * Positions the TextInputDialog at the given X position
     * @param x The x position
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder atX(double x) {
        dialog.setX(x);
        return this;
    }

    /**
     * Positions the TextInputDialog at the given Y position
     * @param y The y position
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder atY(double y) {
        dialog.setY(y);
        return this;
    }

    /**
     * Positions the TextInputDialog at the given X and Y positions
     * @param x The x position
     * @param y The y position
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder at(double x, double y) {
        dialog.setX(x);
        dialog.setY(y);
        return this;
    }

    /**
     * Sets the width of the TextInputDialog.
     * @param width The TextInputDialog's width
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withWidth(double width) {
        dialog.setWidth(width);
        return this;
    }

    /**
     * Sets the height of the TextInputDialog
     * @param height The TextInputDialog's height
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withHeight(double height) {
        dialog.setHeight(height);
        return this;
    }

    /**
     * Sets the size of the TextInputDialog.
     * @param width The TextInputDialog's width
     * @param height The TextInputDialog's height
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder withSize(double width, double height) {
        dialog.setWidth(width);
        dialog.setHeight(height);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the TextInputDialog
     * is requested to be closed.
     * @param e The {@code EventHandler}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder onClose(EventHandler<DialogEvent> e) {
        dialog.setOnCloseRequest(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the TextInputDialog
     * is hidden.
     * @param e The {@code EventHandler}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder onHidden(EventHandler<DialogEvent> e) {
        dialog.setOnHidden(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the TextInputDialog
     * is being hidden.
     * @param e The {@code EventHandler}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder onHiding(EventHandler<DialogEvent> e) {
        dialog.setOnHiding(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the TextInputDialog
     * is shown.
     * @param e The {@code EventHandler}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder onShown(EventHandler<DialogEvent> e) {
        dialog.setOnShown(e);
        return this;
    }

    /**
     * Sets the {@code EventHandler} called when the TextInputDialog
     * is showing.
     * @param e The {@code EventHandler}
     * @return The InputDialogBuilder
     */
    public InputDialogBuilder onShowing(EventHandler<DialogEvent> e) {
        dialog.setOnShowing(e);
        return this;
    }

    /**
     * Shows a blocking alert, optionally returns a String
     * @return Optionally, a String value
     */
    public Optional<String> showAndWait() {
        addGlobalIconIfConfigured();
        return dialog.showAndWait();
    }

    /**
     * Shows a blocking alert, optionally returns a String
     * @return Optionally, a String value
     */
    public Optional<String> showAndWaitString() {
        addGlobalIconIfConfigured();
        return dialog.showAndWait();
    }

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

    public Optional<Double> showAndWaitDouble() {
        addGlobalIconIfConfigured();
        TextFormatter<String> formatter = new TextFormatter<>(change ->
            Pattern.compile("\\d*|\\d+.\\d*").matcher(change.getControlNewText()).matches() ? change : null);
        dialog.getEditor().setTextFormatter(formatter);
        Optional<String> result = dialog.showAndWait();
        return Optional.of(Double.parseDouble(result.orElse("0")));
    }

    public TextInputDialog build() {
        return getDialog();
    }

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
