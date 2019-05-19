package com.zhibo.trafficlight.service;

import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.Circuit;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.Direction;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.dto.DataGroup;

@Service
public class DataGroupService {

    public DataGroup createDataGroup(DetailsAddress da) {
        DataGroup dataGroup = new DataGroup();
        dataGroup.setDetailsAddress(da.getDetailsAddress());
        dataGroup.setDetailsAddressId(da.getId());

        for (MsgManager mm : da.getMsgManagers()) {
            for (Collector collector : mm.getCollectors()) {
                for (Circuit circuit : collector.getCircuits()) {
                    setCircuitToDataGroup(circuit, dataGroup);
                }
            }
        }
        return dataGroup;
    }
    
    /**
     * 将线路的信息写到数据组DataGroup中
     * @param circuit
     * @param dataGroup
     */
    private void setCircuitToDataGroup(Circuit circuit, DataGroup dataGroup) {
        switch (circuit.getDirectionEnum()) {
        case EAST_WEST:
            setDirectionLight(dataGroup.getEastWestDirection(), circuit);
            break;
        case WEST_EAST:
            setDirectionLight(dataGroup.getWestEastDirection(), circuit);
            break;
        case SOUTH_NORTH:
            setDirectionLight(dataGroup.getSouthNorthDirection(), circuit);
            break;
        case NORTH_SOUTH:
            setDirectionLight(dataGroup.getNorthSouthDirection(), circuit);
            break;
        default:
            break;
        }
    }

    /**
     * 设置某一方向上的某条线路的状态和电流
     * 
     * @param direction
     * @param circuit
     */
    private void setDirectionLight(Direction direction, Circuit circuit) {
        switch (circuit.getLightEnum()) {
        case RED:
            direction.getRedLight().setState(circuit.getState());
            direction.getRedLight().setCurrent(circuit.getCurrent());
            break;
        case YELLOW:
            direction.getYellowLight().setState(circuit.getState());
            direction.getYellowLight().setCurrent(circuit.getCurrent());
            break;
        case GREEN:
            direction.getGreenLight().setState(circuit.getState());
            direction.getGreenLight().setCurrent(circuit.getCurrent());
            break;
        case PEDESTRIAN:
            direction.getPedestrianLight().setState(circuit.getState());
            direction.getPedestrianLight().setCurrent(circuit.getCurrent());
            break;
        default:
            break;
        }
    }
}
