/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

// Cooks thread
public class Cooks extends Thread {
    private int id;

    public Cooks(int id){
        this.id = id;
    }

    private int processing(Order order, int dinerId){
        int time = order.getTime();

        // Mark the state of three kinds of machines
        boolean[] orderFinish = new boolean[3];
        if(order.getBurgers() == 0){
            orderFinish[0] = true;
        }
        if(order.getFries() == 0){
            orderFinish[1] = true;
        }
        if(order.getCoke() == 0){
            orderFinish[2] = true;
        }

        int[] start = new int[3];
        while(!(orderFinish[0] && orderFinish[1] && orderFinish[2])){
            start[0] = BurgerMachine.getTime();
            start[1] = FriesMachine.getTime();
            start[2] = CokeMachine.getTime();

            int idx = leastTimeMachine(orderFinish, start);

            if(idx == 0){
                time = BurgerMachine.cookBurgers(time, order.getBurgers(), dinerId);
                orderFinish[0] = true;
            }
            else if(idx == 1){
                time = FriesMachine.cookFries(time, order.getFries(), dinerId);
                orderFinish[1] = true;
            }
            else{
                time = CokeMachine.serveCokes(time, order.getCoke(), dinerId);
                orderFinish[2] = true;
            }
        }
        return time;
    }

    // Find the kind of machine with the minimum starting time
    private int leastTimeMachine(boolean[] complete, int[] start){
        int minTime = -1;
        int idx = 0;
        for(int i = 1; i < complete.length; i++){
            if(!complete[i] && (start[i] < minTime || minTime < 0)){
                minTime = start[i];
                idx = i;
            }
        }
        return idx;
    }

    public void stopWorking(){
        this.interrupt();
    }

    @Override
    public void run(){
        try{
            while(true){
                Order order = OrderList.getOrder();
                if(order == null){
                    synchronized (OrderList.class){
                        OrderList.class.wait();
                    }
                }
                else{
                    int dinerId = order.getDiner().getDinerId();
                    System.out.println("No." + id + " Cook cooks dinner for diner No." + dinerId + " at " + order.getTime());
                    int time = processing(order, dinerId);
                    order.getDiner().orderProcess(time);
                }
            }
        } catch(InterruptedException e){
            stopWorking();
        }
    }
}
