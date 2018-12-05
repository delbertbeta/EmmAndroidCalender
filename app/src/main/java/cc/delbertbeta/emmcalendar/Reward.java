package cc.delbertbeta.emmcalendar;

public class Reward {
    public Reward(int index, int day, int night) {
        this.index = index;
        this.day = day;
        this.night = night;
    }

    public int getIndex() {
        return index;
    }

    public int getDay() {
        return day;
    }

    public int getNight() {
        return night;
    }

    public int getSum() {
        return day + night;
    }

    private int index;
    private int day;
    private int night;

}
