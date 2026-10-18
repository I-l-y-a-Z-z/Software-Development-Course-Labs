package problem5;

public class AddressBook {
    protected String name;
    private Account account;
    private Contact[] contacts;
    private ContactGroup[] groups;

    public AddressBook(){
        this("");
    }

    public AddressBook(String name){
        this.name = name;
        contacts = new Contact[0];
        groups = new ContactGroup[0];
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public Contact getContact(){
        if (contacts.length == 0){
            return null;
        }
        return contacts[0];
    }

    public Contact[] getContacts(){
        return contacts;
    }

    public void insertContact(Contact contact){
        addContact(contact);
    }

    public void addContact(Contact contact){
        Contact[] newContacts = new Contact[contacts.length + 1];
        for (int i = 0; i < contacts.length; i++){
            newContacts[i] = contacts[i];
        }
        newContacts[contacts.length] = contact;
        contacts = newContacts;
        contact.setAddressBook(this);
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
        group.setAddressBook(this);
    }

    public String getSummary(){
        return name + ": " + contacts.length + " contacts, " + groups.length + " groups";
    }

    public Contact findByEmail(String email){
        for (Contact contact : contacts){
            if (contact.getEmailAddress().equalsIgnoreCase(email)){
                return contact;
            }
        }
        return null;
    }
}
