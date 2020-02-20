package se.su.it.latte.teexam.models;

public enum ExamStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");

    private String apiValue;

    ExamStatus(String apiValue){
        this.apiValue = apiValue;
    }

    private String getApiValue(){
        return apiValue;
    }
}
