package org.yecq.goleek.desktop.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yecq.goleek.desktop.service.core.CoreChangeNotifier;

/**
 *
 * @author yecq
 */
@Component
@Aspect
public class NotifyAdvice {

    @Autowired
    private CoreChangeNotifier notifier;

    @After("Pointcut0.inAgentPackage() && Pointcut0.haveNotifyAnnotation()")
    public void notifyChange(JoinPoint p) {
        //好办法
        MethodSignature sign = (MethodSignature) p.getSignature();
        Method m = sign.getMethod();
        if (m.isAnnotationPresent(Notify.class)) {
            // 获取注解的参数
            Notify noti = m.getAnnotation(Notify.class);
            String[] names = noti.value();
            notifier.fireCoreChange(names);
        }
    }
}
