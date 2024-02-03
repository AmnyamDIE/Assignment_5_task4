import java.util.ArrayList;
import java.util.List;

class Edition {
    private String title;
    private int yearOfRelease;
    private Department department;

    public Edition(String title, int yearOfRelease) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }
}

class Department {
    private String name;
    private Library library;
    private List<Edition> editions;

    public Department(String name) {
        this.name = name;
        this.editions = new ArrayList<>();
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        edition.setDepartment(this);
    }

    public void removeEdition(Edition edition) {
        editions.remove(edition);
        edition.setDepartment(null);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfEditions() {
        return editions.size();
    }
}

class Library {
    private String name;
    private List<Department> departments;

    public Library(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public List<Department> getDepartments() {
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

    public List<Edition> searchPublicationsByYear(int year) {
        List<Edition> foundPublications = new ArrayList<>();
        for (Department department : departments) {
            for (Edition edition : department.getEditions()) {
                if (edition.getYearOfRelease() == year) {
                    foundPublications.add(edition);
                }
            }
        }
        return foundPublications;
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Central Library");

        Department department1 = new Department("Science");
        Department department2 = new Department("History");
        Department department3 = new Department("Fiction");

        Edition publication1 = new Edition("Book1", 2020);
        Edition publication2 = new Edition("Book2", 2019);
        Edition publication3 = new Edition("Book3", 2020);

        library.addDepartment(department1);
        library.addDepartment(department2);
        library.addDepartment(department3);

        department1.addEdition(publication1);
        department1.addEdition(publication2);

        department2.addEdition(publication3);

        department3.addEdition(new Edition("Novel1", 2018));

        // Display information
        displayDepartmentPublications(library);

        // Remove the second department
        library.removeDepartment(department2);

        // Display information again
        System.out.println("\nAfter removing the 'History' department:");
        displayDepartmentPublications(library);

        // Search for publications by year
        int searchYear = 2020;
        List<Edition> foundPublications = library.searchPublicationsByYear(searchYear);

        System.out.println("\nPublications released in " + searchYear + ":");
        for (Edition edition : foundPublications) {
            System.out.println("Title: " + edition.getTitle() + ", Department: " + edition.getDepartment().getName());
        }
    }

    private static void displayDepartmentPublications(Library library) {
        System.out.println("Library: " + library.getName());
        for (Department department : library.getDepartments()) {
            System.out.println("\nDepartment: " + department.getName());
            System.out.println("Number of Publications: " + department.getNumberOfEditions());
            System.out.println("Publications:");
            for (Edition edition : department.getEditions()) {
                System.out.println("Title: " + edition.getTitle() + ", Year: " + edition.getYearOfRelease());
            }
        }
    }
}