package michaelmdonato.gohealth_bug_tracker.services;


import java.sql.Date;

public class Bug {
    private long id, parentId;
    String description, status, link;
    Date timestamp;

    public Bug(String description, String link){
        this.description = description;
        this.link = link;
    }

    public String getId() {
        return String.valueOf(id);
    }
    public String getTimestamp() { return String.valueOf(this.timestamp);}
    public String getDescription() { return this.description; }
    public String getStatus(){ return this.status; }
    public String getLink(){ return this.link; }


    public void setId(long id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
