package org.ichcode.version3;

// decision node
class ConditionNode implements DecisionNode {
        private final Decision condition;
        private final DecisionNode trueNode;
        private final DecisionNode falseNode;

        public ConditionNode(Decision condition, DecisionNode trueNode, DecisionNode falseNode) {
            this.condition = condition;
            this.trueNode = trueNode;
            this.falseNode = falseNode;
        }

        @Override
        public Action decide(Alien alien, int px, int py) {
            return condition.test(alien, px, py)
                    ? trueNode.decide(alien, px, py)
                    : falseNode.decide(alien, px, py);
        }
}