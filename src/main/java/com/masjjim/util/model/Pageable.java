package com.masjjim.util.model;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Immutable

public class Pageable<T> extends Page {

    private int totalPages;
    private long totalCount;
    private List<T> items;

    @Builder
    public Pageable(List<T> items, int page, int size, long totalCount) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        calculateTotalPages();
    }

    private void calculateTotalPages() {
        totalPages = (int)(totalCount / size);
        if (totalCount % size > 0) {
            totalPages++;
        }
    }
}
