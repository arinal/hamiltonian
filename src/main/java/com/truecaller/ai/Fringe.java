package com.truecaller.ai;

public interface Fringe<TState extends State> {
    boolean hasCandidates();
    TState pickCandidate();
    void add(TState state);
}
