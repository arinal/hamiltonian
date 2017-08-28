package com.truecaller.ai;

import java.util.function.Predicate;
import java.util.function.BiConsumer;

public class Searcher<TNode, TState extends State<TNode, TState>> {

    private final Fringe<TState> fringe;

    public Searcher(Fringe<TState> fringe) {
        this.fringe = fringe;
    }

    public TState search(TState from, TNode to,
                         BiConsumer<Long, TState> progress) {
        return doSearch(from, p -> p.getNode().equals(to), progress);
    }

    public TState search(TState from,
                         BiConsumer<Long, TState> progress) {
        return doSearch(from, p -> !fringe.hasCandidates(), progress);
    }

    private TState doSearch(TState from, Predicate<TState> endPredicate,
                            BiConsumer<Long, TState> progress) {
        TState current = from;
        fringe.add(current);
        try {
            Long i = 0L;
            do {
                current = fringe.pickCandidate();
                current.getChildStates().forEach(fringe::add);
                progress.accept(i++, current);
            } while (!endPredicate.test(current));
        } catch (Exception ex) {
            current = from;
        }
        return current;
    }
}
