package com.example.jenkins.bean;

import java.util.Collections;
import java.util.List;

public class JobWithDetails extends Job {

    private String description;

    private String displayName;

    private boolean buildable;

    private List<Build> builds = Collections.emptyList();

    private Build firstBuild;

    private Build lastBuild;

    private Build lastCompletedBuild;

    private Build lastFailedBuild;

    private Build lastStableBuild;

    private Build lastSuccessfulBuild;

    private Build lastUnstableBuild;

    private Build lastUnsuccessfulBuild;

    private int nextBuildNumber;

    private boolean inQueue;


    private List<Job> downstreamProjects;

    private List<Job> upstreamProjects;
    
    public String getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public boolean isInQueue() {
        return inQueue;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (buildable ? 1231 : 1237);
        result = prime * result + ((builds == null) ? 0 : builds.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((downstreamProjects == null) ? 0 : downstreamProjects.hashCode());
        result = prime * result + ((firstBuild == null) ? 0 : firstBuild.hashCode());
        result = prime * result + (inQueue ? 1231 : 1237);
        result = prime * result + ((lastBuild == null) ? 0 : lastBuild.hashCode());
        result = prime * result + ((lastCompletedBuild == null) ? 0 : lastCompletedBuild.hashCode());
        result = prime * result + ((lastFailedBuild == null) ? 0 : lastFailedBuild.hashCode());
        result = prime * result + ((lastStableBuild == null) ? 0 : lastStableBuild.hashCode());
        result = prime * result + ((lastSuccessfulBuild == null) ? 0 : lastSuccessfulBuild.hashCode());
        result = prime * result + ((lastUnstableBuild == null) ? 0 : lastUnstableBuild.hashCode());
        result = prime * result + ((lastUnsuccessfulBuild == null) ? 0 : lastUnsuccessfulBuild.hashCode());
        result = prime * result + nextBuildNumber;
        result = prime * result + ((upstreamProjects == null) ? 0 : upstreamProjects.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobWithDetails other = (JobWithDetails) obj;
        if (buildable != other.buildable)
            return false;
        if (builds == null) {
            if (other.builds != null)
                return false;
        } else if (!builds.equals(other.builds))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (downstreamProjects == null) {
            if (other.downstreamProjects != null)
                return false;
        } else if (!downstreamProjects.equals(other.downstreamProjects))
            return false;
        if (firstBuild == null) {
            if (other.firstBuild != null)
                return false;
        } else if (!firstBuild.equals(other.firstBuild))
            return false;
        if (inQueue != other.inQueue)
            return false;
        if (lastBuild == null) {
            if (other.lastBuild != null)
                return false;
        } else if (!lastBuild.equals(other.lastBuild))
            return false;
        if (lastCompletedBuild == null) {
            if (other.lastCompletedBuild != null)
                return false;
        } else if (!lastCompletedBuild.equals(other.lastCompletedBuild))
            return false;
        if (lastFailedBuild == null) {
            if (other.lastFailedBuild != null)
                return false;
        } else if (!lastFailedBuild.equals(other.lastFailedBuild))
            return false;
        if (lastStableBuild == null) {
            if (other.lastStableBuild != null)
                return false;
        } else if (!lastStableBuild.equals(other.lastStableBuild))
            return false;
        if (lastSuccessfulBuild == null) {
            if (other.lastSuccessfulBuild != null)
                return false;
        } else if (!lastSuccessfulBuild.equals(other.lastSuccessfulBuild))
            return false;
        if (lastUnstableBuild == null) {
            if (other.lastUnstableBuild != null)
                return false;
        } else if (!lastUnstableBuild.equals(other.lastUnstableBuild))
            return false;
        if (lastUnsuccessfulBuild == null) {
            if (other.lastUnsuccessfulBuild != null)
                return false;
        } else if (!lastUnsuccessfulBuild.equals(other.lastUnsuccessfulBuild))
            return false;
        if (nextBuildNumber != other.nextBuildNumber)
            return false;
        if (upstreamProjects == null) {
            if (other.upstreamProjects != null)
                return false;
        } else if (!upstreamProjects.equals(other.upstreamProjects))
            return false;
        return true;
    }

}
