package com.truecaller.ai.fringe;

import com.truecaller.ai.Fringe;
import com.truecaller.ai.State;

import java.util.HashSet;

public abstract class AbstractFringe<TState extends State> implements Fringe<TState> {

    private boolean rejectDuplicate;
    private boolean rejectCyclic;
    private HashSet<Integer> addedNode = new HashSet<>();

    public AbstractFringe(boolean rejectDuplicate, boolean rejectCyclic) {
        this.rejectDuplicate = rejectDuplicate;
        this.rejectCyclic = rejectCyclic;
    }

    @Override
    public void add(TState state) {
        if (contains(state) &&
            (rejectDuplicate || state.hasCyclicPath() && rejectCyclic)) {
            return;
        }
        if (rejectDuplicate) addedNode.add(state.nodeHashCode());
        addState(state);
    }

    protected boolean contains(TState state) {
        return addedNode.contains(state.nodeHashCode());
    }

    protected abstract void addState(TState state);
}
