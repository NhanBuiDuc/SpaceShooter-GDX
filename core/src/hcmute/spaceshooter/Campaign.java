package hcmute.spaceshooter;

public class Campaign {
    public String name;
    public String boss;
    public String description;
    public int record;

    public Campaign(String name, String boss, String description,int record) {
        this.name = name;
        this.boss = boss;
        this.description = description;
        this.record=record;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}