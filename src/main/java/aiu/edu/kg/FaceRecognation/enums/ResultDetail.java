package aiu.edu.kg.FaceRecognation.enums;

public enum ResultDetail {
    USER_NOT_FOUND,
    FILES_ARE_EMPTY,
    FILE_TYPE_IS_WRONG,
    EXCEPTION_ERROR,
    WRONG_REQUEST_ID,
    OK,
    NAME_IS_EMPTY,
    SURNAME_IS_EMPTY,
    POSITION_IS_EMPTY,
    GENDER_IS_EMPTY;


    public static ResultDetail valueOf(int value) {
        for (ResultDetail state : ResultDetail.values()) {
            if (state.ordinal() == value) {
                return state;
            }
        }
        return null;
    }
}
