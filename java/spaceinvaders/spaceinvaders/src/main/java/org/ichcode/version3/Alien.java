package org.ichcode.version3;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;

import java.awt.*;

class Alien extends Rectangle {

    private final int speed = 1;
    private final BehaviorTree<Alien> tree;
    private int playerX;
    private int playerY;

    public Alien(int x, int y) {
        super(x, y, 40, 30);
        tree = new BehaviorTree<>(createTree(), this);
    }

    public void move(int shipX, int shipY) {
        this.playerX = shipX;
        this.playerY = shipY;
        tree.step();
        keepInside();
    }

    public void paint(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    // ================= BEHAVIOR TREE =================

    private Task<Alien> createTree() {

        Selector<Alien> root = new Selector<>();

        // Ataque si el jugador está cerca
        Sequence<Alien> attack = new Sequence<>();
        attack.addChild(new PlayerNear());
        attack.addChild(new ChasePlayer());

        // Patrulla
        Sequence<Alien> patrol = new Sequence<>();
        patrol.addChild(new MoveDown());

        root.addChild(attack);
        root.addChild(patrol);

        return root;
    }

    // ================= ACTIONS =================

    class PlayerNear extends LeafTask<Alien> {
        @Override
        public Status execute() {
            return Math.abs(playerX - x) < 80
                    ? Status.SUCCEEDED
                    : Status.FAILED;
        }

        @Override
        protected Task<Alien> copyTo(Task<Alien> task) {
            return null;
        }
    }

    class ChasePlayer extends LeafTask<Alien> {
        @Override
        public Status execute() {
            if (playerX < x) x -= speed;
            if (playerX > x) x += speed;
            y += speed;
            return Status.RUNNING;
        }

        @Override
        protected Task<Alien> copyTo(Task<Alien> task) {
            return null;
        }
    }

    class MoveDown extends LeafTask<Alien> {
        @Override
        public Status execute() {
            y += speed;
            return Status.RUNNING;
        }

        @Override
        protected Task<Alien> copyTo(Task<Alien> task) {
            return null;
        }
    }

    private void keepInside() {
        x = Math.max(0, Math.min(x, 760));
        y = Math.max(0, Math.min(y, 560));
    }
}
