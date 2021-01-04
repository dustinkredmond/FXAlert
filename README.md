## FXAlert

[![Maven Central](https://img.shields.io/maven-central/v/com.dustinredmond.fxalert/FXAlert.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.dustinredmond.fxalert%22%20AND%20a:%22FXAlert%22)

FXAlert believes that dialogs should come easy. JavaFX Alerts can be slightly convoluted.
For example, if we want to add a custom icon to a JavaFX Alert, we must create this monster:

`((Stage) this.alert.getDialogPane().getScene().getWindow()).getIcons().add(YOUR_IMAGE);`

FXAlert makes showing/creating dialogs, getting user input, graphics, icons, and using
JavaFX Alerts in general, a much smoother process.

---

### How to get FXAlert

We're on Maven Central. Use Maven or your favorite build tool.

In pom.xml
```xml
<dependency>
  <groupId>com.dustinredmond.fxalert</groupId>
  <artifactId>FXAlert</artifactId>
  <version>2.3.0</version>
</dependency>
```

In build.gradle
```groovy
compile group: 'com.dustinredmond.fxalert', name: 'FXAlert', version: '2.3.0'
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
 - FXAlert.input()
 - FXAlert.flash()
 
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
---

### Input Dialogs

Often times, users are asked to enter a single piece of information.
FXAlert has built-in functionality to retrieve this. Use `FXAlert.input()`

`FXAlert.input()` works just like the other methods except instead of 
`show()` or `showAndWait()` methods, there are specific methods for getting
a particular type of data.

```java
Optional<String> aString = FXAlert.input().withText("Enter a String:").showAndWaitString();
Optional<Double> aDouble = FXAlert.input().withText("Enter a Double:").showAndWaitDouble();
Optional<Integer> anInt = FXAlert.input().withText("Enter an Integer:").showAndWaitInteger();
```

As well as returning `Optional` values, the underlying input dialog requires that
users enter the correct data type before submission. The allowed type is determined 
by the appropriate `showAndWaitXXX` method. This prevents runtime errors when trying t
o parse the inputs.

---

### "Flash" notifications

These are notifications that cause a banner to be temporarily displayed on the lower-right
corner of the screen. The banner appears for a few seconds, then fades out of view.

These can be built and invoked like below:

```java
FXAlert.flash()
    .withHeader("Some Header Text")
    .withContent("Some more detailed content text...")
    .show();
```

Flash notifications can display custom icons, but can also use the same bundled JavaFX
icons that we see in `Alert`s. By default, the flash notification uses the `AlertType.INFORMATION`
icon, but we can specify which we want to see by using one of the below.

```java
FXAlert.flash().error()      // AlertType.ERROR icon
FXAlert.flash().warn()      // AlertType.WARNING icon
FXAlert.flash().confirm()  // AlertType.CONFIRMATION icon
FXAlert.flash().info()    // AlertType.INFORMATION icon (default)

FXAlert.flash().withGraphic(someNodeHere) // Use a custom icon
```

---

### Documentation

FXAlert is a really simple library; most methods simply wrap JavaFX Alerts. For this reason,
no separate documentation will be maintained. If you want to get a look at what's going on behind
the scenes, check out the [AlertBuilder](./src/main/java/com/dustinredmond/fxalert/AlertBuilder.java)
class, most of the action happens there.

Each method has thorough a Javadoc, whether it's public or private.

---
