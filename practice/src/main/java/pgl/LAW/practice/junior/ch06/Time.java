package pgl.LAW.practice.junior.ch06;

public class Time {
    private int hour;
    private int minute;
    private int second;
    // 3 parameters for construct method
    public Time(int h, int m, int s) {
        setHour(h);
        setMinute(m);
        setSecond(s);
    }

    public Time(Time time) {
        this(time.getHour(), time.getMinute(), time.getSecond());
    }

    public void setHour(int h) {
        hour = ((h >= 0 && h < 24)?h:0);
    }
    public int getHour() {
        return hour;
    }
    public void setMinute(int m) {
        minute = ((m >= 0 && m < 60)?m:0);
    }
    public int getMinute() {
        return minute;
    }
    public void setSecond(int s) {
        second = ((s >= 0 && s < 60)?s:0);
    }
    public int getSecond() {
        return second;
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    public String toString() {
        return String.format("%d:%02d:%02d %s",
                ((getHour()==0||getHour()==12)?12:getHour()%12),
                getMinute(), getSecond(), (getHour()<12?"AM":"PM"));
    }
}
