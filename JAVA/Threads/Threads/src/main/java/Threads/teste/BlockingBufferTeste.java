package Threads.teste;

import Threads.BlockingBuffer;
import Threads.Consumer;
import Threads.Producer;
import Threads.intervace.Buffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingBufferTeste {

    public static void main(String[] args) {
        ExecutorService application = Executors.newCachedThreadPool();
        Buffer sharedLocation = new BlockingBuffer();
        application.execute(new Producer(sharedLocation));
        application.execute(new Consumer(sharedLocation));
        
        application.shutdown();
    }
}
