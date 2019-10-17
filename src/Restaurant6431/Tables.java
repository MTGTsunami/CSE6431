/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

public class Tables {
    private int[] tables;

    public Tables(int numTables){
        tables = new int[numTables];
    }

    public synchronized TableObject setTable(int time){
        // To save the minId and time in a TableObject
        TableObject temp = new TableObject(-1, -1);
        int minId = 0;

        // Sort and find the minimum time as well as the table id
        for(int i = 0; i < tables.length; i++){
            if(tables[i] >= 0) {
                if (tables[i] < tables[minId]) {
                    minId = i;
                }
            }
        }
        temp.setId(minId);
        if(tables[minId] >= 0) {
            if(tables[minId] > time){
                temp.setTime(tables[minId]);
            }
            else{
                temp.setTime(time);
            }
            tables[minId] = -1;
        }
        return temp;
    }

    // Release the table
    public synchronized void leave(int id, int time){
        tables[id] = time;
        notifyAll();
    }
}
