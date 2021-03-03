package Threads.teste;

import Threads.ArrayWrite;
import Threads.ArrayWriteSynchronized;
import Threads.SimpleArray;
import Threads.SimpleArraySynchronized;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArraySynchronizedTest {

    public static void main(String[] args) {
        SimpleArraySynchronized sharedSimpleArray = new SimpleArraySynchronized(6);
        
        ArrayWriteSynchronized write1 = new ArrayWriteSynchronized(1, sharedSimpleArray);
        ArrayWriteSynchronized write2 = new ArrayWriteSynchronized(11, sharedSimpleArray);
        
        
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
