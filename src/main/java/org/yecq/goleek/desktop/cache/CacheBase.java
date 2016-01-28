package org.yecq.goleek.desktop.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.plain.core.CoreChangeListener;
import org.yecq.baseframework.plain.core.CoreChangeNotifier;

/**
 *
 * @author yecq
 */
abstract class CacheBase implements CoreChangeListener {

    private List<CacheListener> list;

    @Autowired
    private CoreChangeNotifier notifier;

    @PostConstruct
    private void init() {
        this.notifier.addCoreChangeListener(this);
        this.list = new LinkedList();
        doOtherInit();
        loadData();
    }

    protected void doOtherInit() {

    }

    public void addCacheListener(CacheListener noti) {
        this.list.add(noti);
    }

    @Override
    public void action() {
        loadData();
        fireFlush();
    }

    abstract protected void loadData();

    protected void fireFlush() {
        Iterator<CacheListener> ite = this.list.iterator();
        while (ite.hasNext()) {
            ite.next().flush();
        }
    }
}
