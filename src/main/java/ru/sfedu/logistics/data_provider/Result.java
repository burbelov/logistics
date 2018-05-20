package ru.sfedu.logistics.data_provider;



import java.util.List;

public class Result<T> {
    private ResultStatuses status;
    private List<T> data;
    private List<String> messages;

    public Result(ResultStatuses status, List<T> data, List<String> messages) {
        this.status = status;
        this.data = data;
        this.messages = messages;
    }

    public Result() {
    }
    
    public ResultStatuses getStatus() {
        return status;
    }

    public void setStatus(ResultStatuses status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    
    
    
}
