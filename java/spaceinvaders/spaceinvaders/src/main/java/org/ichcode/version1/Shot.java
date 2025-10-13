package org.ichcode.version1;

import java.awt.*;

class Shot extends Rectangle {
    public Shot(int x, int y) {
        super(x, y, 5, 10);
    }

    public void mover() {
        y -= 5;
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }
}