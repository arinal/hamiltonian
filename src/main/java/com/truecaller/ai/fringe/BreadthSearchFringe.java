package com.truecaller.ai.fringe;

import com.truecaller.ai.State;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthSearchFringe<TState extends State> extends AbstractFringe<TState> {

    private Queue<TState> candidates = new LinkedList<>();

    public BreadthSearchFringe() {
        super(false, true);
    }

    public BreadthSearchFringe(boolean rejectDuplicate, boolean rejectCyclic) {
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

    @Override
    public TState pickCandidate() {
        return candidates.remove();
    }
}
