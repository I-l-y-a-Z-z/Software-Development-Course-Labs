package problem5;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("Ilyas", "Rahmouni", "ilyas@example.com");
        AddressBook personal = new AddressBook("Personal");
        AddressBook work = new AddressBook("Work");
        account.addAddressBook(personal);
        account.addAddressBook(work);

        Contact youness = new Contact("Youness Latif", "youness@example.com", "0512345678", "Email");
        Contact anass = new Contact("Anass Fertat", "anass@example.org", "0523456789", "Fax");
        Contact amine = new Contact("Amine Chrif", "amine@example.net", "0534567890", "Email");
        personal.addContact(youness);
        personal.insertContact(anass);
        work.addContact(amine);

        ContactGroup friends = new ContactGroup("Friends");
        ContactGroup classmates = new ContactGroup("Classmates");
        friends.addContact(youness);
        classmates.addContact(anass);
        friends.addChild(classmates);
        personal.addGroup(friends);

        System.out.println("Account: " + account.getFullName());
        for (AddressBook book : account.getAddressBooks()){
            System.out.println(book.getSummary());
        }

        System.out.println("\nContact email domains:");
        account.printAllContactDomains();

        Contact found = personal.findByEmail("anass@example.org");
        if (found != null){
            System.out.println("\nContact found: " + found.getContactSummary());
        }

        System.out.println("\n" + friends.buildGroupReport());
    }
}
