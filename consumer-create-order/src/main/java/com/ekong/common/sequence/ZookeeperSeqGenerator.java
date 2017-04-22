package com.ekong.common.sequence;

import com.ekong.utils.SequenceUtil;
import com.ekong.utils.ZookeeperUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiao on 2017/3/23.
 */

public final class ZookeeperSeqGenerator extends AbstractSeqGenerator<String> {

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //单例
    private ZookeeperSeqGenerator() {
    }

    private static class LazyHolder {
        private final static ZookeeperSeqGenerator INSTANCE = new ZookeeperSeqGenerator();
    }

    public static ZookeeperSeqGenerator getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 初始化zookeeper客户端
     */
    private static CuratorFramework client = null;
    private static final String seqNodePath = "/cloud/order/orderSeqId";

    static {
        final Logger loggerZk = LoggerFactory.getLogger("loggerZk");
        loggerZk.info("curatorFrameworkClient started, will check the seq's node and init some params");
        client = ZookeeperUtil.createCuratorFramework();
        //如果节点没有则创建
        try {
            client.start();    //启动CuratorFramework,放在前面
            if (client.checkExists().forPath(seqNodePath) == null) {

                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(seqNodePath, "0".getBytes());    //初始值为0
                loggerZk.info("create node {} success!", seqNodePath);
            }

            //Thread.sleep(1000 * 10);
            //注册 shutdownHook 关闭客户端
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("close the curator client");
                    client.close();
                }
            });

        } catch (Exception e) {
            loggerZk.error("initialize ZookeeperSeqGenerator's zookeeper client failed.");
            e.printStackTrace();
        }
    }

    @Override
    public Object generateSeq() {

        if (this.client == null) {
            throw new RuntimeException("instance of ZookeeperSeqGenerator's client is null.");
        }
        String seqId = "";
        InterProcessMutex lock = new InterProcessMutex(client, seqNodePath);
        try {
            //DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client, seqNodePath, new RetryNTimes(3, 100));
            //distributedAtomicInteger.increment();

            //需要加锁
            if (!lock.acquire(2000, TimeUnit.MILLISECONDS)) {
                logger.info("ZookeeperSeqGenerator client could not acquire the lock");
                return "";
            }
            byte[] bytes = client.getData().forPath(seqNodePath);
            long seq = Long.valueOf(new String(bytes)).longValue();
            seqId = SequenceUtil.autoGenericSequence(++seq);
            client.setData().forPath(seqNodePath, String.valueOf(seq).getBytes());
            return seqId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return seqId;
    }

}
