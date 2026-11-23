package org.example;


import HES.Systems.EnvironmentalSystem;

    public class Main {
        public static void main(String[] args) {
            EnvironmentalSystem system = new EnvironmentalSystem();

            System.out.println(
                    system.getTempFeed().getTemperature(0)
            );
        }
    }