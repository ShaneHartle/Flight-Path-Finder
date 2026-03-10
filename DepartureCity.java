import java.util.LinkedList;

class DepartureCity {
    private String cityName;
    private LinkedList<EdgeCity> arrivalCity;
    
    DepartureCity(String cityName) {
        this.cityName = cityName;
        this.arrivalCity = new LinkedList<>();
    }
    
    public void setCityName(String cityName) { this.cityName = cityName; }
    public String getCityName() { return cityName; }
    
    public void setArrivalCity(LinkedList arrivalCity) { this.arrivalCity = arrivalCity; }
    public LinkedList getArrivalCity() { return arrivalCity; }
    
    public void addEdgeCity(EdgeCity newEdge) {
        arrivalCity.add(newEdge);
    }
}