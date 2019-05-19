package com.zhibo.trafficlight.data;

import java.util.ArrayList;
import java.util.List;

import com.zhibo.trafficlight.dto.CircuitDTO;

/**
 * 方向行
 * @author 44489
 * @version 2019年5月17日上午9:56:10
 */
public class RowDirection {

    //方向名称
    private String directionName;
    //线路列集合
    private List<CircuitDTO> circuitdtos = new ArrayList<>();
    
    public String getDirectionName() {
        return directionName;
    }
    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }
    public List<CircuitDTO> getCircuitdtos() {
        return circuitdtos;
    }
    public void setCircuitdtos(List<CircuitDTO> circuitdtos) {
        this.circuitdtos = circuitdtos;
    }
    
    public void addCircuitDTO(CircuitDTO circuitDTO) {
        circuitdtos.add(circuitDTO);
    }
}
