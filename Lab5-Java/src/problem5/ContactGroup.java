package problem5;

public class ContactGroup {
    protected String name;
    private AddressBook addressBook;
    private Contact[] contacts;
    private ContactGroup parent;
    private ContactGroup[] children;

    public ContactGroup(){
        this("");
    }

    public ContactGroup(String name){
        this.name = name;
        contacts = new Contact[0];
        children = new ContactGroup[0];
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public AddressBook getAddressBook(){
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
        for (ContactGroup child : children){
            child.setAddressBook(addressBook);
        }
    }

    public Contact[] getContacts(){
        return contacts;
    }

    public void addContact(Contact contact){
        Contact[] newContacts = new Contact[contacts.length + 1];
        for (int i = 0; i < contacts.length; i++){
            newContacts[i] = contacts[i];
        }
        newContacts[contacts.length] = contact;
        contacts = newContacts;
        contact.addGroup(this);
    }

    public ContactGroup getParent(){
        return parent;
    }

    public ContactGroup[] getChildren(){
        return children;
    }

    public void addChild(ContactGroup child){
        ContactGroup[] newChildren = new ContactGroup[children.length + 1];
        for (int i = 0; i < children.length; i++){
            newChildren[i] = children[i];
        }
        newChildren[children.length] = child;
        children = newChildren;
        child.parent = this;
        child.setAddressBook(addressBook);
    }

    public String buildGroupReport(){
        StringBuilder result = new StringBuilder();
        result.append("Group: ").append(name).append("\n");
        for (Contact contact : contacts){
            result.append(contact.getContactSummary()).append("\n");
        }
        for (ContactGroup child : children){
            result.append(child.buildGroupReport());
        }
        return result.toString();
    }
}
