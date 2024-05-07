/**
 * FXAlert Java 9+ JPMS compatibility
 */
module com.dustinredmond.fxalert{
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    exports com.dustinredmond.fxalert;
}
