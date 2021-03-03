package Threads;

import java.util.Arrays;
import java.util.Random;

public class SimpleArraySynchronized {

     private final int array[];
     private int writeIndex = 0;
     private final static Random generator = new Random();
     
     public SimpleArraySynchronized(int size){
         array = new int[size];
     }
     
     public synchronized void add(int value){
         int posicao = writeIndex;
         try{
             Thread.sleep(generator.nextInt(500));
         }catch(Exception e){
             e.printStackTrace();
         }
         array[posicao] = value;
         System.out.printf("Next write index: %d\n", writeIndex);
     }
     public String toString(){
         return "\nContexts of SimpleArray:\n"
                 + Arrays.toString(array);
     }
     
}
