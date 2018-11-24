module gui {
    requires core;
    requires javafx.controls;
    requires javafx.media;
    opens com.friendos.gui to javafx.graphics;
}