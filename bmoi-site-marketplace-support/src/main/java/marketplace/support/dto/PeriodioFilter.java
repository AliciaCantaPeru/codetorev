/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodioFilter {

    private Integer week;
    private Integer month;
    private Integer year;

}
