package com.frank.mytest.codetest.leetcode.dp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Pair {
    private final int i;
    private final int j;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair pair = (Pair) obj;
        return this.i == pair.i && this.j == pair.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.i, this.j);
    }
}
