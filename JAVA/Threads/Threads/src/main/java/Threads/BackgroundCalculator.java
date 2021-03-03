package Threads;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class BackgroundCalculator extends SwingWorker<Long, Object> {

    private final int n;
    private final JLabel resultJLabel;
    
    public BackgroundCalculator(int number, JLabel label){
        n = number;
        resultJLabel = label;
    }
    
    @Override
    protected Long doInBackground() throws Exception {
        long nthFib = fibonacci(n);
        //return String.valueOf(nthFib);
        return nthFib;
    }
    
    @Override
    protected void done(){
        try{
            resultJLabel.setText(get().toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private long fibonacci(int n) {
        if(n == 0|| n ==1){
            return n;
        }else{
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

}
