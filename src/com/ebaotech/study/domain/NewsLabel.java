package com.ebaotech.study.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: BlueMelancholy
 * 2019/8/1 9:56
 * @desc: 新闻栏目实体
 */
public class NewsLabel {
    private Integer id;
    private String name;
    private String content;
    /**
     * 父栏目
     */
    private NewsLabel parentNewsLabel;
    /**
     * 子栏目
     */
    private Set<NewsLabel> childrenNewsLabels;

    public NewsLabel() {
        childrenNewsLabels = new HashSet<NewsLabel>();
    }

    public NewsLabel(String name, String content) {
        this();
        this.name = name;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsLabel getParentNewsLabel() {
        return parentNewsLabel;
    }

    public void setParentNewsLabel(NewsLabel parentNewsLabel) {
        this.parentNewsLabel = parentNewsLabel;
    }

    public Set<NewsLabel> getChildrenNewsLabels() {
        return childrenNewsLabels;
    }

    public void setChildrenNewsLabels(Set<NewsLabel> childrenNewsLabels) {
        this.childrenNewsLabels = childrenNewsLabels;
    }

    @Override
    public String toString() {
        return "NewsLabel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", childrenNewsLabels=" + childrenNewsLabels +
                '}';
    }
}
