module Sodoban {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires annotations;
    opens Sokoban to javafx.fxml, javafx.graphics, javafx.controls,java.base;
    exports Sokoban;
    exports Sokoban.Controller;
    opens Sokoban.Controller to java.base, javafx.controls, javafx.fxml, javafx.graphics;
    exports Sokoban.Model;
    opens Sokoban.Model to java.base, javafx.controls, javafx.fxml, javafx.graphics;
}