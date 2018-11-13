package com.yl.demo.bean;

/**
 * @author Alex
 * @since 2018/11/9 14:42
 */
public class DemoBean {

    public int id;

    public Status status;

    public enum Status {

        OK,
        PARTIAL_ALIVE,
        DISABLED,
        ALL_CRASHED;

        public static Status getJobStatus(final int okCount, final int crashedCount, final int disabledCount, final int serverCount) {
            if (okCount == serverCount) {
                return OK;
            }
            if (crashedCount == serverCount) {
                return ALL_CRASHED;
            }
            if (crashedCount > 0) {
                return PARTIAL_ALIVE;
            }
            if (disabledCount > 0) {
                return DISABLED;
            }
            return OK;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
