package org.yecq.goleek.desktop.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义的一些切点
 *
 * @author yecq
 */
public class Pointcut0 {

    // 所有在agent包中的
    @Pointcut("within(org.yecq.goleek.desktop.agent.*)")
    public void inAgentPackage() {
    }

    // 被Notify注解所修饰的方法
    @Pointcut("execution(@org.yecq.goleek.desktop.aspect.Notify * *(..))")
//    @Pointcut("execution(* *(..))")
    public void haveNotifyAnnotation() {
    }
}
