/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.querydsl.core.types.Predicate;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Optional;
import marketplace.repository.SellerPlanRepository;
import marketplace.repository.entity.QSellerPlan;
import marketplace.repository.entity.SellerPlan;
import marketplace.service.administracion.DashboardService;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Pilar
 */
@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private SellerPlanRepository sellerPlanRepository;

    @Override
    public String obtenerDiasRestante(int idSeller) throws Exception {
        Predicate p = QSellerPlan.sellerPlan.idSeller.id.eq(idSeller);
        Optional<SellerPlan> sellerPlan = sellerPlanRepository.findOne(p);
        if (!sellerPlan.isPresent()) {
            return "--";
        }
        LocalDate fechaInicio = Util.getLocalDate(sellerPlan.get().getFecInicio());
        LocalDate fechaFin = Util.getLocalDate(sellerPlan.get().getFecFinal());
        LocalDate fechaActual = LocalDate.now();
        if (fechaFin.isBefore(fechaActual)) {
            return "00";
        }

        long dias = DAYS.between(fechaInicio, fechaFin);
        return String.valueOf(dias);
    }

}
