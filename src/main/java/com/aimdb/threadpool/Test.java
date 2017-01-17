package com.aimdb.threadpool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bbking on 16-12-17.
 */
public class Test {

    public static void main(String[] args) throws IOException {
    
    	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 15, 200,
    			TimeUnit.MILLISECONDS, new  ArrayBlockingQueue<Runnable>(5));
    	
    	for(int i=0;i<15;i++){
    		threadPoolExecutor.execute(new BasicTask(i));
    		System.out.println("poolsize:"+threadPoolExecutor.getPoolSize()+
    				"queuesize:"+threadPoolExecutor.getQueue().size()+
    				"completedtaskcount:"+threadPoolExecutor.getCompletedTaskCount());
    		
    	}
    	threadPoolExecutor.shutdown();

    }
}
