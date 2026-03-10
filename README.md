# Flight Path Finder

A Java-based flight routing system that models airline routes as a graph and computes optimal paths between cities based on cost or travel time.

## Features
- Graph representation using adjacency lists
- Depth-first search (DFS) path exploration
- Computes all possible routes between cities
- Sorts routes based on cost or travel time
- Reads flight data from input files
- Outputs best routes to a file

## Files

Main.java – Program entry point  
PathFinder.java – DFS path search algorithm  
Path.java – Stores path information  
DepartureCity.java – Graph node  
EdgeCity.java – Graph edge  

FlightData.txt – Flight route dataset  
RequestedFlightPlan.txt – Requested routes  
Output.txt – Generated results

## Example Output
Flight: Dallas, Houston (Time)

Path 1: Dallas -> Houston. Time: 51 Cost: 101
Path 2: Dallas -> Austin -> Houston. Time: 86 Cost: 193


## How to Run

Compile:

Run:

The program will read input data and generate the best routes in `Output.txt`.
