package com.example.ensi;


import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Path {

    private Point currentPosition;

    List<Point> predefinedPath;
    Queue<Point> path
            = new PriorityQueue<Point>();


    public Path(Point[] predefinedPath) {
        this.predefinedPath = Arrays.asList(predefinedPath);
        path.addAll(this.predefinedPath);
    }

    PID speedController = new PID(1, 0, 0, 0);

    //set any other PID configuration options here.
/*
    while(true){
        //get some sort of sensor value
        //set some sort of target value
       // double output=speedController.getOutput(path.element(), 10);
        //do something with the output
        Timer.delay(50);
    }
*/
    public List<Point> getPredefinedPath() {
        return predefinedPath;
    }

    public void setPredefinedPath(List<Point> predefinedPath) {
        this.predefinedPath = predefinedPath;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }


}
