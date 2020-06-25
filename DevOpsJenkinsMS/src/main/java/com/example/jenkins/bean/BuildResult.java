package com.example.jenkins.bean;

public enum BuildResult {
    FAILURE,
    UNSTABLE,
    REBUILDING,
    BUILDING,
    ABORTED,
    SUCCESS,
    UNKNOWN,
    NOT_BUILT,
    CANCELLED
}
