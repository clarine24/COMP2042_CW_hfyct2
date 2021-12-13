module com.game.BrickDestroy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.BrickDestroy to javafx.fxml;
    exports com.game.BrickDestroy;
    opens com.game.BrickDestroy.Model to javafx.fxml;
    exports com.game.BrickDestroy.Model;
    exports com.game.BrickDestroy.Controller;
    opens com.game.BrickDestroy.Controller to javafx.fxml;
}