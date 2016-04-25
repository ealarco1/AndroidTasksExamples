package com.example.androidtasks;

import java.util.Stack;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TaskInfoDisplayer implements Runnable {

    String LOG_TAG = "AndroidTasksExamples";


    private final MyApplication app;
    private final TextView taskIdField;
    private final LinearLayout taskView;

    public TaskInfoDisplayer(MainActivity baseActivity) {
        app = (MyApplication) baseActivity.getApplication();
        taskIdField = (TextView) baseActivity.findViewById(R.id.task_id_field);
        taskView = (LinearLayout) baseActivity.findViewById(R.id.task_view);
        taskView.removeAllViews();
    }

    @Override
    public void run() {
        Log.v(LOG_TAG, "===============================");
        showCurrentTaskId();
        showCurrentTaskActivites();
        Log.v(LOG_TAG, "===============================");
    }

    private void showCurrentTaskId() {
        int taskId = app.getCurrentTaskId();
        String taskMessage = "Activities in current task (id: " + taskId + ")";
        taskIdField.setText("Task id: " + taskId);
        Log.v(LOG_TAG, taskMessage);
    }

    private void showCurrentTaskActivites() {
        Stack<MainActivity> task = app.getCurrentTask();
        for (int location = task.size() - 1; location >= 0; location--) {
            MainActivity activity = task.get(location);
            showActivityDetails(activity);
        }
    }

    private void showActivityDetails(MainActivity activity) {
        String activityName = activity.getClass().getSimpleName();
        Log.v(LOG_TAG, activityName);
        ImageView activityRepresentation = getActivityRepresentation(activity);
        taskView.addView(activityRepresentation);
    }

    private ImageView getActivityRepresentation(MainActivity activity) {
        ImageView image = new ImageView(activity);
        int color = activity.getBackgroundColour();
        image.setBackgroundResource(color);
        LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 10);
        params.setMargins(2, 2, 2, 2);
        image.setLayoutParams(params);
        return image;
    }
}
