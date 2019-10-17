/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

public class Order implements Comparable<Order>{
    private int burgers;
    private int fries;
    private int cokes;
    private int time;
    private Diners diner;

    public Order(int time, int burgers, int fries, int cokes, Diners diner){
        this.burgers = burgers;
        this.fries = fries;
        this.cokes = cokes;
        this.time = time;
        this.diner = diner;
    }

    public int getBurgers(){
        return this.burgers;
    }

    public int getFries() {
        return this.fries;
    }

    public int getCoke(){
        return this.cokes;
    }

    public int getTime() {
        return this.time;
    }

    public Diners getDiner() {
        return this.diner;
    }

    // Sort the orders by the time
    @Override
    public int compareTo(Order order){
        if(this.time > order.getTime())
            return 1;
        else if(this.time == order.getTime())
            return 0;
        else
            return -1;
    }
}

