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

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Builder-style class for creating
 * temporary "flash" notifications in the
 * lower-right corner of the user's screen.
 */
public class FlashBuilder {

    /**
     * Constructs the FlashBuilder API
     */
    public FlashBuilder() {
        super();
    }

    /**
     * Instructs the flash notification to use the
     * icon from the AlertType.INFORMATION Alert
     * @return The FlashBuilder
     */
    public FlashBuilder info() {
        this.graphic = FXIcons.getInfo();
        return this;
    }

    /**
     * Instructs the flash notification to use the
     * icon from the AlertType.ERROR Alert
     * @return The FlashBuilder
     */
    public FlashBuilder error() {
        this.graphic = FXIcons.getError();
        return this;
    }

    /**
     * Instructs the flash notification to use the
     * icon from the AlertType.WARNING Alert
     * @return The FlashBuilder
     */
    public FlashBuilder warn() {
        this.graphic = FXIcons.getWarn();
        return this;
    }

    /**
     * Instructs the flash notification to use the
     * icon from the AlertType.CONFIRMATION Alert
     * @return The FlashBuilder
     */
    public FlashBuilder confirm() {
        this.graphic = FXIcons.getConfirm();
        return this;
    }

    /**
     * Sets the Node as the header section for the
     * flash notification.
     * @param header A Node to act as the notification's header.
     * @return The FlashBuilder
     */
    public FlashBuilder withHeader(Node header) {
        this.header = header;
        return this;
    }

    /**
     * Sets the header section of the flash notification.
     * @param header A String to act as content
     * @return The FlashBuilder
     */
    public FlashBuilder withHeader(String header) {
        this.header = new Label(header);
        return this;
    }

    /**
     * Sets the Node as the content section of the
     * flash notification.
     * @param content A Node to act as the notification's content
     * @return The FlashBuilder
     */
    public FlashBuilder withContent(Node content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the content section of the flash
     * notification.
     * @param content A String to act as content
     * @return The FlashBuilder
     */
    public FlashBuilder withContent(String content) {
        this.content = new Label(content);
        return this;
    }

    /**
     * Sets an Image as the graphic (icon) for the
     * flash notification.
     * @param graphic An Image to act as an icon
     * @return The FlashBuilder
     */
    public FlashBuilder withGraphic(Image graphic) {
        ImageView iv = new ImageView(graphic);
        iv.setFitHeight(48);
        iv.setFitWidth(48);
        this.graphic = iv;
        return this;
    }

    /**
     * Sets a Node as the graphic (icon) for the
     * flash notification.
     * @param graphic A Node to act as an icon
     * @return The FlashBuilder
     */
    public FlashBuilder withGraphic(Node graphic) {
        this.graphic = graphic;
        return this;
    }

    /**
     * Set the size of the flash notification
     * with the given width and height.
     * @param x The notification's width
     * @param y The notification's height
     * @return The FlashBuilder
     */
    public FlashBuilder withSize(double x, double y) {
        this.sizeX = x;
        this.sizeY = y;
        return this;
    }

    /**
     * Allows setting the JavaFX style of the pane (GridPane)
     * used to display the notification.
     * @param style The JavaFX CSS style
     * @return The FlashBuilder
     */
    public FlashBuilder withStyle(String style) {
        this.paneStyle = style;
        return this;
    }

    /**
     * Sets the stylesheet for the Flash alert's content Node
     * @param url Path to a stylesheet
     * @return the FlashBuilder
     */
    public FlashBuilder withStylesheet(String url) {
        this.content.getScene().setUserAgentStylesheet(url);
        return this;
    }

    /**
     * Shows the "flash" notification for a brief period of
     * time in the lower-right corner of the user's active screen.
     */
    public void show() {
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setIconified(false);
        stage.setScene(buildGrid());

        if (FXAlert.getIconImage() != null) {
            stage.getIcons().add(FXAlert.getIconImage());
        }

        AnimationTimer t = new AnimationTimer() {
            private double wait;
            @Override
            public void handle(long now) {
                wait++;
                if (!(wait > 150)) {
                    return;
                }
                if (stage.getOpacity() >= 0.01) {
                    stage.setOpacity(stage.getOpacity() - 0.01);
                } else {
                    stage.hide();
                    stop();
                }
            }
        };

        if (sizeX > 0 || sizeY > 0) {
            stage.setX(bounds.getMinX() + bounds.getWidth() - sizeX);
            stage.setY(bounds.getMinY() + bounds.getHeight() - sizeY);
            stage.setWidth(sizeX);
            stage.setHeight(sizeY);
        } else {
            stage.setX(bounds.getMinX() + bounds.getWidth() - 400);
            stage.setY(bounds.getMinY() + bounds.getHeight() - 100);
            stage.setWidth(400);
            stage.setHeight(100);
        }

        stage.show();
        stage.toFront();
        t.start();
    }

    private Scene buildGrid() {
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        grid.setPadding(new Insets(25));
        grid.setVgap(10);
        grid.setHgap(20);

        if (paneStyle != null && !paneStyle.trim().isEmpty()) {
            grid.setStyle(paneStyle);
        }

        // if either header or content is missing
        // graphic should only span one column, otherwise
        // header or content will be "off-centered"
        int rowSpan = (header == null || content == null) ? 1 : 2;

        if (graphic != null) {
            grid.add(graphic, 0, 0, 1, rowSpan);
        } else {
            grid.add(FXIcons.getInfo(), 0, 0, 1 ,rowSpan);
        }

        if (header == null && content == null) {
            return scene;
        }

        if (header == null) {
            grid.add(content, 1, 0);
        } else if (content == null) {
            grid.add(header, 1, 0);
        } else {
            grid.add(header, 1, 0);
            grid.add(content, 1, 1);

        }
        return scene;
    }

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    private Node header;
    private Node content;
    private Node graphic;
    private double sizeX;
    private double sizeY;
    private String paneStyle;

}
