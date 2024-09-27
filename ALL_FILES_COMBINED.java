/*****************************************************************************
 *                              Doctor.java                                *
 *****************************************************************************/

/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22

import java.util.*;

/**
 * Specialized employee who looks after patients.
 */
// line 44 "model.ump"
// line 79 "model.ump"
public class Doctor extends Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Doctor Associations
  private List<Patient> patients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Doctor(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
  {
    super(aName, aId, aSalary, aHospital, allWards);
    patients = new ArrayList<Patient>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Patient getPatient(int index)
  {
    Patient aPatient = patients.get(index);
    return aPatient;
  }

  public List<Patient> getPatients()
  {
    List<Patient> newPatients = Collections.unmodifiableList(patients);
    return newPatients;
  }

  public int numberOfPatients()
  {
    int number = patients.size();
    return number;
  }

  public boolean hasPatients()
  {
    boolean has = patients.size() > 0;
    return has;
  }

  public int indexOfPatient(Patient aPatient)
  {
    int index = patients.indexOf(aPatient);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPatients()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPatient(Patient aPatient)
  {
    boolean wasAdded = false;
    if (patients.contains(aPatient)) { return false; }
    patients.add(aPatient);
    if (aPatient.indexOfDoctor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPatient.addDoctor(this);
      if (!wasAdded)
      {
        patients.remove(aPatient);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePatient(Patient aPatient)
  {
    boolean wasRemoved = false;
    if (!patients.contains(aPatient))
    {
      return wasRemoved;
    }

    int oldIndex = patients.indexOf(aPatient);
    patients.remove(oldIndex);
    if (aPatient.indexOfDoctor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPatient.removeDoctor(this);
      if (!wasRemoved)
      {
        patients.add(oldIndex,aPatient);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPatientAt(Patient aPatient, int index)
  {  
    boolean wasAdded = false;
    if(addPatient(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePatientAt(Patient aPatient, int index)
  {
    boolean wasAdded = false;
    if(patients.contains(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPatientAt(aPatient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Patient> copyOfPatients = new ArrayList<Patient>(patients);
    patients.clear();
    for(Patient aPatient : copyOfPatients)
    {
      aPatient.removeDoctor(this);
    }
    super.delete();
  }

}/*****************************************************************************
 *                              Employee.java                                *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


import java.util.*;
import java.sql.Date;

/**
 * The people who work at the hospital.
 */
// line 20 "model.ump"
// line 111 "model.ump"
class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String name;
  private int id;
  private int salary;

  //Employee Associations
  private List<Shift> shifts;
  private List<Privilege> privileges;
  private Hospital hospital;
  private List<Ward> wards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
  {
    name = aName;
    id = aId;
    salary = aSalary;
    shifts = new ArrayList<Shift>();
    privileges = new ArrayList<Privilege>();
    boolean didAddHospital = setHospital(aHospital);
    if (!didAddHospital)
    {
      throw new RuntimeException("Unable to create employee due to hospital. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    wards = new ArrayList<Ward>();
    boolean didAddWards = setWards(allWards);
    if (!didAddWards)
    {
      throw new RuntimeException("Unable to create Employee, must have at least 1 wards. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setSalary(int aSalary)
  {
    boolean wasSet = false;
    salary = aSalary;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public int getSalary()
  {
    return salary;
  }
  /* Code from template association_GetMany */
  public Shift getShift(int index)
  {
    Shift aShift = shifts.get(index);
    return aShift;
  }

  public List<Shift> getShifts()
  {
    List<Shift> newShifts = Collections.unmodifiableList(shifts);
    return newShifts;
  }

  public int numberOfShifts()
  {
    int number = shifts.size();
    return number;
  }

  public boolean hasShifts()
  {
    boolean has = shifts.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shifts.indexOf(aShift);
    return index;
  }
  /* Code from template association_GetMany */
  public Privilege getPrivilege(int index)
  {
    Privilege aPrivilege = privileges.get(index);
    return aPrivilege;
  }

  public List<Privilege> getPrivileges()
  {
    List<Privilege> newPrivileges = Collections.unmodifiableList(privileges);
    return newPrivileges;
  }

  public int numberOfPrivileges()
  {
    int number = privileges.size();
    return number;
  }

  public boolean hasPrivileges()
  {
    boolean has = privileges.size() > 0;
    return has;
  }

  public int indexOfPrivilege(Privilege aPrivilege)
  {
    int index = privileges.indexOf(aPrivilege);
    return index;
  }
  /* Code from template association_GetOne */
  public Hospital getHospital()
  {
    return hospital;
  }
  /* Code from template association_GetMany */
  public Ward getWard(int index)
  {
    Ward aWard = wards.get(index);
    return aWard;
  }

  public List<Ward> getWards()
  {
    List<Ward> newWards = Collections.unmodifiableList(wards);
    return newWards;
  }

  public int numberOfWards()
  {
    int number = wards.size();
    return number;
  }

  public boolean hasWards()
  {
    boolean has = wards.size() > 0;
    return has;
  }

  public int indexOfWard(Ward aWard)
  {
    int index = wards.indexOf(aWard);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfShiftsValid()
  {
    boolean isValid = numberOfShifts() >= minimumNumberOfShifts();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShifts()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Shift addShift(Date aDate, int aStartTime, int aEndTime)
  {
    Shift aNewShift = new Shift(aDate, aStartTime, aEndTime, this);
    return aNewShift;
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shifts.contains(aShift)) { return false; }
    Employee existingEmployee = aShift.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);

    if (isNewEmployee && existingEmployee.numberOfShifts() <= minimumNumberOfShifts())
    {
      return wasAdded;
    }
    if (isNewEmployee)
    {
      aShift.setEmployee(this);
    }
    else
    {
      shifts.add(aShift);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    //Unable to remove aShift, as it must always have a employee
    if (this.equals(aShift.getEmployee()))
    {
      return wasRemoved;
    }

    //employee already at minimum (1)
    if (numberOfShifts() <= minimumNumberOfShifts())
    {
      return wasRemoved;
    }

    shifts.remove(aShift);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shifts.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPrivilegesValid()
  {
    boolean isValid = numberOfPrivileges() >= minimumNumberOfPrivileges();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPrivileges()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Privilege addPrivilege(String aPrivilege)
  {
    Privilege aNewPrivilege = new Privilege(aPrivilege, this);
    return aNewPrivilege;
  }

  public boolean addPrivilege(Privilege aPrivilege)
  {
    boolean wasAdded = false;
    if (privileges.contains(aPrivilege)) { return false; }
    Employee existingEmployee = aPrivilege.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);

    if (isNewEmployee && existingEmployee.numberOfPrivileges() <= minimumNumberOfPrivileges())
    {
      return wasAdded;
    }
    if (isNewEmployee)
    {
      aPrivilege.setEmployee(this);
    }
    else
    {
      privileges.add(aPrivilege);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePrivilege(Privilege aPrivilege)
  {
    boolean wasRemoved = false;
    //Unable to remove aPrivilege, as it must always have a employee
    if (this.equals(aPrivilege.getEmployee()))
    {
      return wasRemoved;
    }

    //employee already at minimum (1)
    if (numberOfPrivileges() <= minimumNumberOfPrivileges())
    {
      return wasRemoved;
    }

    privileges.remove(aPrivilege);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPrivilegeAt(Privilege aPrivilege, int index)
  {  
    boolean wasAdded = false;
    if(addPrivilege(aPrivilege))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrivileges()) { index = numberOfPrivileges() - 1; }
      privileges.remove(aPrivilege);
      privileges.add(index, aPrivilege);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePrivilegeAt(Privilege aPrivilege, int index)
  {
    boolean wasAdded = false;
    if(privileges.contains(aPrivilege))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrivileges()) { index = numberOfPrivileges() - 1; }
      privileges.remove(aPrivilege);
      privileges.add(index, aPrivilege);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPrivilegeAt(aPrivilege, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHospital(Hospital aHospital)
  {
    boolean wasSet = false;
    if (aHospital == null)
    {
      return wasSet;
    }

    Hospital existingHospital = hospital;
    hospital = aHospital;
    if (existingHospital != null && !existingHospital.equals(aHospital))
    {
      existingHospital.removeEmployee(this);
    }
    hospital.addEmployee(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWardsValid()
  {
    boolean isValid = numberOfWards() >= minimumNumberOfWards();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWards()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWard(Ward aWard)
  {
    boolean wasAdded = false;
    if (wards.contains(aWard)) { return false; }
    wards.add(aWard);
    if (aWard.indexOfEmployee(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWard.addEmployee(this);
      if (!wasAdded)
      {
        wards.remove(aWard);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeWard(Ward aWard)
  {
    boolean wasRemoved = false;
    if (!wards.contains(aWard))
    {
      return wasRemoved;
    }

    if (numberOfWards() <= minimumNumberOfWards())
    {
      return wasRemoved;
    }

    int oldIndex = wards.indexOf(aWard);
    wards.remove(oldIndex);
    if (aWard.indexOfEmployee(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWard.removeEmployee(this);
      if (!wasRemoved)
      {
        wards.add(oldIndex,aWard);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setWards(Ward... newWards)
  {
    boolean wasSet = false;
    ArrayList<Ward> verifiedWards = new ArrayList<Ward>();
    for (Ward aWard : newWards)
    {
      if (verifiedWards.contains(aWard))
      {
        continue;
      }
      verifiedWards.add(aWard);
    }

    if (verifiedWards.size() != newWards.length || verifiedWards.size() < minimumNumberOfWards())
    {
      return wasSet;
    }

    ArrayList<Ward> oldWards = new ArrayList<Ward>(wards);
    wards.clear();
    for (Ward aNewWard : verifiedWards)
    {
      wards.add(aNewWard);
      if (oldWards.contains(aNewWard))
      {
        oldWards.remove(aNewWard);
      }
      else
      {
        aNewWard.addEmployee(this);
      }
    }

    for (Ward anOldWard : oldWards)
    {
      anOldWard.removeEmployee(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWardAt(Ward aWard, int index)
  {  
    boolean wasAdded = false;
    if(addWard(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWardAt(Ward aWard, int index)
  {
    boolean wasAdded = false;
    if(wards.contains(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWardAt(aWard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=shifts.size(); i > 0; i--)
    {
      Shift aShift = shifts.get(i - 1);
      aShift.delete();
    }
    for(int i=privileges.size(); i > 0; i--)
    {
      Privilege aPrivilege = privileges.get(i - 1);
      aPrivilege.delete();
    }
    Hospital placeholderHospital = hospital;
    this.hospital = null;
    if(placeholderHospital != null)
    {
      placeholderHospital.removeEmployee(this);
    }
    ArrayList<Ward> copyOfWards = new ArrayList<Ward>(wards);
    wards.clear();
    for(Ward aWard : copyOfWards)
    {
      aWard.removeEmployee(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "," +
            "salary" + ":" + getSalary()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hospital = "+(getHospital()!=null?Integer.toHexString(System.identityHashCode(getHospital())):"null");
  }
}/*****************************************************************************
 *                              Hospital.java                                *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


import java.util.*;

/**
 * Hospital system - sample UML class diagram in Umple
 */
// line 4 "model.ump"
// line 72 "model.ump"
public class Hospital
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hospital Associations
  private List<Employee> employees;
  private List<Ward> wards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hospital()
  {
    employees = new ArrayList<Employee>();
    wards = new ArrayList<Ward>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Ward getWard(int index)
  {
    Ward aWard = wards.get(index);
    return aWard;
  }

  public List<Ward> getWards()
  {
    List<Ward> newWards = Collections.unmodifiableList(wards);
    return newWards;
  }

  public int numberOfWards()
  {
    int number = wards.size();
    return number;
  }

  public boolean hasWards()
  {
    boolean has = wards.size() > 0;
    return has;
  }

  public int indexOfWard(Ward aWard)
  {
    int index = wards.indexOf(aWard);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Employee addEmployee(String aName, int aId, int aSalary, Ward... allWards)
  {
    return new Employee(aName, aId, aSalary, this, allWards);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    Hospital existingHospital = aEmployee.getHospital();
    boolean isNewHospital = existingHospital != null && !this.equals(existingHospital);
    if (isNewHospital)
    {
      aEmployee.setHospital(this);
    }
    else
    {
      employees.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a hospital
    if (!this.equals(aEmployee.getHospital()))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWardsValid()
  {
    boolean isValid = numberOfWards() >= minimumNumberOfWards();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWards()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Ward addWard(String aName, int aCapacity)
  {
    Ward aNewWard = new Ward(aName, aCapacity, this);
    return aNewWard;
  }

  public boolean addWard(Ward aWard)
  {
    boolean wasAdded = false;
    if (wards.contains(aWard)) { return false; }
    Hospital existingHospital = aWard.getHospital();
    boolean isNewHospital = existingHospital != null && !this.equals(existingHospital);

    if (isNewHospital && existingHospital.numberOfWards() <= minimumNumberOfWards())
    {
      return wasAdded;
    }
    if (isNewHospital)
    {
      aWard.setHospital(this);
    }
    else
    {
      wards.add(aWard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWard(Ward aWard)
  {
    boolean wasRemoved = false;
    //Unable to remove aWard, as it must always have a hospital
    if (this.equals(aWard.getHospital()))
    {
      return wasRemoved;
    }

    //hospital already at minimum (1)
    if (numberOfWards() <= minimumNumberOfWards())
    {
      return wasRemoved;
    }

    wards.remove(aWard);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWardAt(Ward aWard, int index)
  {  
    boolean wasAdded = false;
    if(addWard(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWardAt(Ward aWard, int index)
  {
    boolean wasAdded = false;
    if(wards.contains(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWardAt(aWard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=employees.size(); i > 0; i--)
    {
      Employee aEmployee = employees.get(i - 1);
      aEmployee.delete();
    }
    for(int i=wards.size(); i > 0; i--)
    {
      Ward aWard = wards.get(i - 1);
      aWard.delete();
    }
  }

}/*****************************************************************************
 *                              Janitor.java                                *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


/**
 * Employee who maintains the cleanliness of the hospital.
 */
// line 56 "model.ump"
// line 89 "model.ump"
public class Janitor extends Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Janitor(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
  {
    super(aName, aId, aSalary, aHospital, allWards);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}/*****************************************************************************
 *                              Patient.java                                 *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


import java.util.*;

/**
 * Patient who is at the hospital to get better.
 */
// line 62 "model.ump"
// line 94 "model.ump"
public class Patient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Patient Attributes
  private String name;

  //Patient Associations
  private List<Doctor> doctors;
  private Ward ward;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Patient(String aName, Ward aWard)
  {
    name = aName;
    doctors = new ArrayList<Doctor>();
    boolean didAddWard = setWard(aWard);
    if (!didAddWard)
    {
      throw new RuntimeException("Unable to create patient due to ward. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Doctor getDoctor(int index)
  {
    Doctor aDoctor = doctors.get(index);
    return aDoctor;
  }

  public List<Doctor> getDoctors()
  {
    List<Doctor> newDoctors = Collections.unmodifiableList(doctors);
    return newDoctors;
  }

  public int numberOfDoctors()
  {
    int number = doctors.size();
    return number;
  }

  public boolean hasDoctors()
  {
    boolean has = doctors.size() > 0;
    return has;
  }

  public int indexOfDoctor(Doctor aDoctor)
  {
    int index = doctors.indexOf(aDoctor);
    return index;
  }
  /* Code from template association_GetOne */
  public Ward getWard()
  {
    return ward;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDoctors()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addDoctor(Doctor aDoctor)
  {
    boolean wasAdded = false;
    if (doctors.contains(aDoctor)) { return false; }
    doctors.add(aDoctor);
    if (aDoctor.indexOfPatient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aDoctor.addPatient(this);
      if (!wasAdded)
      {
        doctors.remove(aDoctor);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeDoctor(Doctor aDoctor)
  {
    boolean wasRemoved = false;
    if (!doctors.contains(aDoctor))
    {
      return wasRemoved;
    }

    int oldIndex = doctors.indexOf(aDoctor);
    doctors.remove(oldIndex);
    if (aDoctor.indexOfPatient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aDoctor.removePatient(this);
      if (!wasRemoved)
      {
        doctors.add(oldIndex,aDoctor);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDoctorAt(Doctor aDoctor, int index)
  {  
    boolean wasAdded = false;
    if(addDoctor(aDoctor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDoctors()) { index = numberOfDoctors() - 1; }
      doctors.remove(aDoctor);
      doctors.add(index, aDoctor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDoctorAt(Doctor aDoctor, int index)
  {
    boolean wasAdded = false;
    if(doctors.contains(aDoctor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDoctors()) { index = numberOfDoctors() - 1; }
      doctors.remove(aDoctor);
      doctors.add(index, aDoctor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDoctorAt(aDoctor, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setWard(Ward aWard)
  {
    boolean wasSet = false;
    if (aWard == null)
    {
      return wasSet;
    }

    Ward existingWard = ward;
    ward = aWard;
    if (existingWard != null && !existingWard.equals(aWard))
    {
      existingWard.removePatient(this);
    }
    ward.addPatient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Doctor> copyOfDoctors = new ArrayList<Doctor>(doctors);
    doctors.clear();
    for(Doctor aDoctor : copyOfDoctors)
    {
      aDoctor.removePatient(this);
    }
    Ward placeholderWard = ward;
    this.ward = null;
    if(placeholderWard != null)
    {
      placeholderWard.removePatient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ward = "+(getWard()!=null?Integer.toHexString(System.identityHashCode(getWard())):"null");
  }
}/*****************************************************************************
 *                              Privilege.java                               *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22



/**
 * Various privileges and roles that the employees have.
 */
// line 38 "model.ump"
// line 106 "model.ump"
public class Privilege
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Privilege Attributes
  private String privilege;

  //Privilege Associations
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Privilege(String aPrivilege, Employee aEmployee)
  {
    privilege = aPrivilege;
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create privilege due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrivilege(String aPrivilege)
  {
    boolean wasSet = false;
    privilege = aPrivilege;
    wasSet = true;
    return wasSet;
  }

  public String getPrivilege()
  {
    return privilege;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    //Must provide employee to privilege
    if (aEmployee == null)
    {
      return wasSet;
    }

    if (employee != null && employee.numberOfPrivileges() <= Employee.minimumNumberOfPrivileges())
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      boolean didRemove = existingEmployee.removePrivilege(this);
      if (!didRemove)
      {
        employee = existingEmployee;
        return wasSet;
      }
    }
    employee.addPrivilege(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removePrivilege(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "privilege" + ":" + getPrivilege()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}/*****************************************************************************
 *                              Shift.java                                   *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


import java.sql.Date;

/**
 * Working shifts that the employees have.
 */
// line 30 "model.ump"
// line 101 "model.ump"
public class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private Date date;
  private int startTime;
  private int endTime;

  //Shift Associations
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(Date aDate, int aStartTime, int aEndTime, Employee aEmployee)
  {
    date = aDate;
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create shift due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(int aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(int aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getStartTime()
  {
    return startTime;
  }

  public int getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    //Must provide employee to shift
    if (aEmployee == null)
    {
      return wasSet;
    }

    if (employee != null && employee.numberOfShifts() <= Employee.minimumNumberOfShifts())
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      boolean didRemove = existingEmployee.removeShift(this);
      if (!didRemove)
      {
        employee = existingEmployee;
        return wasSet;
      }
    }
    employee.addShift(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removeShift(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startTime" + ":" + getStartTime()+ "," +
            "endTime" + ":" + getEndTime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}/*****************************************************************************
 *                              Surgeon.java                                 *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


/**
 * Specialized doctor who performs advanced procedures on patients.
 */
// line 50 "model.ump"
// line 84 "model.ump"
public class Surgeon extends Doctor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Surgeon(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
  {
    super(aName, aId, aSalary, aHospital, allWards);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}/*****************************************************************************
 *                              Ward.java                                    *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22

import java.util.*;

/**
 * Subsections within the hospital.
 */
// line 11 "model.ump"
// line 118 "model.ump"
public class Ward
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ward Attributes
  private String name;
  private int capacity;

  //Ward Associations
  private List<Employee> employees;
  private List<Patient> patients;
  private Hospital hospital;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ward(String aName, int aCapacity, Hospital aHospital)
  {
    name = aName;
    capacity = aCapacity;
    employees = new ArrayList<Employee>();
    patients = new ArrayList<Patient>();
    boolean didAddHospital = setHospital(aHospital);
    if (!didAddHospital)
    {
      throw new RuntimeException("Unable to create ward due to hospital. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCapacity(int aCapacity)
  {
    boolean wasSet = false;
    capacity = aCapacity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getCapacity()
  {
    return capacity;
  }
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Patient getPatient(int index)
  {
    Patient aPatient = patients.get(index);
    return aPatient;
  }

  public List<Patient> getPatients()
  {
    List<Patient> newPatients = Collections.unmodifiableList(patients);
    return newPatients;
  }

  public int numberOfPatients()
  {
    int number = patients.size();
    return number;
  }

  public boolean hasPatients()
  {
    boolean has = patients.size() > 0;
    return has;
  }

  public int indexOfPatient(Patient aPatient)
  {
    int index = patients.indexOf(aPatient);
    return index;
  }
  /* Code from template association_GetOne */
  public Hospital getHospital()
  {
    return hospital;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    employees.add(aEmployee);
    if (aEmployee.indexOfWard(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEmployee.addWard(this);
      if (!wasAdded)
      {
        employees.remove(aEmployee);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    if (!employees.contains(aEmployee))
    {
      return wasRemoved;
    }

    int oldIndex = employees.indexOf(aEmployee);
    employees.remove(oldIndex);
    if (aEmployee.indexOfWard(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEmployee.removeWard(this);
      if (!wasRemoved)
      {
        employees.add(oldIndex,aEmployee);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPatients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Patient addPatient(String aName)
  {
    return new Patient(aName, this);
  }

  public boolean addPatient(Patient aPatient)
  {
    boolean wasAdded = false;
    if (patients.contains(aPatient)) { return false; }
    Ward existingWard = aPatient.getWard();
    boolean isNewWard = existingWard != null && !this.equals(existingWard);
    if (isNewWard)
    {
      aPatient.setWard(this);
    }
    else
    {
      patients.add(aPatient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePatient(Patient aPatient)
  {
    boolean wasRemoved = false;
    //Unable to remove aPatient, as it must always have a ward
    if (!this.equals(aPatient.getWard()))
    {
      patients.remove(aPatient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPatientAt(Patient aPatient, int index)
  {  
    boolean wasAdded = false;
    if(addPatient(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePatientAt(Patient aPatient, int index)
  {
    boolean wasAdded = false;
    if(patients.contains(aPatient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPatients()) { index = numberOfPatients() - 1; }
      patients.remove(aPatient);
      patients.add(index, aPatient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPatientAt(aPatient, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setHospital(Hospital aHospital)
  {
    boolean wasSet = false;
    //Must provide hospital to ward
    if (aHospital == null)
    {
      return wasSet;
    }

    if (hospital != null && hospital.numberOfWards() <= Hospital.minimumNumberOfWards())
    {
      return wasSet;
    }

    Hospital existingHospital = hospital;
    hospital = aHospital;
    if (existingHospital != null && !existingHospital.equals(aHospital))
    {
      boolean didRemove = existingHospital.removeWard(this);
      if (!didRemove)
      {
        hospital = existingHospital;
        return wasSet;
      }
    }
    hospital.addWard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Employee> copyOfEmployees = new ArrayList<Employee>(employees);
    employees.clear();
    for(Employee aEmployee : copyOfEmployees)
    {
      if (aEmployee.numberOfWards() <= Employee.minimumNumberOfWards())
      {
        aEmployee.delete();
      }
      else
      {
        aEmployee.removeWard(this);
      }
    }
    for(int i=patients.size(); i > 0; i--)
    {
      Patient aPatient = patients.get(i - 1);
      aPatient.delete();
    }
    Hospital placeholderHospital = hospital;
    this.hospital = null;
    if(placeholderHospital != null)
    {
      placeholderHospital.removeWard(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "capacity" + ":" + getCapacity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hospital = "+(getHospital()!=null?Integer.toHexString(System.identityHashCode(getHospital())):"null");
  }
}