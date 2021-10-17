package sample.User;

import sample.Exceptions.ExistingLogin;
import sample.Persons.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class Users is made to organise and control all data about user of the application.
 *
 * This class includes different Array Lists {@link Users#ph}, {@link Users#entr1}, {@link Users#entr2} a {@link Users#jr}.
 * Each of them has 3 methods.
 *<br>
 * ph:<br>
 * - {@link Users#CreatePhysical()}<br>
 * - {@link Users#CheckPhysical(String, String)}<br>
 * - {@link Users#AddorChangePhysical(int, PhysicalPerson)}<br>
 *
 * ent1:<br>
 * - {@link Users#CreateEntrepreneur()}<br>
 * - {@link Users#CheckEntrepreneur(String, String)}<br>
 * - {@link Users#AddorChangeEntrepreneur(int, NaturalPersonEntrepreneur1)}<br>
 *
 * ent2:<br>
 * - {@link Users#CreateEntrepreneur2()}<br>
 * - {@link Users#CheckEntrepreneur2(String, String)}<br>
 * - {@link Users#AddorChangeEntrepreneur2(int, NaturalPersonEntrepreneur2)}<br>
 *
 * jr:<br>
 * - {@link Users#CreateJuridical()}<br>
 * - {@link Users#CheckJuridical(String, String)}<br>
 * - {@link Users#AddorChangeJuridical(int, JuridicalPerson)}
 *
 */
public class Users {

    /**
     * Array list of physical persons
     *
     * @see Users
     */
    public ArrayList<PhysicalPerson> ph = new ArrayList<>();
    /**
     * Array list of entrepreneurs of type 1
     *
     * @see Users
     */
    public ArrayList<NaturalPersonEntrepreneur1> entr1 = new ArrayList<>();
    /**
     * Array list of entrepreneurs of type 2
     *
     * @see Users
     */
    public ArrayList<NaturalPersonEntrepreneur2> entr2 = new ArrayList<>();
    /**
     * Array list of juridical persons
     *
     * @see Users
     */
    public ArrayList<JuridicalPerson> jr = new ArrayList<>();

    /**
     * Method CreatePhysical is made for filling array list {@link Users#ph} with physical persons from file:
     *
     * @throws IOException            in case the file is empty.
     * @throws ClassNotFoundException in case the file does note contains objects of needed class ({@link PhysicalPerson})
     * @see Users
     */
    public void CreatePhysical() throws IOException, ClassNotFoundException {

        PhysicalPerson physical = null;
        FileInputStream f = new FileInputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personPH.out");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(f);
            while (true) {
                try {
                    physical = (PhysicalPerson) objectInputStream.readObject();
                    ph.add(physical);
                } catch (EOFException ex) {
                    System.out.println("catched 1");
                    break;
                }

            }

        } catch (EOFException e) {
            System.out.println("catched 2");
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }

    }

    /**
     * This method is called when registration to check if there exist any other user with the same username.
     *
     * @param physicalPerson -  user, which is signing up.
     * @throws ExistingLogin (own exception)
     */
    public void CheckExistingPhy(PhysicalPerson physicalPerson) throws ExistingLogin {
        int i = 0;
        for (PhysicalPerson x : ph) {
            if (x.login.equals(physicalPerson.login)) {
                i++;
                throw new ExistingLogin("Login already exist. Create new one!");
            }
        }
        if (i == 0) {
            System.out.println("good");
            try {
                AddorChangePhysical(100, physicalPerson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method is made for checking if there is a physical person while signing in:
     *
     * @param login    is a String which was written while signing in
     * @param password is a String which was written while signing in
     * @return the method returns ordinal number of user in an appropriate arraylist ({@link Users#ph}) to show its information in userpage
     * @see Users
     */
    public String CheckPhysical(String login, String password) {
        int i = 0;
        int j = 0;
        if (ph != null) {
            for (PhysicalPerson pp : ph) {
                j++;
                if (pp.login.equals(login) && pp.password.equals(password)) {
                    i++;
                    break;
                }
            }
        }
        if (i == 1) {
            return String.valueOf(j - 1);
        } else return null;
    }

    /**
     * Method is made for adding a new physical person after registration or changing an existing one while working in "userpage"
     *
     * @param number         is an ordinal number of this user in appropriate arraylist ({@link Users#ph}), if the user is not in it yet( number = 100) it will just add it to the end.
     * @param physicalPerson is a user which will be placed to its old place or add to the end
     * @return the method returns ordinal number of user in an appropriate arraylist ({@link Users#ph}) work with in while calling this method next time again
     * @throws IOException in case the file is empty.
     * @see Users
     */
    public int AddorChangePhysical(int number, PhysicalPerson physicalPerson) throws IOException {

        if (number < ph.size()) {
            ph.remove(number);
        }
        ph.add(physicalPerson);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personPH.out"));
        objectOutputStream.flush();
        for (PhysicalPerson physical : ph
        ) {
            System.out.println(physical.BirthYear);
            objectOutputStream.writeObject(physical);
        }

        objectOutputStream.close();
        return ph.indexOf(physicalPerson);
    }

    /**
     * Method is made for filling array list {@link Users#jr} with juridical persons from file
     *
     * @throws IOException            ina case the file is empty
     * @throws ClassNotFoundException in case the file does note contains objects of needed class ({@link JuridicalPerson})
     * @see Users
     */
    public void CreateJuridical() throws IOException, ClassNotFoundException {

        JuridicalPerson juridicalPerson = null;
        FileInputStream f = new FileInputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personJR.out");
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(f);
            while (true) {
                try {
                    juridicalPerson = (JuridicalPerson) objectInputStream.readObject();
                    jr.add(juridicalPerson);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("catched 2");
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    /**
     * This method is called when registration to check if there exist any other user with the same username.
     *
     * @param juridicalPerson - user, which is signing up.
     * @throws ExistingLogin -(own exception)
     */
    public void CheckExistingJur(JuridicalPerson juridicalPerson) throws ExistingLogin {
        int i = 0;
        for (JuridicalPerson x : jr) {
            if (x.login.equals(juridicalPerson.login)) {
                i++;
                throw new ExistingLogin("Login already exist. Create new one!");
            }
        }
        if (i == 0) {
            System.out.println("good");
            try {
                AddorChangeJuridical(100, juridicalPerson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method is made for checking if there is a juridical person while signing in
     *
     * @param login    is a String which was written while signing in
     * @param password is a String which was written while signing in
     * @return the method returns ordinal number of user in an appropriate arraylist ({@link Users#jr}) to show its information in userpage
     * @see Users
     */
    public String CheckJuridical(String login, String password) {
        int i = 0;
        int j = 0;
        if (ph != null) {
            for (JuridicalPerson jp : jr) {
                j++;
                if (jp.login.equals(login) && jp.password.equals(password)) {
                    i++;
                    break;
                }
            }
        }
        if (i == 1) {
            return String.valueOf(j - 1);
        } else return null;
    }

    /**
     * Method is made for adding a new juridical person after registration or changing an existing one while working in "userpage":
     *
     * @param number          is an ordinal number of this user in appropriate arraylist ({@link Users#jr}), if the user is not in it yet( number = 100) it will just add it to the end.
     * @param juridicalPerson is a user which will be placed to its old place or add to the end
     * @return the method returns ordinal number of user in an appropriate arraylist ({@link Users#jr}) work with in while calling this method next time again
     * @throws IOException in case the file is empty.
     * @see Users
     */
    public int AddorChangeJuridical(int number, JuridicalPerson juridicalPerson) throws IOException {
        if (number < jr.size()) {
            jr.remove(number);
        }
        jr.add(juridicalPerson);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personJR.out"));
        objectOutputStream.flush();
        for (JuridicalPerson juridicalPerson1 : jr
        ) {
            objectOutputStream.writeObject(juridicalPerson1);
        }

        objectOutputStream.close();
        return jr.indexOf(juridicalPerson);
    }

    /**
     * Method is made for filling array list {@link Users#entr1}  from file:
     *
     * @throws IOException            ina case the file is empty
     * @throws ClassNotFoundException in case the file does note contains objects of needed class ({@link NaturalPersonEntrepreneur1})
     * @see Users
     */
    public void CreateEntrepreneur() throws IOException, ClassNotFoundException {

        NaturalPersonEntrepreneur1 entrepreneur1 = null;
        FileInputStream f = new FileInputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personEnt.out");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(f);
            while (true) {
                try {
                    entrepreneur1 = (NaturalPersonEntrepreneur1) objectInputStream.readObject();
                    entr1.add(entrepreneur1);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("catched 2");
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    /**
     * This method is called when registration to check if there exist any other user with the same username.
     *
     * @param naturalPersonEntrepreneur1 - user, which is signing up.
     * @throws ExistingLogin -(own exception)
     */
    public void CheckExistingEnt(NaturalPersonEntrepreneur1 naturalPersonEntrepreneur1) throws ExistingLogin {
        int i = 0;
        for (NaturalPersonEntrepreneur1 x : entr1) {
            if (x.login.equals(naturalPersonEntrepreneur1.login)) {
                i++;
                throw new ExistingLogin("Login already exist. Create new one!");
            }
        }
        if (i == 0) {
            try {
                AddorChangeEntrepreneur(100, naturalPersonEntrepreneur1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method is made for checking if there is a entrepreneur of type 1 while signing in:
     *
     * @param login    is a String which was written while signing in
     * @param password is a String which was written while signing in
     * @return the method returns ordinal number of user in an appropriate array list ({@link Users#entr1}) to show its information in userpage
     * @see Users
     */
    public String CheckEntrepreneur(String login, String password) {
        int i = 0;
        int j = 0;
        if (ph != null) {
            for (NaturalPersonEntrepreneur1 ent : entr1) {
                j++;
                if (ent.login.equals(login) && ent.password.equals(password)) {
                    i++;
                    break;
                }
            }
        }
        if (i == 1) {
            return String.valueOf(j - 1);
        } else return null;
    }

    /**
     * Method is made for adding a new entrepreneur of type 1 after registration or changing an existing one while working in "userpage".
     *
     * @param number        is an ordinal number of this user in appropriate array list ({@link Users#entr1}), if the user is not in it yet( number = 100) it will just add it to the end.
     * @param entrepreneur1 is a user which will be placed to its old place or add to the end
     * @return the method returns ordinal number of user in an appropriate array list ({@link Users#entr1}) work with in while calling this method next time again
     * @throws IOException in case the file is empty.
     * @see Users
     */
    public int AddorChangeEntrepreneur(int number, NaturalPersonEntrepreneur1 entrepreneur1) throws IOException {
        if (number < entr1.size()) {
            entr1.remove(number);
        }
        entr1.add(entrepreneur1);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personEnt.out"));
        objectOutputStream.flush();
        for (NaturalPersonEntrepreneur1 naturalPersonEntrepreneur1 : entr1
        ) {
            objectOutputStream.writeObject(naturalPersonEntrepreneur1);
        }

        objectOutputStream.close();
        return entr1.indexOf(entrepreneur1);
    }

    /**
     * next method is made for filling array list {@link Users#entr2} with entrepreneurs of type 2 from file
     *
     * @throws IOException            ina case the file is empty.
     * @throws ClassNotFoundException in case the file does note contains objects of needed class ({@link PhysicalPerson}).
     * @throws NullPointerException   .
     * @see Users
     */
    public void CreateEntrepreneur2() throws IOException, ClassNotFoundException, NullPointerException {

        NaturalPersonEntrepreneur2 entrepreneur2 = null;
        FileInputStream f = new FileInputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personEnt2.out");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(f);
            while (true) {
                try {
                    entrepreneur2 = (NaturalPersonEntrepreneur2) objectInputStream.readObject();
                    entr2.add(entrepreneur2);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("catched 2");
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    /**
     * This method is called when registration to check if there exist any other user with the same username.
     *
     * @param naturalPersonEntrepreneur2 - user, which is signing up.
     * @throws ExistingLogin -(own exception)
     */
    public void CheckExistingEnt2(NaturalPersonEntrepreneur2 naturalPersonEntrepreneur2) throws ExistingLogin {
        int i = 0;
        for (NaturalPersonEntrepreneur2 x : entr2) {
            if (x.login.equals(naturalPersonEntrepreneur2.login)) {
                i++;
                throw new ExistingLogin("Login already exist. Create new one!");
            }
        }
        if (i == 0) {
            try {
                AddorChangeEntrepreneur2(100, naturalPersonEntrepreneur2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * next method is made for checking if there is a entrepreneur of type 2 while signing in:
     *
     * @param login    is a String which was written while signing in
     * @param password is a String which was written while signing in
     * @return the method returns ordinal number of user in an appropriate array list ({@link Users#entr2}) to show its information in userpage
     * @see Users
     */
    public String CheckEntrepreneur2(String login, String password) {
        int i = 0;
        int j = 0;
        if (ph != null) {
            for (NaturalPersonEntrepreneur2 ent : entr2) {
                j++;
                if (ent.juridicalPerson.login.equals(login) && ent.juridicalPerson.password.equals(password)) {
                    i++;
                    break;
                }
            }
        }
        if (i == 1) {
            return String.valueOf(j - 1);
        } else return null;
    }

    /**
     * next method is made for adding a new entrepreneur of type 2 after registration or changing an existing one while working in "userpage".
     *
     * @param number        is an ordinal number of this user in appropriate array list ({@link Users#entr2}), if the user is not in it yet( number = 100) it will just add it to the end.
     * @param entrepreneur2 is a user which will be placed to its old place or add to the end
     * @return the method returns ordinal number of user in an appropriate array list ({@link Users#entr2}) work with in while calling this method next time again.
     * @throws IOException in case the file is empty.
     * @see Users
     */
    public int AddorChangeEntrepreneur2(int number, NaturalPersonEntrepreneur2 entrepreneur2) throws IOException {
        if (number < entr2.size()) {
            entr2.remove(number);
        }

        entr2.add(entrepreneur2);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/personEnt2.out"));
        objectOutputStream.flush();
        for (NaturalPersonEntrepreneur2 naturalPersonEntrepreneur2 : entr2
        ) {
            objectOutputStream.writeObject(naturalPersonEntrepreneur2);
        }

        objectOutputStream.close();
        return entr2.indexOf(entrepreneur2);
    }
}