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

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

@SuppressWarnings("unused")
public interface IDialogBuilder<T> {

    /**
     * Sets the dialog's title, header, and content texts.
     * @param title Dialog's title text
     * @param header Dialog's header text
     * @param content Dialog's content text
     * @return the dialog builder
     */
    T withText(String title, String header, String content);

    /**
     * Sets the dialog's header and content texts.
     * @param header Dialog's header text
     * @param content Dialog's content text
     * @return the dialog builder
     */
    default T withText(String header, String content) {
        return withText("", header, content);
    }

    /**
     * Sets the dialog's content text
     * @param content Dialog's content text
     * @return the dialog builder
     */
    default T withText(String content) {
        return withText("", "", content);
    }

    /**
     * Sets the content text as a formatted String
     * @param content The formatted content text
     * @param args The text's arguments
     * @return the dialog builder
     */
    default T withTextFormat(String content, Object... args) {
        return withText("", "", String.format(content, args));
    }

    /**
     * Adds the given Image to the dialog's title bar
     * @param image The dialog's title bar icon
     * @return the dialog builder
     */
    T withTitleBarIcon(Image image);

    /**
     * Adds the given ImageView's Image to the dialog's title bar
     * @param imageView ImageView containing the icon
     * @return the dialog builder
     */
    default T withTitleBarIcon(ImageView imageView) {
        return withTitleBarIcon(imageView.getImage());
    }

    /**
     * Adds the image at the given path to the dialog's title bar
     * @param imagePath Path to an icon image
     * @return the dialog builder
     */
    default T withTitleBarIcon(String imagePath) {
        return withTitleBarIcon(new Image(imagePath));
    }

    /**
     * Adds the image at the given URL to the dialog's title bar
     * @param url URL pointing to an image
     * @return the dialog builder
     */
    default T withTitleBarIcon(URL url) {
        return withTitleBarIcon(new Image(url.toExternalForm()));
    }

    /**
     * Adds the image at the given Path to the dialog's title bar
     * @param path Path pointing to an image
     * @return the dialog builder
     */
    default T withTitleBarIcon(Path path) {
        return withTitleBarIcon(new Image(path.toFile().getAbsolutePath()));
    }

    /**
     * Adds the node as the Dialog or Alert's Graphic
     * @param node Node to act as a Graphic
     * @return the dialog builder
     */
    T withGraphic(Node node);

    /**
     * Adds the image at the given path as the Alert
     * or Dialog's Graphic
     * @param imagePath Path to an image
     * @return the dialog builder
     */
    default T withGraphic(String imagePath) {
        return withGraphic(new ImageView(imagePath));
    }

    /**
     * Adds the image at the given Path as the Alert
     * or Dialog's Graphic
     * @param path Path to an image
     * @return the dialog builder
     */
    default T withGraphic(Path path) {
        return withGraphic(new ImageView(path.toFile().getAbsolutePath()));
    }

    /**
     * Clears existing ButtonTypes, then adds them to the
     * Alert/Dialog
     * @param buttonTypes ButtonTypes to be added
     * @return the dialog builder
     */
    T withButtonTypes(ButtonType... buttonTypes);

    /**
     * Specifies the initial modality of the Alert/Dialog
     * @param initModality Alert/Dialog's modality
     * @return the dialog builder
     */
    T withInitModality(Modality initModality);

    /**
     * Specifies the initial owner of the Alert/Dialog
     * @param initOwner Alert/Dialog's owner or parent window
     * @return the dialog builder
     */
    T withInitOwner(Window initOwner);

    /**
     * Specifies the given Node as the Alert/Dialog's content
     * @param node Node to be used as content
     * @return the dialog builder
     */
    T withContent(Node node);

    /**
     * Specifies expandable content to be used in the Alert
     * @param content A Node to act as content
     * @param expanded If true, the content is expaned
     * @return the dialog builder
     */
    T withExpandableContent(Node content, boolean expanded);

    /**
     * Sets expandable content for the Alert
     * @param content The Alert's expandable content
     * @return the dialog builder
     */
    default T withExpandableContent(Node content) {
        return withExpandableContent(content, false);
    }

    /**
     * Sets the Alert/Dialog's resizability
     * @param resizable Resizability of the dialog
     * @return the dialog builder
     */
    T resizable(boolean resizable);

    /**
     * Sets the Alert as resizable
     * @return the dialog builder
     */
    default T resizable() {
        return resizable(true);
    }

    /**
     * Sets the Alert or Dialog's StageStyle
     * @param style The StageStyle
     * @return the dialog builder
     */
    T withStyle(StageStyle style);

    /**
     * Positions the Alert or Dialog at the given
     * X and Y coordinates
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return the dialog builder
     */
    T at(double x, double y);

    /**
     * Specifies the size (width and height) of the dialog
     * @param width Dialog's width
     * @param height Dialog's height
     * @return the dialog builder
     */
    T withSize(double width, double height);

    /**
     * Specifies the dialog's onClose behavior
     * @param e The event handler called onClose
     * @return the dialog builder
     */
    T onClose(EventHandler<DialogEvent> e);

    /**
     * Specifies the dialog's onHidden behavior
     * @param e The event handler called onHidden
     * @return the dialog builder
     */
    T onHidden(EventHandler<DialogEvent> e);

    /**
     * Specifies the dialog's onHiding behavior
     * @param e The event handler called onHiding
     * @return the dialog builder
     */
    T onHiding(EventHandler<DialogEvent> e);

    /**
     * Specifies the dialog's onShown behavior
     * @param e The event handler called onShown
     * @return the dialog builder
     */
    T onShown(EventHandler<DialogEvent> e);

    /**
     * Specifies the dialog's onShowing behavior
     * @param e The event handler called onShowing
     * @return the dialog builder
     */
    T onShowing(EventHandler<DialogEvent> e);

    /**
     * Shows a blocking alert and awaits the result.
     * The Optional result could be String/Double/Integer in the
     * case of an Input dialog, or a ButtonType in the case of an
     * Alert or Dialog that doesn't support input.
     * @return An Optional result from the dialog
     */
    Optional<?> showAndWait();

    /**
     * Shows an alert, without waiting for a result.
     */
    void show();
}
