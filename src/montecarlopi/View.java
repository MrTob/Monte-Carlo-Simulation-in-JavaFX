package montecarlopi;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class View implements Observer {

    private Controller c;
    private BorderPane masterPane;
    private Label currPi, in, out, tries, exactPi = null;
    private int count = 0;

    private ListView<String> list;
    

    public View(Controller c) {
        this.c = c;
        initPane();
    }

    public Controller getController() {
        return c;
    }

    public BorderPane getPane() {
        return masterPane;
    }

    private void initPane() {
        masterPane = new BorderPane();
        currPi = new Label();
        in = new Label();
        out = new Label();
        tries = new Label();
        exactPi = new Label();

        currPi.setText("Approximation for Pi: 0");
        in.setText("Inside: 0");
        out.setText("Outside: 0");
        tries.setText("Attempt: 0");
        exactPi.setText("Exact value: 3.1415926");

        GridPane rightSide = new GridPane();
        rightSide.add(tries, 0, 0);
        rightSide.add(in, 0, 1);
        rightSide.add(out, 0, 2);
        rightSide.add(exactPi, 0, 3);
        rightSide.add(currPi, 0, 4);

        GridPane buttons = new GridPane();
        Button add1000, reset, auto, stop;

        GridPane ThreadList = new GridPane();
        Text text = new Text("running Threads");
        list = new ListView<>();
        list.setPrefSize(100, 100);

        VBox box = new VBox();
        box.getChildren().add(list);
        ThreadList.add(box, 0, 1);
        ThreadList.add(text, 0, 0);

        add1000 = new Button();
        reset = new Button();
        auto = new Button();
        stop = new Button();

        add1000.setText("+ 1000");
        stop.setText("stop Threads");
        reset.setText("Reset");
        auto.setText("generate Auto (Thread)");

        buttons.add(add1000, 1, 0);
        buttons.add(reset, 3, 0);
        buttons.add(auto, 4, 0);
        buttons.add(stop, 1, 1);
       

        rightSide.setVgap(5);
        rightSide.setHgap(5);
        rightSide.add(buttons, 0, 5);
        rightSide.add(ThreadList, 0, 6);

        masterPane.setRight(rightSide);

        masterPane.setCenter(c.getModel().getPane());

        add1000.setOnAction(listener -> {
            c.addThousand();
        });

        reset.setOnAction(listener -> {
            c.getModel().reset(list);       
        });

        auto.setOnAction(listener -> {     
                  c.getModel().generateAuto(list, count);
                  count++;        
        });

        stop.setOnAction(listener -> {
            c.getModel().stop(list);
            count=0;
        });

    }

    ;
    

    @Override
    public void update(Observable o, Object arg) {
        currPi.setText("Approximation for Pi: " + c.getModel().getPi());
        in.setText("Inside: " + c.getModel().getIn());
        out.setText("Outside: " + c.getModel().getOut());
        tries.setText("Attempt: " + c.getModel().getTries());
    }

}
