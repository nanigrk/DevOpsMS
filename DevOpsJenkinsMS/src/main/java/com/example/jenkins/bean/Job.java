package com.example.jenkins.bean;

public class Job extends BaseModel{

    private String name;
    private String url;
    private String color;

    public Job() {
    }
    
    public Job(String name, String url) {
        this();
        this.name = name;
        this.url = url;
        this.setColor(null);
    }

    public Job(String name, String url, String fullName) {
        this();
        this.name = name;
        this.url = url;
        this.setColor(fullName);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    
   

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Job job = (Job) o;

        if (name != null ? !name.equals(job.name) : job.name != null)
            return false;
        if (url != null ? !url.equals(job.url) : job.url != null)
            return false;
       
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
