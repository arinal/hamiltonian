package com.truecaller.ai;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractState<TNode, TState extends State<TNode, TState>>
    implements State<TNode, TState> {

    private final TState parentState;
    private final TNode node;
    private final int hash;

    public AbstractState(TState parentState, TNode node) {
        this.node = node;
        this.parentState = parentState;
        hash = hashCode();
    }

    public AbstractState(TNode node) {
        this(null ,node);
    }

    @Override
    public TNode getNode() {
        return node;
    }

    @Override
    public TState getParentState() {
        return parentState;
    }

    @Override
    public boolean isNotRoot() {
        return getParentState() != null;
    }

    @Override
    public int pathLength() {
        return getPath().size();
    }

    @Override
    public List<TState> getPath() {
        TState currentState = getThis();
        LinkedList<TState> path = new LinkedList<>();
        while (currentState.isNotRoot()) {
            path.addFirst(currentState);
            currentState = currentState.getParentState();
        }
        path.addFirst(currentState);
        return path;
    }

    @Override
    public boolean hasCyclicPath() {
        List<TState> path = getPath();
        return path.stream().map(State::nodeHashCode)
            .distinct().count() != path.size();
    }

    @Override
    public boolean pathEquals(TState other) {
        return pathHashCode() == other.pathHashCode();
    }

    @Override
    public int pathHashCode() {
        return hash == 0?
            Objects.hash(isNotRoot()?
                parentState.nodeHashCode() : 0,
                nodeHashCode()) :
            hash;
    }

    @Override
    public String toString() {
        return node.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == getClass() && pathEquals((TState)other);
    }

    @Override
    public int hashCode() {
        return pathHashCode();
    }

    @Override
    public int nodeHashCode() {
        return node.hashCode();
    }
}
