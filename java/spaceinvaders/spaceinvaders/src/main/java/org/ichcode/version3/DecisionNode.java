package org.ichcode.version3;

// node tree
interface DecisionNode {
    Action decide(Alien alien, int playerX, int playerY);
}
