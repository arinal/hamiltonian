package com.truecaller.ai.fringe.bestfirst;

public interface Evaluator<TState> {
    double evaluate(TState state);
}
