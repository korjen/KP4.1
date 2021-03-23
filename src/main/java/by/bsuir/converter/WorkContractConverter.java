package by.bsuir.converter;

import by.bsuir.dto.WorkContractDTO;
import by.bsuir.model.WorkContract;

public class WorkContractConverter implements TwoWayConverter<WorkContract, WorkContractDTO> {

    @Override
    public WorkContractDTO convert(WorkContract workContract) {
        if (workContract != null) return new WorkContractDTO(workContract.getContractNumber(), workContract.getPosition(),workContract.getSignDate(),workContract.getEndDate(),workContract.getRate(),workContract.getDismissalDate(),workContract.getOrderNumber());
        else return null;
    }

    @Override
    public WorkContract convertBack(WorkContractDTO target) {
        return new WorkContract(target.getContractNumber(),target.getPosition(),target.getSignDate(),target.getEndDate(),target.getRate(),target.getDismissalDate(),target.getOrderNumber());
    }
}
