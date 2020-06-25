package com.example.jenkins.bean;

public class Build extends BaseModel {
    private int number;
    private int queueId;
    private String url;
    private String timestamp;
    private String id;
    private String result;

    public Build(int number, int queueId, String url) {
        super();
        this.number = number;
        this.queueId = queueId;
        this.url = url;
    }

    public Build() {
    }

    public Build(Build from) {
        this(from.getNumber(), from.getUrl());
    }

    public Build(int number, String url) {
        this.number = number;
        this.url = url;
    }

    public int getNumber() {
        return number;
    }

    public int getQueueId() {
        return queueId;
    }

    public String getUrl() {
        return url;
    }

    protected Build setNumber(int number) {
        this.number = number;
        return this;
    }

    protected Build setQueueId(int queueId) {
        this.queueId = queueId;
        return this;
    }

    protected Build setUrl(String url) {
        this.url = url;
        return this;
    }

	public String getTimestamp() {
		return timestamp;
	}

	public String getId() {
		return id;
	}

	public String getResult() {
		return result;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Build other = (Build) obj;
        if (number != other.number)
            return false;
        if (queueId != other.queueId)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + queueId;
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }
}
