package com.truecaller.ai;

public interface Fringe<TState extends State> {
    boolean hasCandidates();
    void add(TState state);
    TState pickCandidate();
}
