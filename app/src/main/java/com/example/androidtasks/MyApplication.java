package com.example.androidtasks;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningTaskInfo;

public class MyApplication extends Application{

    private HashMap<Integer, Stack<MainActivity>> tasks;

    private ActivityManager manager;

    private boolean intentFilterMode;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        tasks = new HashMap<Integer, Stack<MainActivity>>();
    }

    public void pushToStack(MainActivity activity){
        int currentTaskId = getCurrentTaskId();
        if(!tasks.containsKey(currentTaskId)){
            tasks.put(currentTaskId, new Stack<MainActivity>());
        }
        Stack<MainActivity> stack = tasks.get(currentTaskId);
        stack.add(activity);
    }

    public int getCurrentTaskId() {
        List<RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
        RunningTaskInfo runningTask = runningTasks.get(0);
        return runningTask.id;
    }

    public void removeFromStack(MainActivity activity){
        Stack<MainActivity> stack = tasks.get(getCurrentTaskId());
        if(stack != null){
            stack.remove(activity);
        }
    }

    public Stack<MainActivity> getCurrentTask(){
        return tasks.get(getCurrentTaskId());
    }

    public void toggleIntentFilterMode(){
        intentFilterMode = !intentFilterMode;
    }

    public boolean isIntentFilterMode(){
        return intentFilterMode;
    }

}
