package Threads;

public class ArrayWriteSynchronized implements Runnable{
    private final SimpleArraySynchronized sharedSimpleArray;
    private final int startValue;
    
    
    public  ArrayWriteSynchronized(int value, SimpleArraySynchronized array){
        startValue = value;
        sharedSimpleArray = array;
    }
    
    
    @Override
    public void run() {
        for(int i = startValue; i< startValue+3; i++){
            sharedSimpleArray.add(i);
        }
    }
}
