package com.xiao.common.sequence;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiao on 2017/3/23.
 */

public final class ZookeeperSeqGenerator extends SeqGeneratorAdapter<String> {

    //单例创建
    private ZookeeperSeqGenerator(){}

    private static class LazyHodler{
        private final static ZookeeperSeqGenerator INSTANCE = new ZookeeperSeqGenerator();
    }

    public static ZookeeperSeqGenerator getInstance(){
        return LazyHodler.INSTANCE;
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
