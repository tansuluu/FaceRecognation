package aiu.edu.kg.FaceRecognation.enums;

public enum ResultDetail {
    EMPLOYEE_NOT_FOUND,
    EXCEPTION_ERROR,
    REQUEST_ID_NOT_GIVEN,
    REQUEST_ID_ALREADY_EXIST,
    WRONG_REQUEST_ID

    public static ResultDetail valueOf(int value) {
        for (ResultDetail state : ResultDetail.values()) {
            if (state.ordinal() == value) {
                return state;
            }
        }
        return null;
    }
}
