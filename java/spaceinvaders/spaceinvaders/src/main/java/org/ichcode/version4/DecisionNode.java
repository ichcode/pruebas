package org.ichcode.version4;

// node tree
interface DecisionNode {
    Action decide(Alien alien, int playerX, int playerY);
}
