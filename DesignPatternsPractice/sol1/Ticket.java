package sol1;

public class Ticket {
    private int distance;
    private double price;

    public Ticket(int distance) {
        this.distance = Math.max(0, distance);
        if (this.distance>300){
            this.price = (float) this.distance * 0.8;
        }else if (this.distance>200){
            this.price = (float) this.distance * 0.9;
        }else if (this.distance>100){
            this.price = (float)this.distance * 0.95;
        }else{
            this.price = (double) distance;
        }
    }

    public double getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }
}
