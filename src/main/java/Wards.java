import HES.Feeds.TemperatureFeed;
import HES.Systems.EnvironmentalSystem;

public class Wards {
    private Float IdealTemp;
    private String Name;
    private Float CurrentTemp;
    private int ID;

    public Wards(String name, int id, Float idealTemp) {
        Name = name;
        IdealTemp = idealTemp;
        ID = id;
        CurrentTemp = 0.0F;
    }

    // Get Ward's name
    public String getName() {
        return Name;
    }
    public Float getCurrentTemp() {
        return CurrentTemp;
    }

    public void setCurrentTemp(Float currentTemp){
        CurrentTemp = currentTemp;
    }

    public int getID() {
        return ID;
    }
    // Get the Ward's Ideal temperature
    public Float getIdealTemp() {
        return IdealTemp;
    }
}


