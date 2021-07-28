package com.segfault.entity;


import jdk.nashorn.internal.objects.NativeUint8Array;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 栏目实体
 * @Author : zhengwz
 * @Date : 9:39 2021/1/11
 */
@Table(name = "cms_banner")
@Entity
public class Banner implements Serializable {

    @Id
    @Column(name = "banner_id", nullable = false, columnDefinition = "bigint(20) NOT NULL COMMENT 'ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "bigint(20) DEFAULT NULL COMMENT '父栏目ID'")
    private Long parentId;

    @Column(nullable = false, columnDefinition = "varchar(50) NOT NULL COMMENT '名称'")
    private String bannerName;

    @Column(nullable = false, columnDefinition = "varchar(50) NOT NULL COMMENT '栏目编码'")
    private String bannerCode;

    @Column(nullable = false, columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '排序'")
    private Integer sortNum;

    @Column(nullable = false, length = 6, columnDefinition = "smallint(6) NOT NULL DEFAULT 1 COMMENT '栏目类型：1-文章，2-链接'")
    private Short bannerType;

    @Column(nullable = false, length = 1, columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示: 1-显示， 2-不显示'")
    private Byte isDisplay;

    @Column(columnDefinition = "varchar(500) DEFAULT NULL COMMENT '栏目描述'")
    private String description;
}
