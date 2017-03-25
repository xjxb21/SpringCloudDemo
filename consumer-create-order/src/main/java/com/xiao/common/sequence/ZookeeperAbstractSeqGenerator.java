package com.xiao.common.sequence;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by xiao on 2017/3/23.
 */

public final class ZookeeperAbstractSeqGenerator extends AbstractSeqGenerator<String> {

    //单例创建
    private ZookeeperAbstractSeqGenerator(){}

    private static class LazyHolder{
        private final static ZookeeperAbstractSeqGenerator INSTANCE = new ZookeeperAbstractSeqGenerator();
    }

    public static ZookeeperAbstractSeqGenerator getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public Object generateSeq() {
        int randomValue = ThreadLocalRandom.current().nextInt(5, 20);
        //模拟获取
        String seqId = UUID.randomUUID().toString();
        try {
            Thread.sleep(randomValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return seqId;
    }

}
