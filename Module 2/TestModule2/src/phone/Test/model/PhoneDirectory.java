package phone.Test.model;

public class PhoneDirectory {
    private String phone;
    private String team;
    private String fullname;
    private String genner;
    private String address;
    private String birtdate;
    private String email;

    public PhoneDirectory() {

    }

    public PhoneDirectory(String phone, String team, String fullname, String genner, String address, String birtdate, String email) {
        this.phone = phone;
        this.team = team;
        this.fullname = fullname;
        this.genner = genner;
        this.address = address;
        this.birtdate = birtdate;
        this.email = email;
    }

    public static PhoneDirectory parseAdmin(String record) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        String[] fields = record.split(",");
        phoneDirectory.phone = fields[0].trim();
        phoneDirectory.team = fields[1].trim();
        phoneDirectory.fullname = fields[2].trim();
        phoneDirectory.genner = fields[3].trim();
        phoneDirectory.address = fields[4].trim();
        phoneDirectory.birtdate = fields[5].trim();
        phoneDirectory.email = fields[6].trim();

        return phoneDirectory;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGenner() {
        return genner;
    }

    public void setGenner(String genner) {
        this.genner = genner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirtdate() {
        return birtdate;
    }

    public void setBirtdate(String birtdate) {
        this.birtdate = birtdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return phone + ',' +
                team + ',' +
                fullname + ',' +
                genner + ',' +
                address + ',' +
                birtdate + ',' +
                email + ',' ;
    }
}
