package com.example.ensi;

public class Point {

    private float x, y;
    public boolean isBreakpoint = false;
    private int delay = 1000;
    public boolean isObstacle = false;

    public void task(){
        return;
    };

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Point(int x, int y, int delay){
        this.x = x;
        this.y = y;
        this.delay = delay;
    }


}
