package montecarlopi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MontecarloPi extends Application { 
    @Override
    public void start(Stage primaryStage) {
        
        Controller c = new Controller();
        Pane p = c.getView().getPane();
        Scene scene = new Scene(p, 680, 400);
       
        primaryStage.setTitle("Calculation of Pi");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {        
        launch(args);
    }
    
}
