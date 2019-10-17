/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

// Diner thread
public class Diners extends Thread{
    private int dinerId;
    private int arriveTime;
    private int burgers;
    private int fries;
    private int cokes;
    private int tableId;
    private Restaurant restaurant;
    private volatile boolean complete = false;

    public Diners(int dinerId, int arriveTime, int burgers, int fries, int cokes, Restaurant restaurant){
        this.dinerId = dinerId;
        this.arriveTime = arriveTime;
        this.burgers = burgers;
        this.fries = fries;
        this.cokes = cokes;
        this.restaurant = restaurant;
    }

    public int getDinerId(){
        return this.dinerId;
    }

    // Mark whether a diner has finished his eating in the restaurant
    public boolean isComplete(){
        return this.complete;
    }

    // Diners get tables
    public void setTables() throws InterruptedException{
        Tables table = restaurant.setTables();
        synchronized(table){
            TableObject tables = table.setTable(arriveTime);
            while(tables.getTime() == -1) {
                table.wait();
                tables = table.setTable(arriveTime);
            }
            arriveTime = tables.getTime();
            tableId = tables.getId();
            System.out.println("No." + dinerId + " Diner gets table No."+ tableId + " at " + arriveTime);
        }
    }

    // Print the time of a diner's food pick-up and leaving table
    public void orderProcess(int time){
        System.out.println("No." + dinerId + " Diner gets food at " + time);
        restaurant.setTables().leave(tableId, time + 30);
        System.out.println("No." + dinerId + " Diner leaves table at "+ (time + 30));
        complete = true;
    }

    @Override
    public void run(){
        Order order = new Order(arriveTime, burgers, fries, cokes, this);
        OrderList.addOrder(order);
    }
}
