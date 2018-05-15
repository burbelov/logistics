package ru.sfedu.logistics.model;



import java.util.List;

public class DataProviderResult<T> {
    private DataProviderStatuses status;
    private List<T> data;
    private List<String> messages;

    public DataProviderResult(DataProviderStatuses status, List<T> data, List<String> messages) {
        this.status = status;
        this.data = data;
        this.messages = messages;
    }

    public DataProviderResult() {
    }
    
    public DataProviderStatuses getStatus() {
        return status;
    }

    public void setStatus(DataProviderStatuses status) {
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
