package com.liuziyu.star.common.link;

import com.liuziyu.star.entity.AuthInfo;
import org.checkerframework.checker.units.qual.A;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 调用链
 *
 * @author LiuZiyu
 * @date 2022/09/16 16:26
 */
public abstract class AuthLink {
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 级别人员id
     */
    protected String levelUserId;

    /**
     * 级别人员姓名
     */
    protected String levelUserName;

    /**
     * 责任链
     */
    protected AuthLink next;

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink next() {
        return next;
    }

    /**
     * 添加节点
     * @param next
     * @return
     */
    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }

    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
