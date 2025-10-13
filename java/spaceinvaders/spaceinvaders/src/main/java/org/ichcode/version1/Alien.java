package org.ichcode.version1;

import java.awt.*;

class Alien extends Rectangle {
    private int dx = 1;

    public Alien(int x, int y) {
        super(x, y, 10, 10);
    }

    public void move() {
        x += dx;
        if (x <= 0 || x >= 770) {
            dx = -dx;
            y += 20;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}