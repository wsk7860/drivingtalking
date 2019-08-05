package com.drivingtalking.util;

public class PagerManager {

    private static final ThreadLocal<PagerSupporter> pagerSupport = new ThreadLocal<>();

    public static <T> T paging(PagerSupporter pagerSupporter,PaginationRunnable<T> runnable){
        pagerSupport.set(pagerSupporter);
        return  runnable.run();
    }

    private PagerManager(){

    }

    public static PagerSupporter  getPagerSupport(){
        return pagerSupport.get();
    }
}
