package main.object;

public class Desk {
    private int deskId;
    private String status;
    private String date;
    private int seatNum;
    private String currentDate;

    public Desk(){}
    public Desk(int deskId, String status, String date, int seatNum, String currentDate){
        this.deskId = deskId;
        this.status = status;
        this.date = date;
        this.seatNum = seatNum;
        this.currentDate = currentDate;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeskId() {
        return deskId;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}

