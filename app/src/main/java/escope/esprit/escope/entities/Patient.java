package escope.esprit.escope.entities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import escope.esprit.escope.Drawer.MainActivity;

/**
 * Created by wala on 04/12/2017.
 */

public class Patient{

    private int id;
    private String firstname;
    private String lastname;
    private String cin;
    private String birthdate;
    private String email;
    private String adresse;
    private String status;
    private String gender;
    private String image;
    private String createdAt;
    private String updatedAt;
    private String phoneNumer;
    private View.OnClickListener requestBtnClickListener;

    public Patient() {
    }

    public Patient(String firstname, String lastname, String cin, String birthdate, String email, String adresse, String status, String gender, String phonenNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cin = cin;
        this.birthdate = birthdate;
        this.email = email;
        this.adresse = adresse;
        this.gender = gender;
        this.status = status;
        this.phoneNumer=phonenNumber;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public View.OnClickListener getRequestBtnClickListener() {

        return this.requestBtnClickListener=requestBtnClickListener;
    }



    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (id != patient.id) return false;
        if (firstname != null ? !firstname.equals(patient.firstname) : patient.firstname != null)
            return false;
        if (lastname != null ? !lastname.equals(patient.lastname) : patient.lastname != null)
            return false;
        if (cin != null ? !cin.equals(patient.cin) : patient.cin != null) return false;
        if (birthdate != null ? !birthdate.equals(patient.birthdate) : patient.birthdate != null)
            return false;
        if (email != null ? !email.equals(patient.email) : patient.email != null) return false;
        if (adresse != null ? !adresse.equals(patient.adresse) : patient.adresse != null)
            return false;
        if (status != null ? !status.equals(patient.status) : patient.status != null) return false;
        if (gender != null ? !gender.equals(patient.gender) : patient.gender != null) return false;
        if (createdAt != null ? !createdAt.equals(patient.createdAt) : patient.createdAt != null)
            return false;
        if (updatedAt != null ? !updatedAt.equals(patient.updatedAt) : patient.updatedAt != null)
            return false;
        return requestBtnClickListener != null ? requestBtnClickListener.equals(patient.requestBtnClickListener) : patient.requestBtnClickListener == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (cin != null ? cin.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (requestBtnClickListener != null ? requestBtnClickListener.hashCode() : 0);
        return result;
    }
}
