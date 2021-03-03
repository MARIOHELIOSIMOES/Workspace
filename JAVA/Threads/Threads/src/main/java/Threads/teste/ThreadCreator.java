package Threads.teste;

import Threads.PrintTask;

public class ThreadCreator {
    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintTask("task1"));
        Thread t2 = new Thread(new PrintTask("task2"));
        Thread t3 = new Thread(new PrintTask("task3"));
        
        System.out.println("Threads created, starting tasks.");
        
        
        t1.start();
        t2.start();
        t3.start();
        
        System.out.println("Tasks started, main ends.\n");
    }
}
