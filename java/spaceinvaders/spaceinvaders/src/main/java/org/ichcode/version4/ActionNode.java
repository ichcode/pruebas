package org.ichcode.version4;

// Leaf Node
class ActionNode implements DecisionNode {
    private final Action action;

    public ActionNode(Action action) {
        this.action = action;
    }

    @Override
    public Action decide(Alien alien, int px, int py) {
        return action;
    }
}
