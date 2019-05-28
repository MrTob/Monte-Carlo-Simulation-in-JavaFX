package montecarlopi;
public class Circle {
    private double diameter;
    
    public Circle(double diameter) { 
        this.diameter = diameter;
    }
    
    public double getRadius() {
        return diameter/2;
    }
    
    public double getDurchmesser() {
        return diameter;
    }
    
    public boolean isInside(Point p) {
      
        double x = p.getX();
        double y = p.getY();
        
        double a = ((float)diameter/2f)-(float)x;
        double b = ((float)diameter/2f)-(float)y;
        
        double c = (Math.sqrt(Math.pow(a, 2f) + (Math.pow(b, 2f))));
        
        
        if(((double)diameter/2f) >= c) {
            return true;
        }
        
        return false;
    }
    
    
    
}
