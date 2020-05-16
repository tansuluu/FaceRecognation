package aiu.edu.kg.FaceRecognation.enums;

public enum ResultDetail {
    USER_NOT_FOUND,
    FILES_ARE_EMPTY,
    FILE_TYPE_IS_WONG,
    EXCEPTION_ERROR,
    REQUEST_ID_NOT_GIVEN,
    REQUEST_ID_ALREADY_EXIST,
    WRONG_REQUEST_ID,
    OK;

    public static ResultDetail valueOf(int value) {
        for (ResultDetail state : ResultDetail.values()) {
            if (state.ordinal() == value) {
                return state;
            }
        }
        return null;
    }
}
