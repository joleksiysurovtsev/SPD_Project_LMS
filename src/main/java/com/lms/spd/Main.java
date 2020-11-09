package com.lms.spd;

public class Main {

    public static void main(String[] args) {
//        System.out.println(" +-------------------------------------------------------------------------------------------------------------------------+");
//        System.out.println(" |    Date       |  Lecture type      | Lecture number |                          Lecture title           | Lecturer name  |");
//        System.out.println(" +-------------------------------------------------------------------------------------------------------------------------+");
//        System.out.format(" |%15s|%20s|№: %13d|%50s|%16s| ", "Date:", "JAVA_CONCURRENCY", 8, "50 имволов","Имя лектора");
//        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+");
        LMSTerminal lmsTerminal = new LMSTerminal();
        lmsTerminal.startLMS();
    }
}
