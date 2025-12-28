package org.ichcode.version4;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
Decision Tree Diagram

Is the player nearby?
 ├─ Yes → Attack
 │    ├─ Is it to the left? → Move left
 │    └─ Is it to the right? → Move right
 └─ No → Patrol
      ├─ Screen edge? → Change direction
      └─ Keep going down

 */
class Alien extends Rectangle {

    private final DecisionNode brain;
    private final BufferedImage skin;

    public Alien(int x, int y, BufferedImage skin) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 30;
        this.skin = skin;
        brain = createDecisionTree();
    }


    public void move(int playerX, int playerY) {
        Action action = brain.decide(this, playerX, playerY);
        applyAction(action);
        keepInside();
    }

    private void applyAction(Action action) {
        int speed = 1;
        switch (action) {
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
            case DOWN -> y += speed;
            case STAY -> {}
        }
    }

    private void keepInside() {
        x = Math.max(0, Math.min(x, 760));
        y = Math.max(0, Math.min(y, 560));
    }

    public void paint(Graphics g) {
        if (skin != null) {
            g.drawImage(skin, x, y, width, height, null);
        } else {
            g.fillRect(x, y, width, height); // fallback
        }
    }

    // decision tree
    private DecisionNode createDecisionTree() {

        ActionNode left  = new ActionNode(Action.LEFT);
        ActionNode right = new ActionNode(Action.RIGHT);
        ActionNode down  = new ActionNode(Action.DOWN);
        ActionNode stay  = new ActionNode(Action.STAY);

        Decision playerClose =
                (a, px, py) -> Math.abs(px - a.x) < 80;

        Decision playerLeft =
                (a, px, py) -> px < a.x;

        Decision nearBorder =
                (a, px, py) -> a.x < 20 || a.x > 740;

        DecisionNode attack =
                new ConditionNode(playerLeft, left, right);

        DecisionNode patrol =
                new ConditionNode(nearBorder, stay, down);

        return new ConditionNode(playerClose, attack, patrol);
    }
}
