package Threads.teste;

import Threads.ArrayWrite;
import Threads.SimpleArray;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {

    public static void main(String[] args) {
        SimpleArray sharedSimpleArray = new SimpleArray(6);
        
        ArrayWrite write1 = new ArrayWrite(1, sharedSimpleArray);
        ArrayWrite write2 = new ArrayWrite(11, sharedSimpleArray);
        
        
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(write1);
        executor.execute(write2);
        executor.shutdown();
        
        try{
            boolean taskEnded = executor.awaitTermination(1, TimeUnit.MINUTES);
            
            if(taskEnded){
                System.out.println(sharedSimpleArray);
            }else{
                System.out.println("Timed out while waiting for tasks to finish");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
}
