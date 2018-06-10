/**
 * 
 */
package io.chudgar.vinkal.service;

import io.chudgar.vinkal.domain.Statistics;
import io.chudgar.vinkal.domain.Transaction;

/**
 * @author Vinkal
 *
 */
public interface StatisticsService {
	
	void processTx(Transaction transaction);

	Statistics getStatistics();

}
