package Threads.teste;

import Threads.PrintTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {
    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintTask("task1"));
        Thread t2 = new Thread(new PrintTask("task2"));
        Thread t3 = new Thread(new PrintTask("task3"));
        
        System.out.println("Starting Executor");
        
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        
        threadExecutor.execute(t1);
        threadExecutor.execute(t2);
        threadExecutor.execute(t3);
        
        threadExecutor.shutdown();
        
        System.out.println("Tasks started, main ends. \n");
        
    }
}
