package com.segfault.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 内容主体表
 * @Author : zhengwz
 * @Date : 9:42 2021/1/11
 */
@Table(name = "cms_content")
@Entity
public class Content implements Serializable {

    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Banner.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id", columnDefinition = "bigint(20) NOT NULL COMMENT '栏目id'")
    private Banner banner;

    @Column(nullable = false, columnDefinition = "varchar(150) NOT NULL COMMENT '内容标题'")
    private String title;

    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT '内容标题是否加粗'")
    private Byte titleIsBold;

    @Column(columnDefinition = "varchar(20) NOT NULL DEFAULT '#000000' COMMENT '内容标题的颜色'")
    private String titleColor;

    @Column(columnDefinition = "varchar(150) DEFAULT NULL COMMENT '摘要'")
    private String summary;

    @Column(columnDefinition = "longtext DEFAULT NULL COMMENT '正文'")
    private String content;

    @Column(nullable = false, columnDefinition = "datetime NOT NULL COMMENT '发布时间'")
    private LocalDateTime releaseTime;

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '下线时间'")
    private LocalDateTime offlineTime;

    @Column(columnDefinition = "smallint(6) NOT NULL DEFAULT 1 COMMENT '内容状态(1:草稿;  2-初稿   3:流转中;   4:已审核;   5:已发布;  6:退回;  7:下线  8-归档 9 暂存 10 驳回 )'")
    private Short status;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '排序值'")
    private Integer sortNum;

    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶'")
    private Byte isTop;

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '置顶结束时间'")
    private LocalDateTime topEndTime;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '浏览量'")
    private Integer views;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '点赞数'")
    private Integer ups;

    @Column(columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '点踩数'")
    private Integer downs;

    @Column(columnDefinition = "smallint(6) NOT NULL DEFAULT '1' COMMENT '浏览设置（1-允许游客访问   2-登录后访问）'")
    private Short viewControl;

    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT '删除标识（0为正常1为删除）'")
    private Byte deletedFlag;

    @Column(columnDefinition = "int(11) DEFAULT '0' COMMENT '浏览人数'")
    private Integer peopleViews;

}
