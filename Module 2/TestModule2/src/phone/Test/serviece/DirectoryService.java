package phone.Test.serviece;

import phone.Test.model.PhoneDirectory;
import phone.Test.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class DirectoryService {
    String path = "data/contacts.csv";

    public DirectoryService() {
        getItem();
    }

    public List<PhoneDirectory> getItem() {
        List<PhoneDirectory> newDirectory = new ArrayList<>();
        List<String> records = CSVUtils.read(path);

        for (String record : records) {
            newDirectory.add(PhoneDirectory.parseAdmin(record));
        }
        return newDirectory;
    }


    public void add(PhoneDirectory newDirectory) {
        List<PhoneDirectory> telephoneDirectories = getItem();
        telephoneDirectories.add(newDirectory);
        CSVUtils.write(path, telephoneDirectories);
    }

    public void remove(PhoneDirectory newTelephone) {
        List<PhoneDirectory> phoneDirectories = getItem();
        phoneDirectories.removeIf(phoneDirectory -> phoneDirectory.getPhone().equalsIgnoreCase(newTelephone.getPhone()));
        CSVUtils.write(path, phoneDirectories);
    }


    public boolean getDiectoryByName(String name) {
        List<PhoneDirectory> phoneDirectories = getItem();
        for (PhoneDirectory directory : phoneDirectories) {
            if (directory.getFullname().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }

    public boolean getDiectorryByNumber(String phone) {
        List<PhoneDirectory> phoneDirectories = getItem();
        for (PhoneDirectory directory : phoneDirectories) {
            if (directory.getPhone().equalsIgnoreCase(phone)) {
                return true;
            }
        }
        return false;
    }

    public PhoneDirectory getDiectoryByPhoneNumber(String phone) {
        List<PhoneDirectory> phoneDirectories = getItem();
        for (PhoneDirectory directory : phoneDirectories) {
            if (directory.getPhone().equalsIgnoreCase(phone)) {
                return directory;
            }
        }
        return null;
    }
}
