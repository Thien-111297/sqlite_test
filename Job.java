package vn.itplus.sqlite_test;

import java.io.Serializable;

public class Job implements Serializable {
    private int id;
    private String tieude;
    private String noiDung;

    public Job(int id, String tieude, String noiDung) {
        this.id = id;
        this.tieude = tieude;
        this.noiDung = noiDung;
    }
    public Job(String tieude){
        this.tieude=tieude;
    }

    public Job() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return this.tieude;
    }
}
