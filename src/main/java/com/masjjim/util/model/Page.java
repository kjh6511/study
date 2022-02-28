package com.masjjim.util.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Page {
    protected int page;
    protected int size;
    public static int validSize(int size, int def) {
        return size > 0 ? size : def;
    }
    public int getOffset() {
        return page *  size;
    }
}
