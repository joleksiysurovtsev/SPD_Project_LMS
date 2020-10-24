package com.lms.spd;

public class Main {
    public static void main(String[] args) {
       LMSTerminal lmsTerminal = new LMSTerminal();
        lmsTerminal.startLMS();
        Literature inter = new JournalArticle("Ахота","Проблемы рыбалки","fff", 5);
        System.out.println(inter.toString());
    }

}
