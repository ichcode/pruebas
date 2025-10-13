package org.ichcode.version2;

import java.awt.*;


class Ship extends Rectangle {
    private int dx = 0;

    public Ship(int x, int y) {
        super(x, y, 40, 20);
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void mover() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 760) x = 760;
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
}