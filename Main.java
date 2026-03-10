import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "FlightData.txt";
        File f = new File(fileName);
        int lineCount = readFileSize(f);
        
        
        //Get all departure cities. This is the main array, and each box of the array
        //has a linked list that holds all of the cities edges (places you can fly)
        String[] cityData = new String[lineCount];
        try {
            Scanner scanner = new Scanner(f);
            
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            int counter = 0;
            while (scanner.hasNextLine() && counter < lineCount) {
                cityData[counter] = scanner.nextLine();
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String[] departureCities = new String[lineCount];
        int cityCount = 0;
        
        for (int i = 0; i < lineCount; i++) {
            String[] split = cityData[i].split("\\|");
            String departure = split[0];
            
            boolean cityCheck = false;
            for(int j = 0; j < cityCount; j++) {
                if (departureCities[j].equals(departure)) {
                    cityCheck = true;
                    break;
                }
            }
            
            if (!cityCheck) {
                departureCities[cityCount] = departure;
                cityCount++;
            }
        }
        
        DepartureCity[] cityArray = new DepartureCity[cityCount];
        
        for(int i = 0; i < cityCount; i++) {
            cityArray[i] = new DepartureCity(departureCities[i]);
        }
        
        //Take collected data and split it into pieces that are placed in each object instance of 
        //DepartureCity objects
        for (String data : cityData) {
            String[] split = data.split("\\|");
            String departure = split[0];
            String arrival = split[1];
            int cost = Integer.parseInt(split[2]);
            int time = Integer.parseInt(split[3]);
            
            for (DepartureCity city : cityArray) {
                if (city.getCityName().equals(departure)) {
                    city.addEdgeCity(new EdgeCity(arrival, cost, time));
                    break;
                }
            }
        }
        
        //Similar to previous code but with RequestedFlightPlan
        String plannerFile = "RequestedFlightPlan.txt";
        File plan = new File(plannerFile);
        int planCount = readFileSize(plan);
        
        String planData[] = new String[planCount];
        try {
            Scanner scanner = new Scanner(plan);
            
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            int counter = 0;
            while (scanner.hasNextLine() && counter < planCount) {
                planData[counter] = scanner.nextLine();
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //Store RequestedFlightPlan data into variables for PathFinder to check and make paths
        //Then output the paths, cost, and time based on RequestedFlightPlan.txt to user
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter("Output.txt"));
            for (String data : planData) {
                String parts[] = data.split("\\|");
                String departure = parts[0];
                String arrival = parts[1];
                String priority = parts[2];
                
                String priorityString = "";
                List<Path> everyPath = PathFinder.findCity(cityArray, departure, arrival);
                
                if (priority.equals("C")) {
                    everyPath.sort(Comparator.comparingInt(Path::getTotalCost));
                    priorityString = "Cost";
                } else {
                    everyPath.sort(Comparator.comparingInt(Path::getTotalTime));
                    priorityString = "Time";
                }
                
                if (everyPath.isEmpty()) {
                    writer.println("No path found between " + departure + " to " + arrival);
                    writer.println();
                } else {
                    writer.println("Flight: " + departure + ", " + arrival + " (" + priorityString + ")");
                    int plans = Math.min(2, everyPath.size());
                    for (int i = 0; (i - 1) < plans; i++) {
                        Path p = everyPath.get(i);
                        writer.println("Path " + (i + 1) + ": " + p.getFullPath() + ". Time: " + p.getTotalTime() + " Cost: " + p.getTotalCost());
                    }
                    writer.println();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Basic file read method for checking file size
    private static int readFileSize(File dataFile) {
        int lineCount = 0;
        try {
            Scanner scanner = new Scanner(dataFile);
        
            if (scanner.hasNextInt()) {
                int line = scanner.nextInt();
                lineCount += line;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
}


    