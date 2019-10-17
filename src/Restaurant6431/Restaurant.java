/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */

package Restaurant6431;

import java.io.BufferedReader;
import java.io.FileReader;


public class Restaurant{
    // The properties of the restaurant: tables & cooks & machines (fixed # of machines, no need to initialize)
    private Tables table;
    private Cooks[] cooks;

    Restaurant(int numTables, int numCooks){
        this.table = new Tables(numTables);
        this.cooks = new Cooks[numCooks];
    }

    public Tables setTables(){
        return this.table;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        // Read # of Diners
        int numDiners = Integer.parseInt(br.readLine().trim());
        Diners[] diners = new Diners[numDiners];

        // Read # of Tables
        int numTables = Integer.parseInt(br.readLine().trim());

        // Read # of Cooks
        int numCooks = Integer.parseInt(br.readLine().trim());

        // Create a restaurant
        Restaurant restaurant = new Restaurant(numTables, numCooks);

        // Create a thread for each cook
        for(int i = 0; i < numCooks; i++){
            restaurant.cooks[i] = new Cooks(i);
            restaurant.cooks[i].start();
        }

        // Read the Diners
        for(int i = 0; i < numDiners; i++){
            String[] str = br.readLine().split(" ");
            int arriveTime = Integer.parseInt(str[0]);
            int burgers = Integer.parseInt(str[1]);
            int fries = Integer.parseInt(str[2]);
            int cokes = Integer.parseInt(str[3]);
            diners[i] = new Diners(i, arriveTime, burgers, fries, cokes, restaurant);
            diners[i].setTables();
            diners[i].run();
            Thread.sleep(3);
        }
        br.close();

        // Check whether all orders are completed
        for(int i = 0; i < numDiners; i++){
            while(true){
                if(diners[i].isComplete()) {
                    break;
                }
            }
        }

        // Let all cooks stop working
        for(int i = 0; i < numCooks; i++){
            restaurant.cooks[i].stopWorking();
        }
    }
}
