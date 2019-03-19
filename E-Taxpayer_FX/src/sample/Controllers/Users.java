package sample.Controllers;

import sample.Persons.*;

import java.io.*;
import java.util.ArrayList;

public class Users {
    ArrayList<PhysicalPerson> ph = new ArrayList<>();
    ArrayList<NaturalPersonEntrepreneur1> entr1;
    ArrayList<NaturalPersonEntrepreneur2> entr2;
    ArrayList<JuridicalPerson> jr;

    public void CreatePhysical() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/users_PH.txt"));
        if (br != null) {
            String line;
            while ((line = br.readLine()) != null) {
                PhysicalPerson pp = null;
                System.out.println("OK");
                String login = line;
                String password = br.readLine();
                String name = br.readLine();
                String sername = br.readLine();
                int age = Integer.valueOf(br.readLine());
                int birthyear = Integer.valueOf(br.readLine());
                String passnumber = br.readLine();
                String gender = br.readLine();
                String location = br.readLine();
                int identnumb = Integer.valueOf(br.readLine());
                int carAge = Integer.valueOf(br.readLine());
                double carPrice = Double.valueOf(br.readLine());
                String TypeofRealty = br.readLine();
                double SqareRealty = Double.valueOf(br.readLine());
                double LandSqare = Double.valueOf(br.readLine());

                pp = new PhysicalPerson(login, password, name, sername, age, birthyear, passnumber, gender, location, identnumb);
                pp.SetAdditionalInf(carPrice, carAge, TypeofRealty, SqareRealty, LandSqare);
                ph.add(pp);
            }
        }
    }

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

    public int AddorChangePhysical(int number,PhysicalPerson physicalPerson) {
        if(number<ph.size()){
       ph.remove(number);}
       ph.add(physicalPerson);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/users_PH.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        if(pw!=null) {
            for (PhysicalPerson pp:ph) {
                System.out.println("OK!!!!!!!");
                pw.println(pp.login);
                pw.println(pp.password);
                pw.println(pp.Name);
                pw.println(pp.Sername);
                pw.println(pp.Age);
                pw.println(pp.BirthYear);
                pw.println(pp.GetPassNumber());
                pw.println(pp.Gender);
                pw.println(pp.GetLocation());
                pw.println(pp.GetIdentificationCode());
                pw.println(pp.CarAge);
                pw.println(pp.CarPrice);
                pw.println(pp.TypeofRealty);
                pw.println(pp.SquareofRealty);
                pw.println(pp.LandSquare);
            }
            pw.close();
        }
      return ph.indexOf(physicalPerson);
    }
}