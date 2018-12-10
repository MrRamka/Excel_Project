package com.yabcompany;


public class Main {

    public static void main(String[] args) {
        String graphName = "Points scored";
        String[] names = {"Team 1", "Team 2", "Team 3", "Team 4",};
        String[] param = {"Period 1", "Period 2"};
        int[][] values = {{50, 89}, {36, 24}, {12, 37}, {38, 67}};

        GraphPrinter.pointGraph(graphName, names, param, values);

    }
}
