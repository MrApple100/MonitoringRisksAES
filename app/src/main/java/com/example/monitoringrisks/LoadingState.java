package com.example.monitoringrisks;

import androidx.annotation.Nullable;

public class LoadingState {
    private Status status;
    private String msg;

    public LoadingState(Status status, @Nullable String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static LoadingState LOADED = new LoadingState(Status.SUCCESS,null);
    public static LoadingState LOADING = new LoadingState(Status.RUNNING,null);
    public static LoadingState error(String msg){
        return new LoadingState(Status.FAILED,msg);
    }
    enum Status{
        RUNNING,
        SUCCESS,
        FAILED
    }

}
