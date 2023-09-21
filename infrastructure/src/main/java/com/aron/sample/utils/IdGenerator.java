package com.aron.sample.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

/**
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据机器位作区分)，并且效率较高.
 */
@Slf4j
public class IdGenerator {
  private static final int NODE_ID_BITS = 10;
  private static final int SEQUENCE_BITS = 12;

  private static final int TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;
  private static final int NODE_LEFT_SHIFT = SEQUENCE_BITS;

  /**
   * 10位机器位可以部署的最大值
   */
  private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);
  /**
   * 12位序列号的最大值
   */
  private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

  /**
   * 2019-08-01 00:00:00
   */
  private static final long CUSTOM_EPOCH = 1564588800000L;

  private volatile long lastTimestamp = -1L;
  private volatile long sequence = 0L;

  private static int nodeId;
  private static IdGenerator snowFlake;

  static {
    nodeId = createNodeId();
    snowFlake = new IdGenerator();
  }

  public static synchronized long newId() {
    return snowFlake.generate();
  }

  private long generate() {
    return createNextId();
  }

  private synchronized long createNextId() {
    long currentTimestamp = timestamp();
    if (currentTimestamp < lastTimestamp) {
      throw new IllegalStateException("Invalid System Clock!");
    }
    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0) {
        currentTimestamp = waitNextMillis(currentTimestamp);
      }
    } else {
      sequence = 0;
    }
    lastTimestamp = currentTimestamp;
    long id = currentTimestamp << TIMESTAMP_LEFT_SHIFT;
    id |= (nodeId << NODE_LEFT_SHIFT);
    id |= sequence;
    return id;
  }

  /**
   * 通过获取机器的网卡地址，转换成16进制的字符串，再得到字符串的hashcode.
   * 最后与{@link MAX_NODE_ID}做与运算避免nodeId超过最大值限制.
   *
   * @return node id
   */
  private static int createNodeId() {
    int nodeId;
    try {
      StringBuilder sb = new StringBuilder();
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        byte[] mac = networkInterface.getHardwareAddress();
        if (mac != null) {
          for (byte macByte : mac) {
            sb.append(String.format("%02X", macByte));
          }
        }
      }
      nodeId = sb.toString().hashCode();
    } catch (Exception exception) {
      nodeId = (new SecureRandom().nextInt());
    }
    nodeId = nodeId & MAX_NODE_ID;
    log.info("generate node id: {}", nodeId);
    return nodeId;
  }

  private static long timestamp() {
    return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
  }

  private long waitNextMillis(long currentTimestamp) {
    while (currentTimestamp == lastTimestamp) {
      currentTimestamp = timestamp();
    }
    return currentTimestamp;
  }
}

