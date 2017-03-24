package com.xiao.common.sequence;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by xiao on 2017/3/23.
 */

public final class ZookeeperSeqGenerator extends SeqGeneratorAdapter<String> {

    //单例创建
    private ZookeeperSeqGenerator(){}

    private static class LazyHolder{
        private final static ZookeeperSeqGenerator INSTANCE = new ZookeeperSeqGenerator();
    }

    public static ZookeeperSeqGenerator getInstance(){
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
