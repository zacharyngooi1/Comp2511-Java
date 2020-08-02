package controllers;

public abstract class Animation {
    protected AnimationController animationController;
    public Animation next = null;
    protected long duration;

    public void setNext(Animation next) {
        this.next = next;
    }

    public abstract void enter();
    public abstract void exit();
}
