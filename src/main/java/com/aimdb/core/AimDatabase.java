package com.aimdb.core;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bbking on 17-1-23.
 */
public class AimDatabase {
    private final AtomicLong atomicLong;
    public  AimDatabase(){
        atomicLong = new AtomicLong();
    }

}
