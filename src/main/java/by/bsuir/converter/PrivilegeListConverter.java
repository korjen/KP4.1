package by.bsuir.converter;

import by.bsuir.dto.PrivilegeDTO;
import by.bsuir.model.Privilege;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeListConverter implements ListTwoWayConverter<Privilege, PrivilegeDTO>{
    private PrivilegeConverter privilegeConverter;

    @Override
    public List<PrivilegeDTO> convertList(List<Privilege> source) {
        List<PrivilegeDTO> privilegeDTOS = new ArrayList<>();
        for (Privilege privilege : source) {
            privilegeDTOS.add(privilegeConverter.convert(privilege));
        }
        return privilegeDTOS;
    }

    @Override
    public List<Privilege> convertBackList(List<PrivilegeDTO> target) {
        List<Privilege> privileges = new ArrayList<>();
        for(PrivilegeDTO privilegeDTO : target) {
            privileges.add(privilegeConverter.convertBack(privilegeDTO));
        }
        return privileges;
    }

    public void setPrivilegeConverter(PrivilegeConverter privilegeConverter) {
        this.privilegeConverter = privilegeConverter;
    }
}
