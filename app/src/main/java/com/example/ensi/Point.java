package com.example.ensi;

public class Point {

    private int x, y;
    public boolean isBreakpoint = false;
    private int delay = 1000;
    public boolean isObstacle = false;
    private int width = 1;
    private int height = 1;

    public void task(){
        return;
    };

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Point(int x, int y, int delay, int width, int height){
        this.x = x;
        this.y = y;
    }


}
