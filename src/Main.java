import java.util.ArrayList;

class Edition {
    private String name;
    private String author;
    private int yearOfPublication;
    private Department department;

    public Edition(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getYearOfPublication() {
        return 0;
    }

    public Department getDepartment() {
        return null;
    }
}

class Department {
    private String genre;
    private ArrayList<Edition> editions = new ArrayList<>();
    private Library library;

    public Department(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<Edition> getEditions() {
        return editions;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        edition.setDepartment(this);
        library.updateNumberOfEditions();
    }

    public void removeEdition(Edition edition) {
        editions.remove(edition);
        edition.setDepartment(null);
        library.updateNumberOfEditions();
    }
}

class Library {
    private String name;
    private ArrayList<Department> departments = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
        department.setLibrary(this);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
        department.setLibrary(null);
    }

    public void updateNumberOfEditions() {
    }

    public ArrayList<Edition> searchPublicationsByYear(int year) {
        ArrayList<Edition> foundPublications = new ArrayList<>();
        for (Department department : departments) {
            for (Edition edition : department.getEditions()) {
                if (edition.getYearOfPublication() == year) {
                    foundPublications.add(edition);
                }
            }
        }
        return foundPublications;
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Main Library");
        Department department1 = new Department("Science Fiction");
        Department department2 = new Department("Fantasy");
        Edition edition1 = new Edition("Book 1", "Author 1", 2013);
        Edition edition2 = new Edition("Book 2", "Author 2", 2013);
        Edition edition3 = new Edition("Book 3", "Author 3", 2023);

        library.addDepartment(department1);
        library.addDepartment(department2);
        department1.addEdition(edition1);
        department1.addEdition(edition2);
        department2.addEdition(edition3);

        displayDepartmentInfo(library);

        library.removeDepartment(department2);

        displayDepartmentInfo(library);

        int searchYear = 2013;
        ArrayList<Edition> foundPublications = library.searchPublicationsByYear(searchYear);

        displayFoundPublications(foundPublications);
    }

    private static void displayDepartmentInfo(Library library) {
        for (Department department : library.getDepartments()) {
            System.out.println("Department: " + department.getGenre());
            System.out.println("Number of Publications: " + department.getEditions().size());
        }
        System.out.println("------");
    }
    private static void displayFoundPublications(ArrayList<Edition> foundPublications) {
        for (Edition publication : foundPublications) {
            System.out.println("Publication: " + publication.getName());
            System.out.println("Department: " + publication.getDepartment().getGenre());
        }
    }
}
