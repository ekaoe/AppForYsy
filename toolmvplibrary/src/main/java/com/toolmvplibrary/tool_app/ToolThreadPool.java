package com.toolmvplibrary.tool_app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToolThreadPool {

    private static ToolThreadPool instance;
    public static ToolThreadPool getInstance() {
        if (instance == null) {
            instance = new ToolThreadPool();
        }
        return instance;
    }

    /**
     * 线程池
     */
    private ExecutorService mExecutorService;

    public ToolThreadPool() {
        mExecutorService = Executors.newFixedThreadPool(15);
    }

    /**
     * 获取线程池
     *
     * @return
     */
    public final ExecutorService getExecutorService() {
        return mExecutorService;
    }

    public void exeRunable(Runnable run) {
        getExecutorService().execute(run);
    }
}