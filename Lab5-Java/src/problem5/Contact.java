package problem5;

public class Contact {
    protected String emailAddress;
    protected String faxNumber;
    protected String name;
    protected String primaryContactMethod;
    private AddressBook addressBook;
    private ContactGroup[] groups;

    public Contact(){
        this("", "", "", "");
    }

    public Contact(String name, String emailAddress, String faxNumber, String primaryContactMethod){
        this.name = name;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.primaryContactMethod = primaryContactMethod;
        groups = new ContactGroup[0];
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getFaxNumber(){
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber){
        this.faxNumber = faxNumber;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPrimaryContactMethod(){
        return primaryContactMethod;
    }

    public void setPrimaryContactMethod(String primaryContactMethod){
        this.primaryContactMethod = primaryContactMethod;
    }

    public AddressBook getAddressBook(){
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    public ContactGroup[] getGroups(){
        return groups;
    }

    public void addGroup(ContactGroup group){
        ContactGroup[] newGroups = new ContactGroup[groups.length + 1];
        for (int i = 0; i < groups.length; i++){
            newGroups[i] = groups[i];
        }
        newGroups[groups.length] = group;
        groups = newGroups;
    }

    public String getEmailDomain(){
        int index = emailAddress.indexOf('@');
        if (index == -1){
            return "";
        }
        return emailAddress.substring(index + 1);
    }

    public String getContactSummary(){
        return name + " - Email: " + emailAddress + ", Fax: " + faxNumber
                + ", Primary contact method: " + primaryContactMethod;
    }
}
