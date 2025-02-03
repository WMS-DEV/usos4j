package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosDormitoryParam(String dormitoryId) implements UsosParams {

    public static UsosDormitoryParamBuilder builder(String dormitoryId){
        return new UsosDormitoryParamBuilder().dormitoryId(dormitoryId);
    }
}
