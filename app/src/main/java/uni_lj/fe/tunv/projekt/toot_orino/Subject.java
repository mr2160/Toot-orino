package uni_lj.fe.tunv.projekt.toot_orino;

public class Subject {
    private String name;
    private Number hourlyRate;

    public Subject(String name, Number hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public Subject(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Number hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
