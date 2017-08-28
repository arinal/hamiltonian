package com.truecaller.ai;

import java.util.List;

public interface State<TNode, TState extends State> {

    boolean isNotRoot();
    TState getParentState();
    Iterable<TState> getChildStates();
    TState getThis();

    int pathLength();
    List<TState> getPath();
    boolean hasCyclicPath();
    boolean pathEquals(TState to);
    int pathHashCode();

    TNode getNode();
    int nodeHashCode();
}
