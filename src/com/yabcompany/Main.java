package com.yabcompany;


public class Main {

    public static void main(String[] args) {
        String graphName = "Points scored";
        String[] names = {"Team A", "Team B", "Team C", "Team D","Team E"};
        String[] param = {"Period 1", "Period 2","Period 3"};
        int[][] values = {{20, 10, 40}, {25, 50, 60}, {75, 30,90}, {40, 60, 20  }, {75,40,90}};

        GraphPrinter gp = new GraphPrinter();
        gp.pointGraph(graphName, names, param, values);


    }
}
