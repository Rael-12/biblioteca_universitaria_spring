package com.rauljaimes.bibliotecaUniversitariaSpring.loan.mapper;

import com.rauljaimes.bibliotecaUniversitariaSpring.loan.dto.LoanResponse;
import com.rauljaimes.bibliotecaUniversitariaSpring.loan.entity.LoanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILoanMapper {

    LoanResponse loanEntityToResponse(LoanEntity loanEntity);
}