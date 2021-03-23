package by.bsuir.converter;

import by.bsuir.dto.*;
import by.bsuir.model.Passport;
import by.bsuir.model.User;
import by.bsuir.model.WorkContract;
import by.bsuir.model.Worker;

public class WorkerConverter implements TwoWayConverter<Worker, WorkerDTO> {
    private WorkContractConverter workContractConverter;
    private PassportConverter passportConverter;
    private UserConverter userConverter;

    @Override
    public WorkerDTO convert(Worker worker) {
        WorkContractDTO workContractDTO = workContractConverter.convert(worker.getContract());
        PassportDTO passportDTO = passportConverter.convert(worker.getPassport());
        UserDTO userDTO = userConverter.convert(worker.getUser());
        return new WorkerDTO(worker.getIdWorker(), workContractDTO, passportDTO, userDTO, worker.getExperience(),worker.getNumberOfDependants(),worker.isSoloParent(),worker.isActive(),worker.getAccountNumber());
    }

    @Override
    public Worker convertBack(WorkerDTO target) {
        WorkContract workContract = workContractConverter.convertBack(target.getContract());
        Passport passport = passportConverter.convertBack(target.getPassport());
        User user = userConverter.convertBack(target.getUser());
        return new Worker(workContract, passport, user, target.getExperience(),target.getNumberOfDependants(), target.isSoloParent(),target.isActive(),target.getAccountNumber());
    }

    public void setWorkContractConverter(WorkContractConverter workContractConverter) {
        this.workContractConverter = workContractConverter;
    }

    public void setPassportConverter(PassportConverter passportConverter) {
        this.passportConverter = passportConverter;
    }

    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}
