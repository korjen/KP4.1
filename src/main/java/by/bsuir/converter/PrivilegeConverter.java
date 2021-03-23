package by.bsuir.converter;

import by.bsuir.dto.PrivilegeDTO;
import by.bsuir.model.Privilege;

public class PrivilegeConverter implements TwoWayConverter<Privilege, PrivilegeDTO> {
    @Override
    public PrivilegeDTO convert(Privilege source) {
        if (source != null) return new PrivilegeDTO(source.getIdPrivilege(),source.getName(),source.getEffect());
        else return null;
    }

    @Override
    public Privilege convertBack(PrivilegeDTO target) {
        Privilege privilege = new Privilege(target.getName(),target.getEffect());
        privilege.setIdPrivilege(target.getIdPrivilege());
        return privilege;
    }
}