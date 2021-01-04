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

import javafx.scene.control.Label;

/**
 * Provides access to the built-in JavaFX icons.
 * NOTE: If modena.css changes, this class will need to.
 */
public class FXIcons {

    /**
     * Returns a Label with the style of an Alert with type
     * INFORMATION's icon.
     * @return A Label with information icon
     */
    public static Label getInfo() {
        return getIcon("alert", "information", "dialog-pane");
    }

    /**
     * Returns a Label with the style of an Alert with type
     * CONFIRMATION's icon.
     * @return A Label with confirmation icon
     */
    public static Label getConfirm() {
        return getIcon("choice-dialog", "dialog-pane");
    }

    /**
     * Returns a Label with the style of an Alert with type
     * ERROR's icon.
     * @return A Label with error icon
     */
    public static Label getError() {
        return getIcon("alert", "error", "dialog-pane");
    }

    /**
     * Returns a Label with the style of an Alert with type
     * WARNING's icon.
     * @return A Label with warning icon
     */
    public static Label getWarn() {
        return getIcon("alert", "warning", "dialog-pane");
    }

    /**
     * Retrieves a JavaFX icon from its style class elements.
     * @param elements Icon style class elements
     * @return A Label with the icon
     */
    private static Label getIcon(String... elements) {
        Label label = new Label();
        label.getStyleClass().addAll(elements);
        label.setPrefSize(48, 48);
        return label;
    }

}
