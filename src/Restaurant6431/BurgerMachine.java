/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

public class BurgerMachine {
    private static int time;

    public static int getTime(){
        return time;
    }

    public static synchronized int cookBurgers(int machineTime, int burgers, int dinerId){
        int start;
        if(time > machineTime){
            start = time;
        }
        else{
            start = machineTime;
        }

        // 5 mins. per serving of burgers
        time = start + 5 * burgers;

        System.out.println("BurgersMachine cooks diner No." + dinerId + " 's order from " + start + " to " + time);
        return time;
    }
}
