package com.yabcompany;


public class Main {

    public static void main(String[] args) {
        String graphName = "Points scored";
        String[] names = {"Team 1", "Team 2", "Team 3", "Team 4","Team 5"};
        String[] param = {"Period 1", "Period 2","Period 3"};
        int[][] values = {{20, 10, 40}, {25, 50, 60}, {75, 30,90}, {40, 60, 20  }, {75,40,100}};

        GraphPrinter.pointGraph(graphName, names, param, values);

    }
}
