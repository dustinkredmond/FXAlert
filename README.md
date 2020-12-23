## FXAlert

FXAlert believes that dialogs should come easy. JavaFX Alerts can be slightly convoluted.
For example, if we want to add a custom icon to a JavaFX Alert, we must create this monster:

`((Stage) this.alert.getDialogPane().getScene().getWindow()).getIcons().add(YOUR_IMAGE);`

FXAlert makes showing/creating dialogs, getting user input, graphics, icons, and using
JavaFX Alerts in general, a much smoother process.

---

### How to get FXAlert

We're on Maven Central. Use Maven or Gradle.

In pom.xml
```xml
<dependency>
  <groupId>com.dustinredmond.fxalert</groupId>
  <artifactId>FXAlert</artifactId>
  <version>2.0.2</version>
</dependency>
```

In build.gradle
```groovy
compile group: 'com.dustinredmond.fxalert', name: 'FXAlert', version: '2.0.2'
```

---

### How does it work?

FXAlert's API makes it easy to create different types of dialog. We have several ways to do this.
We can use the static methods:

 - FXAlert.info()
 - FXAlert.error()
 - FXAlert.warning()
 - FXAlert.confirm()
 - FXAlert.exception()
 
These methods produce a builder-style syntax.
We can chain method calls to get the behavior we need.

`FXAlert.info().withText("Hello, World!").withTitleBarIcon(icon).show();`

`FXAlert.info().withGraphic(myGraphic).withText("Test").show();`

---

### Title/Header/Content texts

We can set various types of text with overloaded `withText(...)` methods.

```
FXAlert.info().withText("Title text", "Header text", "Content text");
FXAlert.info().withText("Header text", "Content text");
FXAlert.info().withText("Content text");
```

---

### Brevity, please!

If long method chains aren't your thing, we've got you covered. You can also use static convenience
methods included in FXAlert. 

```
FXAlert.showInfo("I'm an info dialog!");
FXAlert.showWarning("I'm a warning dialog!");
FXAlert.showException(ex, "I'm an exception dialog!");
```

Note that each short-hand method, also has a builder form:
```java
try {
    DriverManager.getConnection(url);
} catch (Exception e) {
    FXAlert.exception(e).withTextFormat("Couldn't reach: %s", url).show();
}
```

---

### How about confirmation dialogs, how do those work?

```
Optional<ButtonType> result = FXAlert.confirm().withText("Please confirm!").showAndWait();
result.ifPresent(e -> {
 // do something with our result here
});

// But, wait, I don't like Optionals.
// Fine, we have you covered....

boolean okay = FXAlert.showConfirmed("Click Ok or Cancel.");
if (okay) {
    // user clicked "Ok"
} else {
    // user cancelled or closed window
}
```
