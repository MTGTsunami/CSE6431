/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

public class FriesMachine {
    private static int time;

    public static int getTime(){
        return time;
    }

    public static synchronized int cookFries(int machineTime, int fries, int dinerId){
        int start;
        if(time > machineTime){
            start = time;
        }
        else{
            start = machineTime;
        }

        // 3 mins. per serving of fries
        time = start + 3 * fries;

        System.out.println("FriesMachine cooks diner No." + dinerId + " 's order from " + start + " to " + time);
        return time;
    }
}
