package com.ekong.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiao on 2017/1/17.
 */
public class ZookeeperUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperUtil.class);

    public static final String connectionString = "192.168.8.101:2181,192.168.8.102:2181,192.168.8.103:2181";
    public static final int connectionTimeoutMs = 3000;
    public static final int sessionTimeoutMs = 1000 * 60 * 10;

    public static CuratorFramework createCuratorFramework() {
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(new RetryNTimes(3, 500))
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
        return cf;
    }
}
