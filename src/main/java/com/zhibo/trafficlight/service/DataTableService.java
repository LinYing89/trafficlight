package com.zhibo.trafficlight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.Circuit2;
import com.zhibo.trafficlight.data.Direction2;
import com.zhibo.trafficlight.data.RowDirection;
import com.zhibo.trafficlight.data.RowTitle;
import com.zhibo.trafficlight.data.TrafficLight2;
import com.zhibo.trafficlight.dto.CircuitDTO;
import com.zhibo.trafficlight.dto.DataTable;

@Service
public class DataTableService {

    @Autowired
    private TrafficLight2Service trafficLight2Service;
    @Autowired
    private Circuit2Service circuit2Service;
    @Autowired
    private Direction2Service direction2Service;
    
    public DataTable getDataTable(long detailsAddressId) {
        DataTable dataTable = new DataTable();
        RowTitle rowTitle = new RowTitle();
        rowTitle.addTitle("方向");
        dataTable.setRowTitle(rowTitle);
        
        //添加信号灯表头
        List<TrafficLight2> trafficLights = trafficLight2Service.findByDetailsAddressId(detailsAddressId);
        for(TrafficLight2 light : trafficLights) {
            rowTitle.addTitle(light.getName());
        }
        
        List<Circuit2> circuits = circuit2Service.findByDetailsAddressId(detailsAddressId);
        
        List<Direction2> directions = direction2Service.findByDetailsAddressId(detailsAddressId);
        for(int i = 0; i < directions.size(); i++) {
            Direction2 direction = directions.get(i);
            RowDirection rowDirection = new RowDirection();
            rowDirection.setDirectionName(direction.getName());
            for(int j = 0; j < trafficLights.size(); j++) {
                Circuit2 circuit = findByRowAndColumnIndex(circuits, i, j);
                CircuitDTO circuitDTO = circuit2ToCircuitDTO(circuit);
                rowDirection.addCircuitDTO(circuitDTO);
            }
            dataTable.addRowDirection(rowDirection);
        }
        return dataTable;
    }
    
    private Circuit2 findByRowAndColumnIndex(List<Circuit2> circuits, int rowIndex, int columnIndex) {
        for(Circuit2 c : circuits) {
            if(c.getRowIndex() == rowIndex && c.getColumnIndex() == columnIndex) {
                return c;
            }
        }
        Circuit2 circuit = new Circuit2();
        circuit.setRowIndex(rowIndex);
        circuit.setColumnIndex(columnIndex);
        //TODO 保存到数据库
        return circuit;
    }
    
    private CircuitDTO circuit2ToCircuitDTO(Circuit2 circuit) {
        CircuitDTO circuitDTO = new CircuitDTO();
        circuitDTO.setCircuitId(circuit.getId());
        circuitDTO.setCircuitNumber(circuit.getNumber());
        circuitDTO.setCollectorCode(circuit.getCollectorCode());
        circuitDTO.setMsgManagerCode(circuit.getMsgManagerCode());
        return circuitDTO;
    }
}
