package com.lamedh.ai.fringe.bestfirst;

public interface Evaluator<TState> {
    double evaluate(TState state);
}
