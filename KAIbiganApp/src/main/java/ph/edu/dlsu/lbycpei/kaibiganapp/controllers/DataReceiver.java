package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

public interface DataReceiver {
    public default void setUserData(String firstName, String middleName, String lastName, String birthDate, String setheight, String setweight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType) {
    }
}
