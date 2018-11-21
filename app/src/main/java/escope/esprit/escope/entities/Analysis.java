package escope.esprit.escope.entities;

import android.view.View;

import escope.esprit.escope.User.User;

/**
 * Created by wala on 04/12/2017.
 */

public class Analysis {

    private int id;
    private String result;
    private String disease;
    private String numberOfVirus;
    private String comment;
    private String date;
    private int patientId;
    private int userId;
    private String createdAt;
    private String updatedAt;
    private View.OnClickListener requestBtnClickListener;

    public Analysis() {
    }

    public Analysis( String result, String disease, String numberOfVirus, String comment,String date) {
        this.result = result;
        this.disease = disease;
        this.numberOfVirus = numberOfVirus;
        this.comment = comment;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public String getNumberOfVirus() {
        return numberOfVirus;
    }

    public void setNumberOfVirus(String numberOfVirus) {
        this.numberOfVirus = numberOfVirus;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Analysis analysis = (Analysis) o;

        if (id != analysis.id) return false;
        if (patientId != analysis.patientId) return false;
        if (userId != analysis.userId) return false;
        if (result != null ? !result.equals(analysis.result) : analysis.result != null)
            return false;
        if (disease != null ? !disease.equals(analysis.disease) : analysis.disease != null)
            return false;
        if (numberOfVirus != null ? !numberOfVirus.equals(analysis.numberOfVirus) : analysis.numberOfVirus != null)
            return false;
        if (comment != null ? !comment.equals(analysis.comment) : analysis.comment != null)
            return false;
        if (date != null ? !date.equals(analysis.date) : analysis.date != null) return false;
        if (createdAt != null ? !createdAt.equals(analysis.createdAt) : analysis.createdAt != null)
            return false;
        if (updatedAt != null ? !updatedAt.equals(analysis.updatedAt) : analysis.updatedAt != null)
            return false;
        return requestBtnClickListener != null ? requestBtnClickListener.equals(analysis.requestBtnClickListener) : analysis.requestBtnClickListener == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (disease != null ? disease.hashCode() : 0);
        result1 = 31 * result1 + (numberOfVirus != null ? numberOfVirus.hashCode() : 0);
        result1 = 31 * result1 + (comment != null ? comment.hashCode() : 0);
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + patientId;
        result1 = 31 * result1 + userId;
        result1 = 31 * result1 + (createdAt != null ? createdAt.hashCode() : 0);
        result1 = 31 * result1 + (updatedAt != null ? updatedAt.hashCode() : 0);
        result1 = 31 * result1 + (requestBtnClickListener != null ? requestBtnClickListener.hashCode() : 0);
        return result1;
    }
}
