module gui {
    requires java.logging;
    requires core;
    requires resources;
    requires javafx.controls;
    opens com.friendos.gui to javafx.graphics;
}