package montecarlopi;


import java.util.LinkedList;
import java.util.Observable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Model extends Observable{
    private Circle c;
    private LinkedList<Point> list;
    private Pane pane;
    private Canvas can;
    private int in, out, tries;
    private UpdateThread uT;
    private int count = 0;
    private boolean exit;
    public boolean isExit(){
        return exit;
    }
    
    public Model() {
        int size = 400;
        c = new Circle(size);
        list = new LinkedList<>();
        pane = new Pane();
        pane.setPrefSize(size, size);
        initPane();
        in = 0;
        out = 0; 
        tries = 0;
    }
    
    public Pane getPane() {
        return pane;
    }
    
    public int getIn() {
        return in;
    }
    
    public int getOut() {
        return out;
    }
    
    public int getTries() {
        return tries;
    }
    
    private void initPane() {
        initCanvas();
        pane.getChildren().add(can);
    }
    
    private void initCanvas() {
        can = new Canvas(pane.getPrefHeight(),pane.getPrefWidth());
        GraphicsContext gc = can.getGraphicsContext2D(); 
        gc.clearRect(0, 0, can.getWidth(), can.getHeight());
        gc.fill();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(0, 0, this.c.getDurchmesser() ,this.c.getDurchmesser());
        
        gc.strokeRect(0, 0, this.c.getDurchmesser() ,this.c.getDurchmesser());
    }
    public Point generatePoint() {
        if(exit)return null;
        
        double x = (Math.random() * c.getDurchmesser());
        double y = (Math.random() * c.getDurchmesser());
        
        Point p = new Point(x,y);
        addPoint(p);
        
        return p;
    }
    
    public void reset(ListView<String> list1) {
        
        list = new LinkedList<>();
        tries = 0; 
        in = 0;
        out = 0;
        
        GraphicsContext gc = can.getGraphicsContext2D();
        gc.clearRect(0, 0, can.getWidth(), can.getHeight());
      
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(0, 0, c.getDurchmesser(), c.getDurchmesser());
        gc.strokeRect(0, 0, c.getDurchmesser(), c.getDurchmesser());
        setExit(true);
        list1.getItems().clear();
        setChanged();
       notifyObservers();
    }
    
    public void addPoint(Point p) {
        list.add(p);
        boolean in = inOrout(p);
        tries++;
        
        GraphicsContext gc = can.getGraphicsContext2D();
        
       
        if(in){
             gc.setStroke(Color.VIOLET); //inside
        }else{
             gc.setStroke(Color.BLUE); //outside
        }
        gc.setLineWidth(1);
        gc.strokeRect((double)p.getX(),(double)p.getY(), 1, 1);
        
        setChanged();
        notifyObservers();
    }
    
    public void generateAuto (ListView<String> list,int count){
        setExit(false);
            UpdateThread ut = new UpdateThread(this, list, count);
            count++;
            ut.start();
       
        
           
    }
    
    private boolean inOrout(Point p) {
        if(c.isInside(p)) {
            in++;
            return true;
        }else{
            out++;
        }
        return false;
    }
    
    public double getPi() {
        if(out != 0) {
           return ((double)in / ((double)out+(double)in)) * 4; 
        }
        
        return 0;
    }


    void stop(ListView<String> list) {
        list.getItems().clear();
        exit=true;   
    }

    public void setExit(boolean exit) {
        this.exit=exit;
    }

 
        
    
    
    
}
