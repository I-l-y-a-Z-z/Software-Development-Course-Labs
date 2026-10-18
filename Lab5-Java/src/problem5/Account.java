package problem5;

public class Account {
    protected String emailAddress;
    protected String name;
    private AddressBook[] addressBooks;

    public Account(){
        this("", "");
    }

    public Account(String name, String emailAddress){
        this.name = name;
        this.emailAddress = emailAddress;
        addressBooks = new AddressBook[0];
    }

    public Account(String firstName, String lastName, String emailAddress){
        this((firstName + " " + lastName).trim(), emailAddress);
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public AddressBook[] getAddressBooks(){
        return addressBooks;
    }

    public void addAddressBook(AddressBook book){
        AddressBook[] newBooks = new AddressBook[addressBooks.length + 1];
        for (int i = 0; i < addressBooks.length; i++){
            newBooks[i] = addressBooks[i];
        }
        newBooks[addressBooks.length] = book;
        addressBooks = newBooks;
        book.setAccount(this);
    }

    public void printAllContactDomains(){
        for (AddressBook book : addressBooks){
            for (Contact contact : book.getContacts()){
                System.out.println(contact.getEmailDomain());
            }
        }
    }

    public String getFullName(){
        return name.trim();
    }
}
