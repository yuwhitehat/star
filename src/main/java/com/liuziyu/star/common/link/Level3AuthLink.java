package com.liuziyu.star.common.link;

import com.liuziyu.star.entity.AuthInfo;

import java.text.ParseException;
import java.util.Date;

/**
 * 3级审批人审批节点
 *
 * @author LiuZiyu
 * @date 2022/09/16 16:37
 */
public class Level3AuthLink extends AuthLink{
    private Date beginDate = f.parse("2022-04-01 00:00:00");
    private Date endDate = f.parse("2022-07-30 23:59:59");

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {

        return null;
    }
}
