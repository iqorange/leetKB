package SegmentTree;

/**
 * 融合器接口
 * @param <E>
 */
public interface Merger<E> {
    E merge(E a, E b);
}
