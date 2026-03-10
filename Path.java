import java.util.*;

public class Path {
    private String cityName;
    private int totalCost; 
    private int totalTime;
    private String fullPath;
    
    public Path(String cityName, int totalCost, int totalTime, String fullPath) {
        this.cityName = cityName;
        this.totalCost = totalCost;
        this.totalTime = totalTime;
        this.fullPath = fullPath;
    }
    
    public String getCityName() { return cityName; }
    public int getTotalCost() { return totalCost; }
    public int getTotalTime() { return totalTime; }
    public String getFullPath() { return fullPath; }
}