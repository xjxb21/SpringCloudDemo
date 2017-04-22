package com.ekong.common.sequence;

/**
 * 序号生成器
 */
public interface SeqGenerator {

    /**
     * 生成序号
     * @return
     */
    Object generateSeq();

    /**
     * 获取序号
     * @return
     */
    Object getSeqId();

    /**
     * 获取序号
     * @param timeout 等待超时时间
     * @return
     */
    Object getSeqId(Long timeout);
}
