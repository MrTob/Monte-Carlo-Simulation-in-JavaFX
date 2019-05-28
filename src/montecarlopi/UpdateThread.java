
package montecarlopi;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ListView;

/**
 *
 * @author tobia
 */
public class UpdateThread extends Thread {

    private int i = 0;
    private Model test;

    public UpdateThread(Model test, ListView<String> list, int count) {
        setDaemon(true);
        setName("Thread " + count);
        this.test = test;
        list.getItems().add(getName());
    }

    @Override
    public void run() {

        while (!this.isInterrupted()) {

            if (test.isExit()) {
                return;
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    test.generatePoint();
                }
            });

            try {

                sleep(TimeUnit.MILLISECONDS.toMillis(5));
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
