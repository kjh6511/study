package com.masjjim.util.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchFieldCriteria<T> {
    private List<SearchFieldOperation<T>> cris;
}
