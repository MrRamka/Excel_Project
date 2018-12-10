package com.yabcompany;


public class Main {

    public static void main(String[] args) {
        String graphName = "Points scored";
        String[] names = {"Team 1", "Team 2", "Team 3", "Team 4",};
        String[] param = {"Period 1", "Period 2"};
        int[][] values = {{100, 10}, {25, 50}, {75, 30}, {40, 70}};

        GraphPrinter.pointGraph(graphName, names, param, values);

    }
}
