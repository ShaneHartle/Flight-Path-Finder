class EdgeCity {
    private String cityName;
    private int cost;
    private int time;
    
    EdgeCity(String cityName, int cost, int time) {
        this.cityName = cityName;
        this.time = time;
        this.cost = cost;
    }

    public String getCityName() { return cityName; }
    
    public int getCost() { return cost; }
    
    public int getTime() { return time; }
}