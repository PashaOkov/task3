package com.company;

public class Main {

    public static void main(String[] args) {

        ArgumentProcess arguments = new ArgumentProcess(args);
        MySaxParser parser = new MySaxParser();
        parser.parse(arguments);
    }
}
