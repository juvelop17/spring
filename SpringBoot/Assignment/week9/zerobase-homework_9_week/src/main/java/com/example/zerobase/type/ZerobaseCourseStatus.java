package com.example.zerobase.type;

public enum ZerobaseCourseStatus {
    IN_PROGRESS, CLOSE, OPEN;

    public boolean isOpen() {
        return this == OPEN;
    }
}
