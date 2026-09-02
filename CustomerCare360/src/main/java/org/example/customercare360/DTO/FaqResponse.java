package org.example.customercare360.DTO;

public class FaqResponse {

    private String question;
    private String answer;

    public FaqResponse() {
    }

    public FaqResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}