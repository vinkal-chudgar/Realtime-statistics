/**
 * 
 */
package io.chudgar.vinkal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.chudgar.vinkal.domain.Statistics;
import io.chudgar.vinkal.service.StatisticsService;

/**
 * @author Vinkal
 *
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(method = RequestMethod.GET)
	public Statistics getStatistics() {
		return statisticsService.getStatistics();
	}
}
