package sharedClasses.utils;

import java.io.Serializable;

public class WrapperForObjects implements Serializable {
    private final Object object;
    private final DescriptionForObject description;
    private Status status;

    public WrapperForObjects(Object object, DescriptionForObject description) {
        this.object = object;
        this.description = description;
        status = Status.DEFAULT;
    }

    public WrapperForObjects(Object object, DescriptionForObject description, Status status) {
        this.object = object;
        this.description = description;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }

    public DescriptionForObject getDescription() {
        return description;
    }
}
