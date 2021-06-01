package main.object;

public class Desk {
    private String status;
    private String date;
    private int seatNum;

    public Desk(){}
    public Desk(String status, String date, int seatNum){
        this.status = status;
        this.date = date;
        this.seatNum = seatNum;
    }

    public void setDate(String date){
        this.date = date;
    }
    public void setSeatNum(int seatNum){
        this.seatNum = seatNum;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public int getSeatNum() {
        return seatNum;
    }
}

