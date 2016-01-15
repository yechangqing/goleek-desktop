package org.yecq.goleek.desktop.view;

/**
 * 每一个需要作为通知和被通知对象，可以继承该对象
 *
 * @author yecq
 */
public abstract class ChangeBase implements ChangeListener {

    private Object userObject;    // 用户对象
    protected final String name;          // 自己的名字
    private String[] changes;       // 随着哪些对象而变
    private ChangeService cs;

    public ChangeBase(String name, String[] changes, Object userObject, ChangeService cs) {
        this.name = name;
        this.changes = changes;
        this.userObject = userObject;
        this.cs = cs;
        this.cs.addChangeListener(this.changes, this);
    }

    // 触发通知中心
    public void fireMeChanged() {
        this.cs.fireChanged(new String[]{this.name});
    }

    @Override
    public String[] getNotifiedNames() {
        return this.changes;
    }

    // 返回包含的对象
    public Object getUserObject() {
        return this.userObject;
    }

    // 设置包含对象
    public void setUserObject(Object o) {
        this.userObject = o;
    }

    // 返回对象名称
    @Override
    public String getName() {
        return this.name;
    }

}
