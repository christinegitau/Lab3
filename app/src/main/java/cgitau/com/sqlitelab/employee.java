package cgitau.com.sqlitelab;


/**
 * Created by cgita on 10/23/2017.
 */

public class employee {
    int _Id;
    int Salary;
    String Department;
    String Rank;


    //empty constructor
    public employee(){

    }

    //constructor
    public employee(int _Id, int Salary, String Department, String Rank){
        this._Id= _Id;
        this.Salary = Salary;
        this.Department = Department;
        this.Rank = Rank;
    }

    public employee(int Salary, String Department, String Rank){
        this.Salary = Salary;
        this.Department = Department;
        this.Rank = Rank;
    }

    public int getId() {
        return _Id;
    }

    public void setId(int id) {
        this._Id = id;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        this.Salary = salary;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        this.Department = department;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        this.Rank = rank;
    }

}
