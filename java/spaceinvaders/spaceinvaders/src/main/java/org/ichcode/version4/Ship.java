package org.ichcode.version4;

import java.awt.*;
import java.awt.image.BufferedImage;


class Ship extends Rectangle {
    private int dx = 0;
    private final BufferedImage skin;

    public Ship(int x, int y, BufferedImage skin) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 80;
        this.skin = skin;
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
        if (skin != null) {
            g.drawImage(skin, x, y, width, height, null);
        } else {
            g.fillRect(x, y, width, height); // fallback
        }
    }
}