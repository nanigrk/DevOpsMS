package com.example.jenkins.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents build information with details about what has been done
 * like duration start and of course the build result.
 *
 */
public class BuildWithDetails extends Build {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private boolean building;
    private String description;
    private String displayName;
    private long duration;
    private long estimatedDuration;
    private String fullDisplayName;
    private String id;
    private long timestamp;
    private BuildResult result;
    private String consoleOutputText;
    private String consoleOutputHtml;
    @JsonProperty("changeSets")
    private String builtOn;

    public BuildWithDetails() {
        // Default ctor is needed to jackson.
    }

    public BuildWithDetails(BuildWithDetails details) {
       this.description = details.description;
        this.displayName = details.displayName;
        this.building = details.building;
        this.duration = details.duration;
        this.estimatedDuration = details.estimatedDuration;
        this.fullDisplayName = details.fullDisplayName;
        this.id = details.id;
        this.timestamp = details.timestamp;
        this.result = details.result;
        this.consoleOutputHtml = details.consoleOutputHtml;
        this.consoleOutputText = details.consoleOutputText;
        this.builtOn = details.builtOn;
    }

    public boolean isBuilding() {
        return building;
    }


    public String getBuiltOn() {
        return builtOn;
    }


    
    public BuildWithDetails setResult(BuildResult result) {
        this.result = result;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuildWithDetails other = (BuildWithDetails) obj;
        if (building != other.building)
            return false;
        if (builtOn == null) {
            if (other.builtOn != null)
                return false;
        } else if (!builtOn.equals(other.builtOn))
            return false;
       if (consoleOutputHtml == null) {
            if (other.consoleOutputHtml != null)
                return false;
        } else if (!consoleOutputHtml.equals(other.consoleOutputHtml))
            return false;
        if (consoleOutputText == null) {
            if (other.consoleOutputText != null)
                return false;
        } else if (!consoleOutputText.equals(other.consoleOutputText))
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
        if (duration != other.duration)
            return false;
        if (estimatedDuration != other.estimatedDuration)
            return false;
        if (fullDisplayName == null) {
            if (other.fullDisplayName != null)
                return false;
        } else if (!fullDisplayName.equals(other.fullDisplayName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (result != other.result)
            return false;
        if (timestamp != other.timestamp)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (building ? 1231 : 1237);
        result = prime * result + ((builtOn == null) ? 0 : builtOn.hashCode());
        result = prime * result + ((consoleOutputHtml == null) ? 0 : consoleOutputHtml.hashCode());
        result = prime * result + ((consoleOutputText == null) ? 0 : consoleOutputText.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + (int) (duration ^ (duration >>> 32));
        result = prime * result + (int) (estimatedDuration ^ (estimatedDuration >>> 32));
        result = prime * result + ((fullDisplayName == null) ? 0 : fullDisplayName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
