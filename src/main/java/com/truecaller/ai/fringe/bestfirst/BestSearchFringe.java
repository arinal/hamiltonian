package com.truecaller.ai.fringe.bestfirst;

import com.truecaller.ai.State;
import com.truecaller.ai.fringe.AbstractFringe;

import java.util.PriorityQueue;

public class BestSearchFringe<TState extends State>
    extends AbstractFringe<TState> {

    private Evaluator<TState> evaluator;

    private PriorityQueue<TState> candidates = new PriorityQueue<>(1, (s1, s2) -> {
        Double value1 = evaluator.evaluate(s2);
        Double value2 = evaluator.evaluate(s1);
        return value1.compareTo(value2);
    });

    public BestSearchFringe(Evaluator<TState> evaluator, boolean rejectDuplicate, boolean rejectCyclic) {
        super(rejectDuplicate, rejectCyclic);
        this.evaluator = evaluator;
    }

    @Override
    public TState pickCandidate() {
        return candidates.remove();
    }

    @Override
    protected void addState(TState state) {
        candidates.add(state);
    }

    @Override
    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }
}
