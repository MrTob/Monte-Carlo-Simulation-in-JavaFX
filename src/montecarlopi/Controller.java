package montecarlopi;

public class Controller {
    private Model m = null;
    private View v = null;
    
    public Controller() {
        m = new Model();
        v = new View(this);
        
        m.addObserver(v);
    }
    
    public Model getModel() {
        return m;
    }
    
    public View getView() {
        return v;
    }
    
    public void addOne() {
        m.setExit(false);
        m.generatePoint();
    }
    
    public void addThousand() {
        m.setExit(false);
        for(int i = 0; i < 1000; i++) {
            m.generatePoint();
        }
    }
    
 
}
