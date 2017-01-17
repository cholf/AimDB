package com.aimdb.threadpool;

public class BasicTask implements Runnable {
	private int num;
	public  BasicTask (int num){
		this.num = num;
	}
	public void run() {
		
		System.out.println("running-num: "+num);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    	System.out.println("run end-num :"+num);
	}

}
