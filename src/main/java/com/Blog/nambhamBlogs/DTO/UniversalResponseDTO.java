package com.Blog.nambhamBlogs.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UniversalResponseDTO<T> {
        private ResponseCodeJson responseCodeJson;
        private List list;
        private T object;
        private Integer reqid;
        private Map<?,?> map;
}
