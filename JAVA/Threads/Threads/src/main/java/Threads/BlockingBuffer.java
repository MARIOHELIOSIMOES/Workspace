package Threads;

import Threads.intervace.Buffer;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer{

    private final ArrayBlockingQueue<Integer> buffer; //buffer compartilhado
    
    public BlockingBuffer(){
        buffer = new ArrayBlockingQueue<Integer>(1);
    }
    
    @Override
    public void set(int value) throws InterruptedException {
        buffer.put(value);
        System.out.printf("%s %2d\t%s%d\n", "Producer writes ", value, "Buffer cells occupied: ", buffer.size());
    }

    @Override
    public int get() throws InterruptedException {
        int readValeu = buffer.take();
        System.out.printf("%s %2d\t%s%d\n", "Consumer reads ", readValeu, "Buffer cells occupied: ",buffer.size());
        return readValeu;
    }

}
