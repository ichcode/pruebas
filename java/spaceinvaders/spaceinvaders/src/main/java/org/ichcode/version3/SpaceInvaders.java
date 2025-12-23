package org.ichcode.version3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener {
    private final int width = 800;
    private final int height = 600;
    private final Timer timer;
    private final Ship ship;
    private final ArrayList<Alien> aliens;
    private final ArrayList<Shot> shots;
    private boolean endGame = false;
    private int score = 0;

    public SpaceInvaders() {

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        timer = new Timer(1000 / 60, this);
        ship = new Ship(width / 2, height - 50);
        aliens = new ArrayList<>();
        shots = new ArrayList<>();
        addKeyListener(this);
        setFocusable(true);
        iniciarJuego();
    }

    private void iniciarJuego() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                aliens.add(new Alien(50 + j * 60, 50 + i * 40));
            }
        }
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!endGame) {
            ship.paint(g);
            for (Alien alien : aliens) {
                alien.paint(g);
            }
            for (Shot shot : shots) {
                shot.paint(g);
            }
        } else {
            g.setColor(Color.yellow);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game over", width / 2 - 100, height / 2);
            g.drawString("Score:  "+ score , width / 2 - 80, height / 2 + 40);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!endGame) {
            moveObjects();
            checkCollisions();
            repaint();
        }
    }

    private void moveObjects() {
        ship.mover();
        for (Alien alien : aliens) {
            alien.move(ship.x,ship.y);
        }
        Iterator<Shot> it = shots.iterator();
        while (it.hasNext()) {
            Shot disparo = it.next();
            disparo.mover();
            if (disparo.y < 0) {
                it.remove();
            }
        }
    }

    private void checkCollisions() {
        Iterator<Shot> itShot = shots.iterator();
        while (itShot.hasNext()) {
            Shot disparo = itShot.next();
            Iterator<Alien> itMarciano = aliens.iterator();
            while (itMarciano.hasNext()) {
                Alien alien = itMarciano.next();
                if (disparo.intersects(alien)) {
                    itShot.remove();
                    itMarciano.remove();
                    score ++;
                    break;
                }
            }
        }

        if (aliens.isEmpty()) {
            endGame = true;
        }

        for (Alien alien : aliens) {
            if (alien.y + alien.height >= height - 100) {
                endGame = true;
                break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.setDx(-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.setDx(10);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shots.add(new Shot(ship.x + ship.width / 2, ship.y));
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.setDx(0);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        SpaceInvaders game = new SpaceInvaders();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




