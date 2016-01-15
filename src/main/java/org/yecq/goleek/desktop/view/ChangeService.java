package org.yecq.goleek.desktop.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 注册与调用类，每一个使用该包的环境必须实例化这个类
 *
 * @author yecq
 */
public abstract class ChangeService implements ChangeListener {

    protected Map<String, ChangeListener> obj;  // 保存各个对象
    protected Map<String, List<ChangeListener>> listeners;
    protected Map<String, List<ChangeListener>> another;   // 保存另外的一个系统对自己的监听
    protected String name;
    protected String[] changes;
    protected ChangeService cs;

    // 单独使用时的构造方法
    public ChangeService() {
        this.obj = new HashMap<>();
        this.listeners = new HashMap<>();
        this.another = new HashMap<>();
        this.name = null;
        this.changes = null;
        this.cs = null;
    }

    // 设置名字
    public void setName(String name) {
        this.name = name;
    }

    // 接受对象注册
    synchronized public boolean addChangeListener(ChangeListener cl) {
        if (cl == null) {
            return false;
        }

        String[] names = cl.getNotifiedNames();
        return addChangeListener(names, cl);
    }

    synchronized public boolean addChangeListener(String[] names, ChangeListener cl) {
        if (names == null) {
            return false;
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].trim().equals("")) {
                return false;
            }
            // 如果没有此对象，就创建
            String nn = names[i].trim();
            if (!this.listeners.containsKey(nn)) {
                this.listeners.put(nn, new LinkedList<ChangeListener>());
            }
            List<ChangeListener> tmp = this.listeners.get(nn);
            if (!tmp.contains(cl)) {
                tmp.add(cl);
            }
        }

        String name1 = cl.getName();
        if (name1 != null && !name1.trim().equals("")) {
            this.obj.put(name1.trim(), cl);
        }

        return true;
    }

    // 对象注销
    synchronized public boolean removeChangeListener(ChangeListener cl) {
        if (cl == null) {
            return false;
        }

        String[] names = cl.getNotifiedNames();
        if (names == null) {
            return false;
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].trim().equals("")) {
                return false;
            }
            String nn = names[i].trim();
            if (this.listeners.containsKey(nn)) {
                // 取出这个链表
                List<ChangeListener> tmp = this.listeners.get(nn);
                tmp.remove(cl);
            }
        }

        return true;
    }

    // 通知各对象改变
    synchronized public void fireChanged(String[] names) {
        if (names == null) {
            return;
        }

        Set<ChangeListener> set = new LinkedHashSet();
        for (int i = 0; i < names.length; i++) {
            List<ChangeListener> lt = this.listeners.get(names[i].trim());
            if (lt != null) {
                set.addAll(lt);
            }
        }

        Iterator<ChangeListener> ite = set.iterator();
        while (ite.hasNext()) {
            ite.next().doChangeAction();
        }
    }

    @Override
    public String[] getNotifiedNames() {
        return this.changes;
    }

    // 触发通知中心
    public void fireMeChanged() {
        this.fireChanged(new String[]{this.name});
    }

    ///////////////////////  和另一个系统交互时用  /////////////////////////////
    // 监听另一系统时的构造方法
    public ChangeService(String name, String[] changes, ChangeService cs) {
        this();
        this.name = name;
        this.changes = changes;
        this.cs = cs;
        this.cs.addChangeService(changes, this);
    }

    // 接受其他系统的注册
    synchronized public boolean addChangeService(String[] names, ChangeListener cl) {
        if (names == null) {
            return false;
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].trim().equals("")) {
                return false;
            }
            // 如果没有此对象，就创建
            String nn = names[i].trim();
            if (!this.another.containsKey(nn)) {
                this.another.put(nn, new LinkedList<ChangeListener>());
            }
            List<ChangeListener> tmp = this.another.get(nn);
            if (!tmp.contains(cl)) {
                tmp.add(cl);
            }
        }

        return true;
    }

    synchronized public boolean removeChangeService(ChangeListener cl) {
        if (cl == null) {
            return false;
        }

        String[] names = cl.getNotifiedNames();
        if (names == null) {
            return false;
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].trim().equals("")) {
                return false;
            }
            String nn = names[i].trim();
            if (this.another.containsKey(nn)) {
                // 取出这个链表
                List<ChangeListener> tmp = this.another.get(nn);
                tmp.remove(cl);
            }
        }

        return true;
    }

    synchronized public void fireServiceChanged(String[] names) {
        if (names == null) {
            return;
        }

        Set<ChangeListener> set = new LinkedHashSet();
        for (int i = 0; i < names.length; i++) {
            List<ChangeListener> lt = this.another.get(names[i].trim());
            if (lt != null) {
                set.addAll(lt);
            }
        }

        Iterator<ChangeListener> ite = set.iterator();
        while (ite.hasNext()) {
            ite.next().doChangeAction();
        }
    }

    public void fireMeServiceChanged() {
        fireServiceChanged(new String[]{this.name});
    }

    @Override
    public String getName() {
        return this.name;
    }

    public ChangeListener getChangeListenerObject(String name) {
        if (name == null) {
            return null;
        }
        name = name.trim();
        return this.obj.get(name);
    }
}
