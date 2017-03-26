package com.xiao.common.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列号生成器
 */
public abstract class AbstractSeqGenerator<T> implements SeqGenerator, Callable<T> {

    public final Logger logger = LoggerFactory.getLogger(AbstractSeqGenerator.class);

    private ThreadPoolExecutor generatorPool;
    AtomicInteger rejectCount = new AtomicInteger(0);

    /**
     * 获取当前seqGenerator工作线程池,如没有则创建一个
     *
     * @return
     */
    private ThreadPoolExecutor getGeneratorPool() {
        if (generatorPool == null) {
            int processorCount = Runtime.getRuntime().availableProcessors() + 2;
            ThreadPoolExecutor pool = new ThreadPoolExecutor(
                    processorCount - 1,
                    processorCount * 2,
                    1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(processorCount * 3),
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            System.out.println("pool reject request， reject count is : " + rejectCount.incrementAndGet());
                        }
                    });
            this.generatorPool = pool;
        }
        return this.generatorPool;
    }

    /**
     * 检查返回值的类型
     */
    private T validateType(Object o) throws Exception {
        if (o == null) {
            throw new Exception("sequence is null");
        }
        //此处限定了sequence的类型
        if (!(o instanceof String || o instanceof Integer)) {
            throw new Exception("sequence's class type only support String or Integer, but current sequence's type is " + o.getClass());
        }
        return (T) o;
    }

    /**
     * 检查返回值的类型，并生成序列号
     *
     * @return
     * @throws Exception 当序列号为空，或类型不为String 或 Integer 抛出异常
     */
    private T generateSeq0() throws Exception {
        // generateSeq 方法在子类中实现
        Object o = generateSeq();
        //验证返回值类型
        T seq = validateType(o);
        return seq;
    }

    @Override
    public T call() throws Exception {
        return generateSeq0();
    }

    @Override
    public Object getSeqId() {
        return getSeqId(0L);
    }

    /**
     * 调用方法：
     * ZookeeperSeqGenerator.getSeqId();
     *
     * @param timeout 等待超时时间，如果超时则返回 null 值。
     * @return 新的序列号
     */
    @Override
    public final T getSeqId(Long timeout) {
        try {
            FutureTask<T> futureTask = new FutureTask<>(this);
            getGeneratorPool().submit(futureTask);

            T seqId;
            if (timeout > 0) {
                seqId = futureTask.get(timeout, TimeUnit.MILLISECONDS);
            } else {
                seqId = futureTask.get();
            }

            logger.debug("getSeqId success>>>>> {}",seqId);

            return seqId;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + " Time out ,return null.");
            return null;
        }
    }
}
