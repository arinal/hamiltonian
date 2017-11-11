package com.lamedh.ai.fringe;

import com.lamedh.ai.State;

import java.util.Stack;

public class DepthSearchFringe<TState extends State> extends AbstractFringe<TState> {

    private Stack<TState> candidates = new Stack<>();

    public DepthSearchFringe(boolean rejectDuplicate, boolean rejectCyclic) {
        super(rejectDuplicate, rejectCyclic);
    }

    @Override
    protected void addState(TState state) {
        candidates.add(state);
    }

    @Override
    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }

    public TState pickCandidate() {
        return candidates.pop();
    }
}
