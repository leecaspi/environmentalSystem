import HES.Systems.EnvironmentalSystem;

import java.util.ArrayList;
import java.util.List;

public class Controls {
    private final EnvironmentalSystem system;
    protected final Wards[] wards;

    public Controls() {
        system = new EnvironmentalSystem();
        wards = new Wards[]{new Wards("North Ward", 0, 23.0f),
                new Wards("Central Ward", 1, 22.0f),
                new Wards("South Ward", 2, 20.0f)
        };
    }

    public void checkTemp() {
        for (Wards w : wards) {
            Float currentTemp = system.getTempFeed().getTemperature(w.getID());
            Float idial = w.getIdealTemp();
            w.setCurrentTemp(currentTemp);
            if (currentTemp < (idial - 0.5f)) {
                system.turnHeatingOn(w.getID(), true);
            } else if (currentTemp > (idial + 0.5f)) {
                {
                    system.turnHeatingOn(w.getID(), false);
                }
            }
        }
    }

    public void checkIfHumid () {
        int inside = system.getHumidityFeed().getInsideHumidity();
        int outside = system.getHumidityFeed().getOutsideHumidity();
        system.turnDehumidifierOn(inside > outside + 10);
        }

    public int getInHumidity(){ return system.getHumidityFeed().getInsideHumidity();}
    public int getOutHumidity(){ return system.getHumidityFeed().getOutsideHumidity();}
    public Float getPollution(){ return system.getPollutionFeed().getPollution();}

    public void checkPollution () {
        float pollutantP = system.getPollutionFeed().getPollution();
        system.turnAirPurifierOn(pollutantP > 5.0);
    }
    public String isHeatingOn(int wardID){
        if(system.isHeatingOn(wardID)){
            return "ON";
        }else {return "OFF";}
    }
    public String isDehumidifierOn(){
        if(system.isDehumidifierOn()){
            return "ON";
        }else {return "OFF";}
    }
    public String isAirPurifierOn(){
        if(system.isAirPurifierOn()){
            return "ON";
        }else {return "OFF";}
    }



}
