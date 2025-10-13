package org.ichcode.version2;

import java.awt.*;
import java.util.Random;

class Alien extends Rectangle {
    private final Random random = new Random();
    private final double[] qValues = new double[4]; // 0: left, 1: right, 2: up, 3: down
    private final double learningRate = 0.1;
    private final double discountFactor = 0.9;
    private final double epsilon = 0.2; // Exploration vs. exploitation
    private final int screenWidth = 780;
    private final int screenHeight = 580;

    public Alien(int x, int y) {
        super(x, y, 10, 10);
    }

    public void move(int playerX, int playerY) {
        // Action selection
        int action;
        if (random.nextDouble() < epsilon) {
            action = random.nextInt(4); // Explorer
        } else {
            action = bestAction(); // exploit
        }

        applyAction(action);

        // Simple reward: getting closer to the player gives points
        double reward = -Math.hypot(x - playerX, y - playerY);

        // Update Q-value (reinforcement learning)
        int bestNext = bestAction();
        qValues[action] = qValues[action] + learningRate *
                (reward + discountFactor * qValues[bestNext] - qValues[action]);

        // Stay within the limits
        if (x < 0) x = 0;
        if (x > screenWidth) x = screenWidth;
        if (y < 0) y = 0;
        if (y > screenHeight) y = screenHeight;
    }

    private void applyAction(int action) {
        int speed = 3;
        switch (action) {
            case 0 -> x -= speed; // left
            case 1 -> x += speed; // right
            case 2 -> y -= speed; // up
            case 3 -> y += speed; // down
        }
    }

    private int bestAction() {
        int best = 0;
        for (int i = 1; i < qValues.length; i++) {
            if (qValues[i] > qValues[best]) best = i;
        }
        return best;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
