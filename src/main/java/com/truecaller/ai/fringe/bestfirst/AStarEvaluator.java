package com.truecaller.ai.fringe.bestfirst;

import com.truecaller.ai.State;

public class AStarEvaluator<TState extends State> implements Evaluator<TState> {

    private Evaluator<TState> greedyEvaluator;

    public AStarEvaluator(Evaluator<TState> greedyEvaluator) {
        this.greedyEvaluator = greedyEvaluator;
    }

    @Override
    public double evaluate(TState state) {
        return greedyEvaluator.evaluate(state) + getActual(state);
    }

    private int getActual(TState state) {
        return state.pathLength();
    }
}
