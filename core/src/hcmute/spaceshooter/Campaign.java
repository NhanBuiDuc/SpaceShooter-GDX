package hcmute.spaceshooter;

public class Campaign {
    public String name;
    public String boss;
    public int record;

    public Campaign(String name, String boss,int record) {
        this.name = name;
        this.boss = boss;
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

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}