package com.zhibo.trafficlight.dto;

public class CircuitDTO {

    private long  circuitId;
    private int circuitNumber;
    private int msgManagerCode;
    private int collectorCode;
    
    public long getCircuitId() {
        return circuitId;
    }
    public void setCircuitId(long circuitId) {
        this.circuitId = circuitId;
    }
    public int getCircuitNumber() {
        return circuitNumber;
    }
    public void setCircuitNumber(int circuitNumber) {
        this.circuitNumber = circuitNumber;
    }
    public int getMsgManagerCode() {
        return msgManagerCode;
    }
    public void setMsgManagerCode(int msgManagerCode) {
        this.msgManagerCode = msgManagerCode;
    }
    public int getCollectorCode() {
        return collectorCode;
    }
    public void setCollectorCode(int collectorCode) {
        this.collectorCode = collectorCode;
    }
}
