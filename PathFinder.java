import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

//Uses a stack to find city paths from chosen origin given by user in RequestedFlightPlan.txt
public class PathFinder {
    public static List<Path> findCity(DepartureCity[] cities, String originCity, String endCity) {
        List<Path> result = new ArrayList<>();
        Stack<Path> stack = new Stack<>();
        
        //Creating stack
        stack.push(new Path(originCity, 0, 0, originCity));
        
        //Iterative backtracking done below
        while (stack.isEmpty() == false) {
            Path current = stack.pop();
                
            if (current.getCityName().equals(endCity)) {
                result.add(current);
                continue;
            }
            
            //Grabs current city from DepartureCity object for path finding
            DepartureCity currentCity = findCity(cities, current.getCityName());
            
            //Gets every city and its edges
            //Iterates through origin city's edges until its gone through every edge
            //Adds cost and time between cities from each edgeCity object iterated
            //Pushes this new path onto the stack of paths
            if (currentCity != null) {
                for (Object o : currentCity.getArrivalCity()) {
                    EdgeCity edge = (EdgeCity) o;
                    String nextCity = edge.getCityName();
                    if (!current.getFullPath().contains(nextCity)) {
                        int totalCost = current.getTotalCost() + edge.getCost();
                        int totalTime = current.getTotalTime() + edge.getTime();
                        String totalPath = (current.getFullPath() + " -> " + nextCity);
                        stack.push(new Path(nextCity, totalCost, totalTime, totalPath));
                    }
                }
            }
        }
        return result;
    }
    
    private static DepartureCity findCity(DepartureCity[] cities, String name) {
        for (DepartureCity city : cities) {
            if (city.getCityName().equals(name)) {
                return city;
            }
        }
        return null;
    }
}