package model;

public class Task implements Comparable<Task> {

    private Integer id;
    private Integer arrivalTime;
    private Integer serviceTime;

    public Task(Integer id, Integer arrivalTime, Integer serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                '}';
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivalTime.compareTo(o.arrivalTime);
    }
}