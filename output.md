I've attempted to compile the project again, including the JUnit JARs. However, the compilation still fails with "module not found" errors for `javafx.fxml`, `javafx.graphics`, and `javafx.controls`.

**This error indicates that the JavaFX modules are not being correctly resolved by the Java compiler (`javac`), and is not related to the JUnit setup.** The JUnit JARs (which you've placed in the `lib` folder) are on the classpath, but `javac` cannot compile any JavaFX-dependent classes (like `com.codecademy.gui.GUI.java`) until the JavaFX SDK is correctly configured as modules.

**To resolve this, please carefully review the "Building and Running" section in the `GEMINI.md` file.** Pay close attention to:
*   The `--module-path` argument in the `javac` command, ensuring that `C:\Users\Dayal\Documents\Java\javafx-sdk-22.0.1\lib` is the correct and complete path to your JavaFX SDK's `lib` directory.
*   The `--add-modules javafx.controls,javafx.fxml,javafx.graphics` argument, confirming these modules are indeed present in your JavaFX SDK.

If command-line compilation continues to be problematic, **using an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse is strongly recommended.** IDEs typically handle the complex JavaFX module path configuration automatically, making development and testing much smoother.

Once the main project compiles successfully, we can then proceed to compile and run the unit tests.