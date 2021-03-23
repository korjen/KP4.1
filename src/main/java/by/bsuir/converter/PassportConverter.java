package by.bsuir.converter;

import by.bsuir.dto.PassportDTO;
import by.bsuir.model.Passport;

public class PassportConverter implements TwoWayConverter<Passport, PassportDTO> {

    @Override
    public PassportDTO convert(Passport passport) {
        if(passport !=null) return new PassportDTO(passport.getPassportSeriesNumber(), passport.getSurname(), passport.getName(), passport.getPatronymic(), passport.getGender(),passport.getBirthDate(),passport.getAddress() );
        else return null;
    }

    @Override
    public Passport convertBack(PassportDTO target) {
        return new Passport(target.getPassportSeriesNumber(), target.getSurname(),target.getName(),target.getPatronymic(),target.getGender(),target.getBirthDate(),target.getAddress());
    }
}
