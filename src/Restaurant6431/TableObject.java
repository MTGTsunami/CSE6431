/* This is the project Restaurant 6431 created by Yifei Diao on Apr.13th */
package Restaurant6431;

public class TableObject{
    private int id;
    private int time;

    public TableObject(int id, int time){
        this.id = id;
        this.time = time;
    }

    public int getId(){
        return this.id;
    }

    public int getTime(){
        return this.time;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTime(int time){
        this.time = time;
    }
}
