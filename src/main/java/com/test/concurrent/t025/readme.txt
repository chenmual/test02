对于map/set的使用
    HashMap 不安全
    TreeMap 不安全 排序
    LinkedHashMap 链表

    HashTable 低效同步容器(没有Set)
    Collections.synchronizedXXX 并发量比较少可以使用的并发容器

    ConcurrentHashMap 高并发
    ConcurrentSkipListMap 高并发并需要排序
list的使用
    ArrayList
    LinkedList

    Collections.synchronizedXXX 并发量较少时可以使用
    CopyOnWriteList 写的特别少 读的特别多的时候使用的并发队列
    Queue
        ConcurrentLinkedQueue 高并发队列 没有ConcurrentArrayQueue
        BlockingQueue 阻塞式队列
            LinkedBlockingQueue 无界队列
            ArrayBlockingQueue 有界队列
            TransferQueue 直接把东西交给消费者执行 如果没有消费者阻塞
            SychronusQueue 队列容量为0 把东西交给消费者执行 如果没有消费者阻塞
        DelayQueue 定时执行任务


