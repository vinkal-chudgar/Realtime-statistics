/**
 * 
 */
package io.chudgar.vinkal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.chudgar.vinkal.common.utils.DateTimeUtil;
import io.chudgar.vinkal.domain.Transaction;
import io.chudgar.vinkal.service.StatisticsService;

/**
 * @author Vinkal Chudgar
 *
 */
@RestController
@RequestMapping("/transactions")
public class TxController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> processTx(@RequestBody Transaction tx) {

		if (tx == null || !DateTimeUtil.isWithinInterval(tx.getTimestamp())) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		statisticsService.processTx(tx);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);

	}
}
