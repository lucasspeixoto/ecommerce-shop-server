package com.ibm.shop.data.response;

import com.ibm.shop.data.vo.StateVO;

import java.util.List;
import java.util.Objects;

public class StateResponse {

    private List<StateVO> content;

    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public StateResponse() {
    }

    public StateResponse(List<StateVO> content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<StateVO> getContent() {
        return content;
    }

    public void setContent(List<StateVO> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateResponse that)) return false;
        return getPageNo() == that.getPageNo() && getPageSize() == that.getPageSize() && getTotalElements() == that.getTotalElements() && getTotalPages() == that.getTotalPages() && isLast() == that.isLast() && Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getPageNo(), getPageSize(), getTotalElements(), getTotalPages(), isLast());
    }
}
