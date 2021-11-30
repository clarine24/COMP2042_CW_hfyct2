module com.game.BrickDestroy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.BrickDestroy to javafx.fxml;
    exports com.game.BrickDestroy;
}